package biggestxuan.emcworld.common.items.Equipment.Weapon.Sword;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/19
 */

import biggestxuan.emcworld.api.item.INeedLevelItem;
import biggestxuan.emcworld.api.item.ISecondEMCItem;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseWeaponItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Tier.EWAtmTier;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import net.minecraft.item.ItemStack;

public class VibraniumSword extends BaseWeaponItem implements IRangeAttackWeapon, ISecondEMCItem, INeedLevelItem {
    public VibraniumSword() {
        super(EWAtmTier.VIBRANIUM,0,-2.4F);
    }

    @Override
    public int getUseLevel(ItemStack stack) {
        return 90;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 1000;
    }

    @Override
    public double getAttackRange(ItemStack stack) {
        return 2;
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }
}
