package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/19
 */

import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.equipment.armor.IUpgradeableArmor;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin extends Item implements IUpgradeableArmor, IPrefixItem {

    @Shadow
    @Final
    @Mutable
    private final int defense;

    public ArmorItemMixin(Properties p_i48487_1_, int defense) {
        super(p_i48487_1_);
        this.defense = defense;
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        int weight = 15;
        for (int i = 0; i < l; i++) {
            weight = (int) (1.58f * weight);
        }
        return (int) (weight * 1.75);
    }

    @Override
    public double getEMCCostRate() {
        return 1;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return 1;
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return 0;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return (int) (defense * ConfigManager.DIFFICULTY.get() / 4d);
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
        return getLevel(stack) < 2 ? (float)getPrefixCommonRate(stack) : (float) (defense * 0.5f * getPrefixCommonRate(stack) * getLevel(stack));
    }

    private double getPrefixCommonRate(ItemStack stack){
        double b = 0;
        Prefix prefix = getPrefix(stack);
        if(prefix == Prefix.NULL) return b;
        if(prefix.getLevel() <= 3){
            b =  - (4 - prefix.getLevel()) * 0.2 * defense;
        }else{
            b = (prefix.getLevel()-4) * 0.1 * defense;
        }
        return b;
    }
}
