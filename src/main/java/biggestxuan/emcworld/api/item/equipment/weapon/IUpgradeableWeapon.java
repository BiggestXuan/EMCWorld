package biggestxuan.emcworld.api.item.equipment.weapon;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.api.item.ICostEMCItem;
import biggestxuan.emcworld.api.item.IEMCRepairableItem;
import biggestxuan.emcworld.api.item.ISecondEMCItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.common.utils.DamageUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IUpgradeableWeapon extends IUpgradeableItem,IRangeAttackWeapon,IAdditionsDamageWeapon, ICostEMCItem, ISecondEMCItem, IEMCRepairableItem {
    @Override
    DamageUtils getAttackRange(PlayerEntity player, ItemStack stack);
}
