package biggestxuan.emcworld.api.item.equipment.armor;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/24
 */

import biggestxuan.emcworld.api.item.ICostEMCItem;
import biggestxuan.emcworld.api.item.IEMCRepairableItem;
import biggestxuan.emcworld.api.item.ISecondEMCItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import net.minecraft.item.ItemStack;

public interface IUpgradeableArmor extends IUpgradeableItem ,ICostEMCItem, ISecondEMCItem{
    double hurtRate(ItemStack stack);

    float extraHealth(ItemStack stack);
}
