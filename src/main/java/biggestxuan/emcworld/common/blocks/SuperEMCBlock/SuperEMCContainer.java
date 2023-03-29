package biggestxuan.emcworld.common.blocks.SuperEMCBlock;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/26
 */

import biggestxuan.emcworld.api.block.EMCWorldBaseContainer;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;

public class SuperEMCContainer extends EMCWorldBaseContainer {
    private final BlockPos pos;
    public SuperEMCContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public SuperEMCContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity){
        super(EWContainerTypes.superEMCContainer.get(),pContainerId);
        SuperEMCTileEntity tile = (SuperEMCTileEntity) blockEntity;
        this.addSlot(new Slot(tile.getInventory(),0,30,15));
        this.addSlot(new Slot(tile.getInventory(),1,30,59));
        this.addSlot(new Slot(tile.getInventory(),2,115,36){
            @Override
            public boolean mayPlace(ItemStack p_75214_1_) {
                return false;
            }
        });
        pos = tile.getBlockPos();
        addBar(inv);
    }

    public BlockPos getPos(){
        return pos;
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index){
        ItemStack s = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            s = stack.copy();
            if(index <= 2){
                if(!moveItemStackTo(stack,3,39,true)){
                    return ItemStack.EMPTY;
                }
            }else{
                if(!moveItemStackTo(stack,0,2,false)){
                    if(index < 30){
                        if (!moveItemStackTo(stack, 30, 39, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index < 39){
                        if (!moveItemStackTo(stack, 3, 39, false)) {
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
