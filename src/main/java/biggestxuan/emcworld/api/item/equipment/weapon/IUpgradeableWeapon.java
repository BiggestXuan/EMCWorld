package biggestxuan.emcworld.api.item.equipment.weapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.api.item.IUpgradeableItem;
import net.minecraft.item.ItemStack;

public interface IUpgradeableWeapon extends IUpgradeableItem,IRangeAttackWeapon,IAdditionsDamageWeapon{
    @Override
    double getAttackRange(ItemStack stack);
}
