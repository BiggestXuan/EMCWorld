package biggestxuan.emcworld.common.items.Equipment.Weapon.Sword;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/19
 */

import biggestxuan.emcworld.api.item.INeedLevelItem;
import biggestxuan.emcworld.api.item.ISecondEMCItem;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseWeaponItem;
import biggestxuan.emcworld.api.item.equipment.weapon.IUpgradeableWeapon;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Tier.EWSwordTier;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import biggestxuan.emcworld.common.utils.DamageUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class UnobtainiumSword extends BaseWeaponItem implements IUpgradeableWeapon,IRangeAttackWeapon, ISecondEMCItem, INeedLevelItem {
    public UnobtainiumSword() {
        super(EWSwordTier.UNOBTAINIUM,0,-2.4F);
    }

    @Override
    public int getUseLevel(ItemStack stack) {
        return 100;
    }

    @Override
    public double getEMCCostRate() {
        return 0d;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 3000000;
    }

    @Override
    public DamageUtils getAttackRange(PlayerEntity player ,ItemStack stack) {
        return DamageUtils.of(2.5);
    }

    @Override
    public boolean isEnchantable(ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        return 1;
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public DamageUtils getAdditionsDamage(PlayerEntity player,ItemStack stack) {
        return DamageUtils.of(0);
    }
}
