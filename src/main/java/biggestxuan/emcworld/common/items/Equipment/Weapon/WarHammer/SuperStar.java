package biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/16
 */

import biggestxuan.emcworld.api.item.equipment.warhammer.BaseEMCGodWarHammer;
import net.minecraft.item.ItemStack;

public class SuperStar extends BaseEMCGodWarHammer {
    @Override
    protected double getAttackCostRate(ItemStack stack) {
        return Math.pow(1.04f,getLevel(stack));
    }

    @Override
    protected long modifyEMCSecond(ItemStack stack) {
        return Math.round(Math.pow(1.7,getLevel(stack)-5)*8.5);
    }

    @Override
    protected double criticalChance(ItemStack stack) {
        return Math.pow(1.023,getLevel(stack)) - 0.9;
    }

    @Override
    protected double criticalRate(ItemStack stack) {
        return Math.pow(1.024,getLevel(stack));
    }

    @Override
    protected float damage(ItemStack stack) {
        return (float) (Math.pow(1.16,getLevel(stack)) * 6.5);
    }

    @Override
    protected double attackRange(ItemStack stack) {
        return Math.pow(1.097,getLevel(stack));
    }

    @Override
    public double getAttackSpeed(ItemStack stack) {
        return Math.pow(0.973,getLevel(stack));
    }

}
