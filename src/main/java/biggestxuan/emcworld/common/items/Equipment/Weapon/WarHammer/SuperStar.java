package biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/16
 */

import biggestxuan.emcworld.api.item.equipment.warhammer.BaseEMCGodWarHammer;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.item.ItemStack;

public class SuperStar extends BaseEMCGodWarHammer {
    @Override
    protected double getAttackCostRate(ItemStack stack) {
        return Math.pow(1.037f,getLevel(stack)*0.97f);
    }

    @Override
    protected long modifyEMCSecond(ItemStack stack) {
        return Math.round(Math.pow(1.36,getLevel(stack)-5)*8.5);
    }

    @Override
    protected double criticalChance(ItemStack stack) {
        return Math.pow(1.016,getLevel(stack)) - 0.9;
    }

    @Override
    protected double criticalRate(ItemStack stack) {
        return Math.pow(1.015,getLevel(stack));
    }

    @Override
    protected float damage(ItemStack stack) {
        float base = (float) (Math.pow(1.145,Math.min(22,getLevel(stack))) * 5);
        return getLevel(stack) >= 22 ? (float) MathUtils.getGodWeaponAddition(stack, base) : base;
    }

    @Override
    protected double attackRange(ItemStack stack) {
        return Math.pow(1.097,getLevel(stack));
    }

    @Override
    public double AttackSpeed(ItemStack stack) {
        return Math.pow(0.975,getLevel(stack));
    }

}
