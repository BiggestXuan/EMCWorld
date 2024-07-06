package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/28
 */

import net.minecraft.item.ItemStack;

public interface IEMCRepairableItem{
    long getTickCost(ItemStack stack);
}
