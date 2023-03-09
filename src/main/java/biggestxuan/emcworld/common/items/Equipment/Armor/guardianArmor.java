package biggestxuan.emcworld.common.items.Equipment.Armor;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/26
 */

import biggestxuan.emcworld.api.item.IDifficultyItem;
import biggestxuan.emcworld.api.item.equipment.armor.BaseEMCGodArmorItem;
import biggestxuan.emcworld.api.item.equipment.armor.EMCWorldArmorMaterial;
import net.minecraft.item.ItemStack;

public class guardianArmor extends BaseEMCGodArmorItem implements IDifficultyItem {
    public guardianArmor(int index) {
        super(EMCWorldArmorMaterial.GUARDIAN, index);
    }

    @Override
    public double healBoostRate(ItemStack stack) {
        return 1.65 + 0.08 * getLevel(stack) * getShieldRate();
    }

    @Override
    public long getMaxInfuser(ItemStack stack){
        return (long) (Math.pow(1.417,getLevel(stack)+12) * 500000 * getPrefixCommonRate(stack));
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        int level = getLevel(stack);
        return (long) (13000 + Math.pow(2.3,level));
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        int weight = 10;
        for (int i = 0; i < l; i++) {
            weight = (int) (2.175f * weight);
        }
        return weight + 4500;
    }

    @Override
    public float getSpeed(ItemStack stack) {
        return (float) (0.14f * getPrefixCommonRate(stack) + getLevel(stack) * 0.07f);
    }

    @Override
    public float health(ItemStack stack) {
        return (float) (3.6f * getPrefixCommonRate(stack) + 0.85f * getLevel(stack));
    }

    @Override
    public double hurt(ItemStack stack) {
        return 0.9 * Math.pow(0.995f,getLevel(stack) / getPrefixCommonRate(stack));
    }

    @Override
    public double getDifficulty() {
        return 3D;
    }

    @Override
    public boolean isHardcore() {
        return false;
    }

    @Override
    public double getReachDistance(ItemStack stack) {
        return 1.2 + 0.2 * getLevel(stack);
    }

    @Override
    public float shield(ItemStack stack) {
        return (float) (25 * getPrefixCommonRate(stack) + 6f * getLevel(stack) * getShieldRate());
    }

    @Override
    public float shield_speed(ItemStack stack) {
        return (float) (3f * getPrefixCommonRate(stack) + 0.3f * getLevel(stack) * getShieldRate());
    }
}
