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

public class AtmSword extends BaseWeaponItem implements IRangeAttackWeapon, ISecondEMCItem {
    public AtmSword() {
        super(EWAtmTier.ATM,0,-2.4F);
    }

    @Override
    public int getUseLevel(ItemStack stack) {
        return 80;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 300;
    }

    @Override
    public double getAttackRange(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }
}
