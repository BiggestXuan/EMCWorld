package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/19
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.equipment.armor.IUpgradeableArmor;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin extends Item implements IUpgradeableMaterial, IUpgradeableArmor {

    @Shadow
    @Final
    @Mutable
    private final int defense;

    public ArmorItemMixin(Properties p_i48487_1_, int defense) {
        super(p_i48487_1_);
        this.defense = defense;
    }

    @Nonnull
    @Override
    public ITextComponent getName(@Nonnull ItemStack p_200295_1_) {
        int level = getLevel(p_200295_1_);
        String name = this.toString();
        ResourceLocation rl = getRegistryName();
        if(rl == null || getMaxLevel() == 0){
            return super.getName(p_200295_1_);
        }
        return EMCWorld.tc("item."+rl.getNamespace()+"."+name).append(" (+"+level+")");
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
        return 1;
    }

    @Override
    public float extraHealth(ItemStack stack) {
        return getLevel(stack) == 0 ? 0 : defense * 0.08f * getLevel(stack);
    }
}
