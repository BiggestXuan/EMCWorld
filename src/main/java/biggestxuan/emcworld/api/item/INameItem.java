package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/23
 */

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;

import javax.annotation.Nullable;

public interface INameItem {
    @Nullable
    IFormattableTextComponent getKey(ItemStack stack);
}
