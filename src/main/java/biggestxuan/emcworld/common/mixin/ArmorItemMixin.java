package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/19
 */

import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.equipment.armor.IHealBoostArmor;
import biggestxuan.emcworld.api.item.equipment.armor.IUpgradeableArmor;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin extends Item implements IUpgradeableArmor, IHealBoostArmor,IPrefixItem {

    @Shadow
    @Final
    @Mutable
    private final int defense;

    public ArmorItemMixin(Properties p_i48487_1_, int defense) {
        super(p_i48487_1_);
        this.defense = defense;
    }

    @Override
    public double getHealBoostRate(ItemStack stack) {
        return 1 + 0.0125 * getLevel(stack) * MathUtils.log(2,defense);
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        int weight = 15;
        for (int i = 0; i < l; i++) {
            weight = (int) (1.8f * weight);
        }
        return (int) (weight * 1.75);
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return 1;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return Math.min((int) (defense * ConfigManager.DIFFICULTY.get() / 4d),12);
    }

    @Override
    public double hurtRate(ItemStack stack) {
        double b = 1;
        Prefix prefix = getPrefix(stack);
        if(prefix == Prefix.NULL) return b;
        if(prefix.getLevel() <= 3){
            b += 0.04 * (4 -prefix.getLevel());
        }else{
            b -= 0.013 * (prefix.getLevel() -4);
        }
        return b;
    }

    @Override
    public float extraHealth(ItemStack stack) {
        Prefix prefix = getPrefix(stack);
        float health = 0f;
        if(prefix.getLevel() <= 3){
            for (int i = 0; i < 4-prefix.getLevel(); i++) {
                health -= 0.2f * defense;
            }
        }else{
            for (int i = 0; i < prefix.getLevel()-4; i++) {
                health += 0.05f * defense;
            }
        }
        int level = getLevel(stack);
        health = health >= 1 ? (float) ((1 + 0.0125 * level) * health) : (float) (0.75f * MathUtils.log(2,defense+1) * level);
        return health;
    }
}
