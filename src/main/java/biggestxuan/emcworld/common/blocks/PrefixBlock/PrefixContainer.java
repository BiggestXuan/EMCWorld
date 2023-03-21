package biggestxuan.emcworld.common.blocks.PrefixBlock;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/01
 */

import biggestxuan.emcworld.api.block.EMCWorldBaseContainer;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.common.items.Equipment.PrefixScroll;
import biggestxuan.emcworld.common.items.Equipment.Scroll.ScrollItem;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;

public class PrefixContainer extends EMCWorldBaseContainer {
    private final BlockPos pos;
    public PrefixContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public PrefixContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity){
        super(EWContainerTypes.prefixContainer.get(),pContainerId);
        PrefixTileEntity tile = (PrefixTileEntity) blockEntity;
        this.addSlot(new PrefixSlot.ScrollSlot(tile.getInventory(),0,45,31));
        this.addSlot(new PrefixSlot.WeaponSlot(tile.getInventory(),1,114,31));
        pos = tile.getBlockPos();
        addBar(inv);
    }

    public BlockPos getPos(){
        return pos;
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index){
        Slot slot = slots.get(index);
        ItemStack s = ItemStack.EMPTY;
        if(slot != null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            s = stack.copy();
            if(index <= 1){
                if(!moveItemStackTo(stack,2,38,true)){
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack,s);
            }else{
                Slot left = slots.get(0);
                Slot right = slots.get(1);
                if(left == null || right == null){
                    return ItemStack.EMPTY;
                }
                Item item = stack.getItem();
                if(item instanceof ScrollItem && (!left.hasItem() || item.equals(left.getItem().getItem()))){
                    if (!moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }else if(item instanceof IPrefixItem && !right.hasItem()){
                    if (!moveItemStackTo(stack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }else if(item instanceof PrefixScroll){
                    if(left.hasItem() && left.getItem().getItem() instanceof ScrollItem){
                        if (!moveItemStackTo(stack, 1, 2, false)) {
                            return ItemStack.EMPTY;
                        }
                    }else{
                        if(!moveItemStackTo(stack, 0, 1, false)){
                            return ItemStack.EMPTY;
                        }
                    }
                }else{
                    if(index < 29){
                        if (!moveItemStackTo(stack, 29, 38, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index < 38){
                        if (!moveItemStackTo(stack, 2, 38, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }
            if(stack.isEmpty()){
                slot.set(ItemStack.EMPTY);
            }else{
                slot.setChanged();
            }
            slot.onTake(playerIn,stack);
        }
        return s;
    }
}
