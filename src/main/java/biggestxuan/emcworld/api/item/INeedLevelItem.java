package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/19
 */

import net.minecraft.item.ItemStack;

public interface INeedLevelItem {
    int getUseLevel(ItemStack stack);
}
