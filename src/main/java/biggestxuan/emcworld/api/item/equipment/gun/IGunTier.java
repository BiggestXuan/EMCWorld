package biggestxuan.emcworld.api.item.equipment.gun;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/04
 */

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public interface IGunTier extends IItemTier {
    double getPenetrate();

    String getName();

    int cd(ItemStack stack);

    double accuracy(ItemStack stack);

    double backlash(ItemStack stack);
}
