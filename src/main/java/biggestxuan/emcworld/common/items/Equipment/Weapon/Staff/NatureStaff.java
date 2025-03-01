package biggestxuan.emcworld.common.items.Equipment.Weapon.Staff;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/25
 */

import biggestxuan.emcworld.api.item.equipment.staff.BaseEMCGodStaff;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.item.ItemStack;

public class NatureStaff extends BaseEMCGodStaff {
    @Override
    protected long getBaseEMCModify(ItemStack stack) {
        return Math.round(Math.pow(1.4,getLevel(stack)-5)*8);
    }

    @Override
    protected double getBaseCostRate(ItemStack stack) {
        return Math.pow(1.035,getLevel(stack)*0.98f);
    }

    @Override
    protected float getBaseBurstDamage(ItemStack stack) {
        float base = (float) Math.pow(1.175f,Math.min(22,getLevel(stack))-1);
        return getLevel(stack) >= 22 ? (float) MathUtils.getGodWeaponAddition(stack,base) : base;
    }

    @Override
    protected double getBaseCriticalChance(ItemStack stack) {
        return 0.02 * getLevel(stack)*0.99f;
    }

    @Override
    protected double getBaseCriticalRate(ItemStack stack) {
        return 0.015 * getLevel(stack)*0.99f;
    }

    @Override
    protected double getBaseBurstSpeed(ItemStack stack) {
        return Math.log((getLevel(stack) + 1) * 2) * 3;
    }

    @Override
    protected int getColor() {
        return 0xede4a8;
    }
}
