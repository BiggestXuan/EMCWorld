package biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/06
 */

import biggestxuan.emcworld.api.item.equipment.warhammer.BaseEMCGodWarHammer;
import net.minecraft.item.ItemStack;

public class Creation extends BaseEMCGodWarHammer {
    @Override
    protected double getAttackCostRate(ItemStack stack) {
        return Math.pow(1.053f,getLevel(stack));
    }

    @Override
    protected long modifyEMCSecond(ItemStack stack) {
        return Math.round(Math.pow(1.75,getLevel(stack)-5)*8.5);
    }

    @Override
    protected double criticalChance(ItemStack stack) {
        return Math.pow(1.0245,getLevel(stack)) - 1;
    }

    @Override
    protected double criticalRate(ItemStack stack) {
        return Math.pow(1.0225,getLevel(stack));
    }

    @Override
    protected float damage(ItemStack stack) {
        return (float) (Math.pow(1.186,getLevel(stack)) * 7);
    }

    @Override
    protected double attackRange(ItemStack stack) {
        return Math.pow(1.14,getLevel(stack));
    }

    @Override
    public double getAttackSpeed(ItemStack stack) {
        return Math.pow(0.98,getLevel(stack));
    }

}
