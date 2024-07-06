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

public class GemSlot extends SlotItemHandler {

    public GemSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack p_75214_1_) {
        return p_75214_1_.getItem() instanceof EMCGemItem;
    }
}
