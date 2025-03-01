package biggestxuan.emcworld.common.items.Equipment.Armor;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/26
 */

import biggestxuan.emcworld.api.item.equipment.armor.BaseEMCGodArmorItem;
import biggestxuan.emcworld.api.item.equipment.armor.EMCWorldArmorMaterial;
import net.minecraft.item.ItemStack;

public class fireRedArmor extends BaseEMCGodArmorItem {
    public fireRedArmor(int index) {
        super(EMCWorldArmorMaterial.FIRE_RED,index);
    }

    @Override
    public double healBoostRate(ItemStack stack) {
        return 1 + 0.02 * getLevel(stack) * getShieldRate();
    }

    @Override
    public long getMaxInfuser(ItemStack stack){
        return (long) (Math.pow(1.417,getLevel(stack)) * 500000 * getPrefixCommonRate(stack));
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        int level = getLevel(stack);
        if(level == 0) return 0L;
        return (long) Math.pow(2.2,level);
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        int weight = 10;
        for (int i = 0; i < l; i++) {
            weight = (int) (1.95f * weight);
        }
        return weight;
    }

    @Override
    public float getSpeed(ItemStack stack) {
        return (float) (0.01f * getLevel(stack) * getPrefixCommonRate(stack));
    }

    @Override
    public float health(ItemStack stack) {
        return (float) (0.3f * getLevel(stack) * getPrefixCommonRate(stack));
    }

    @Override
    public double hurt(ItemStack stack) {
        return Math.pow(0.9975,getLevel(stack)) / getPrefixCommonRate(stack);
    }

    @Override
    public double getReachDistance(ItemStack stack) {
        return 0.1 * getLevel(stack);
    }

    @Override
    public float shield(ItemStack stack) {
        return (float) ((20f * getLevel(stack) * getPrefixCommonRate(stack) + 25) * getShieldRate());
    }

    @Override
    public float shield_speed(ItemStack stack) {
        return (float) (1.6f * getLevel(stack) * getShieldRate() * getPrefixCommonRate(stack));
    }
}
