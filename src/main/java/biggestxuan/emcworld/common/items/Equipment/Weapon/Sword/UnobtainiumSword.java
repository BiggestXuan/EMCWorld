package biggestxuan.emcworld.common.items.Equipment.Weapon.Sword;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/19
 */

import biggestxuan.emcworld.api.item.ISecondEMCItem;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseWeaponItem;
import biggestxuan.emcworld.api.item.equipment.tier.EWAtmTier;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import net.minecraft.item.ItemStack;

public class UnobtainiumSword extends BaseWeaponItem implements IRangeAttackWeapon, ISecondEMCItem {
    public UnobtainiumSword() {
        super(EWAtmTier.UNOBTAINIUM,0,-2.4F);
    }

    @Override
    public int getUseLevel(ItemStack stack) {
        return 100;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 3000;
    }

    @Override
    public double getAttackRange(ItemStack stack) {
        return 2.5;
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }
}
