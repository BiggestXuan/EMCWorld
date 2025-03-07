package biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/06
 */

import biggestxuan.emcworld.api.item.equipment.warhammer.BaseEMCGodWarHammer;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.item.ItemStack;

public class Creation extends BaseEMCGodWarHammer {
    @Override
    protected double getAttackCostRate(ItemStack stack) {
        return Math.pow(1.065f,getLevel(stack));
    }

    @Override
    protected long modifyEMCSecond(ItemStack stack) {
        return Math.round(Math.pow(1.4,getLevel(stack)-5)*8.7);
    }

    @Override
    protected double criticalChance(ItemStack stack) {
        return Math.pow(1.021,getLevel(stack)) - 0.8;
    }

    @Override
    protected double criticalRate(ItemStack stack) {
        return Math.pow(1.018,getLevel(stack));
    }

    @Override
    protected float damage(ItemStack stack) {
        float base = (float) (Math.pow(1.162,Math.min(22,getLevel(stack))) * 5);
        return getLevel(stack) >= 22 ? (float) MathUtils.getGodWeaponAddition(stack, base) : base;
    }

    @Override
    protected double attackRange(ItemStack stack) {
        return Math.pow(1.125,getLevel(stack));
    }

    @Override
    public double AttackSpeed(ItemStack stack) {
        return Math.pow(0.95,getLevel(stack));
    }

}
