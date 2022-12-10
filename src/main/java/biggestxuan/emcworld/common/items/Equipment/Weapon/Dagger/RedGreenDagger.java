package biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/09
 */

import biggestxuan.emcworld.api.item.equipment.dagger.BaseEMCGodDagger;
import net.minecraft.item.ItemStack;

public class RedGreenDagger extends BaseEMCGodDagger {
    @Override
    protected double AttackSpeed(ItemStack stack) {
        return Math.pow(1.015,getLevel(stack));
    }

    @Override
    protected double EMCCost(ItemStack stack) {
        return Math.pow(1.058f,getLevel(stack));
    }

    @Override
    protected long EMCModify(ItemStack stack) {
        return Math.round(Math.pow(1.8,getLevel(stack)));
    }

    @Override
    protected float AddonDamage(ItemStack stack) {
        return (float) (Math.pow(1.16,getLevel(stack)) * 7);
    }
}
