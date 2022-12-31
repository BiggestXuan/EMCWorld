package biggestxuan.emcworld.common.items.Equipment.Armor;

/*
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
    public long getMaxInfuser(ItemStack stack){
        return (long) (Math.pow(1.417,getLevel(stack)) * 500000);
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
        return 0.01f * getLevel(stack);
    }

    @Override
    public float extraHealth(ItemStack stack) {
        return 0.3f * getLevel(stack);
    }

    @Override
    public double hurtRate(ItemStack stack) {
        return Math.pow(0.993,getLevel(stack));
    }

    @Override
    public double getReachDistance(ItemStack stack) {
        return 0.1 * getLevel(stack);
    }

    @Override
    public float maxShield(ItemStack stack) {
        return 8f * getLevel(stack) * getShieldRate();
    }

    @Override
    public float shieldSpeed(ItemStack stack) {
        return 0.45f * getLevel(stack) * getShieldRate();
    }
}
