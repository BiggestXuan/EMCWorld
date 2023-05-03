package biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/09
 */

import biggestxuan.emcworld.api.item.equipment.dagger.BaseEMCGodDagger;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.item.ItemStack;

public class RedGreenDagger extends BaseEMCGodDagger {
    @Override
    protected double AttackSpeed(ItemStack stack) {
        return Math.pow(1.015,getLevel(stack)*0.98f);
    }

    @Override
    protected double EMCCost(ItemStack stack) {
        return Math.pow(1.058f,getLevel(stack));
    }

    @Override
    protected long EMCModify(ItemStack stack) {
        return Math.round(Math.pow(1.41,getLevel(stack)));
    }

    @Override
    protected float AddonDamage(ItemStack stack) {
        float base = getLevel(stack) == 0 ? 0 + 1.5f : (float) (Math.pow(1.15,Math.min(22,getLevel(stack))) * 7);
        return getLevel(stack) >= 22 ? (float) MathUtils.getGodWeaponAddition(stack,base) : base;
    }
}
