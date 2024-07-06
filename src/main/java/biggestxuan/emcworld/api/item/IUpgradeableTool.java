package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/19
 */

import net.minecraft.item.ItemStack;

public interface IUpgradeableTool extends IUpgradeableItem{
    double getAdditionSpeed(ItemStack stack);
}
