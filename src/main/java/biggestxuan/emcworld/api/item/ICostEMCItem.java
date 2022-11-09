package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import net.minecraft.item.ItemStack;

public interface ICostEMCItem {
    double getEMCCostRate();

    double costEMCWhenAttack(ItemStack stack);
}
