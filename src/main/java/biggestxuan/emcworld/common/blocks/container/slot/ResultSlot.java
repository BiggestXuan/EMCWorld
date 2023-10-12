package biggestxuan.emcworld.common.blocks.container.slot;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/14
 */

import biggestxuan.emcworld.common.items.EMCGemItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ResultSlot extends SlotItemHandler {

    public ResultSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack p_75214_1_) {
        return false;
    }
}
