package biggestxuan.emcworld.common.blocks.container;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.block.EMCWorldBaseContainer;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import dev.latvian.mods.projectex.block.entity.PersonalLinkBlockEntity;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/11
 */

@EMCWorldSince("1.0.5")
public class PersonalLinkContainer extends EMCWorldBaseContainer {
    public PersonalLinkContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public PersonalLinkContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity) {
        super(EWContainerTypes.personalLinkContainer.get(), pContainerId);
        PersonalLinkBlockEntity tile = (PersonalLinkBlockEntity) blockEntity;
        final int[] x = {8};
        final int[] y = {18};
        tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(c -> {
            for (int i = 0; i < 27; i++) {
                addSlot(new SlotItemHandler(c,i, x[0],y[0]){
                    @Override
                    public boolean mayPlace(@Nonnull ItemStack stack) {
                        return EMCHelper.getEmcValue(stack) > 0;
                    }
                });
                x[0] += 18;
                if(i == 8 || i == 17){
                    x[0] = 8;
                    y[0] += 18;
                }
            }
        });
        addBar(inv);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index) {
        ItemStack s = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            s = stack.copy();
            if(index <= 26){
                if(!moveItemStackTo(stack,27,62,true)){
                    return ItemStack.EMPTY;
                }
            }else{
                if (!moveItemStackTo(stack, 0, 26, false)) {
                    if(index <= 53){
                        if (!moveItemStackTo(stack, 54, 62, false)) {
                            return ItemStack.EMPTY;
                        }
                    }else{
                        if(!moveItemStackTo(stack,27,53,false)){
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (stack.getCount() == s.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, stack);
        }
        return s;
    }
}
