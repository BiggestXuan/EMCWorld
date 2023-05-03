package biggestxuan.emcworld.common.blocks.InfuserBlock;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/14
 */

import biggestxuan.emcworld.api.block.EMCWorldBaseContainer;
import biggestxuan.emcworld.common.blocks.InfuserBlock.Slot.GemSlot;
import biggestxuan.emcworld.common.blocks.InfuserBlock.Slot.ResultSlot;
import biggestxuan.emcworld.common.items.EMCGemItem;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class InfuserContainer extends EMCWorldBaseContainer {
    private final BlockPos pos;
    private final World world;
    private final IIntArray data;

    public InfuserContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new IntArray(7));
    }

    public InfuserContainer(int pContainerId, PlayerInventory inv, TileEntity entity, IIntArray data) {
        super(EWContainerTypes.infuserContainer.get(),pContainerId);
        this.data = data;
        addDataSlots(this.data);
        InfuserBlockTileEntity tileEntity = (InfuserBlockTileEntity) entity;
        assert tileEntity != null;
        this.addSlot(new Slot(tileEntity.getInventory(),0,43,6));
        this.addSlot(new Slot(tileEntity.getInventory(),1,15,29));
        this.addSlot(new GemSlot(tileEntity.getInventory(),2,43,29));
        this.addSlot(new Slot(tileEntity.getInventory(),3,71,29));
        this.addSlot(new Slot(tileEntity.getInventory(),4,26,52));
        this.addSlot(new Slot(tileEntity.getInventory(),5,62,52));
        this.addSlot(new ResultSlot(tileEntity.getInventory(),6,133,29));
        addBar(inv);
        this.pos = tileEntity.getBlockPos();
        this.world = tileEntity.getLevel();
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index) {
        Slot slot = slots.get(index);
        ItemStack s = ItemStack.EMPTY;
        if(slot != null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            s = stack.copy();
            if(index <= 6){
                if (!moveItemStackTo(stack, 7, 42, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, s);
            } else{
                Item item = stack.getItem();
                Slot gemSlot = slots.get(2);
                if(item instanceof EMCGemItem && gemSlot != null && (!gemSlot.hasItem() ||  gemSlot.getItem().getItem().equals(stack.getItem()))){
                    if (!moveItemStackTo(stack, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                }else{
                    if(!(moveItemStackTo(stack, 0, 2, false) || moveItemStackTo(stack, 3, 6, false))){
                        if(index < 33){
                            if (!moveItemStackTo(stack, 34, 43, false)) {
                                return ItemStack.EMPTY;
                            }
                        } else if (index < 43){
                            if (!moveItemStackTo(stack, 1, 34, false)) {
                                return ItemStack.EMPTY;
                            }
                        }
                    }
                }
            }
            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            slot.onTake(playerIn,stack);
        }
        return s;
    }

    public int getProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 20;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getCraftLevel(){
        return this.data.get(4);
    }

    public int getRadiation(){
        return Math.min(57 * this.data.get(3) / this.data.get(6),57);
    }

    public int getEMC(){
        return (int) (70 - (70 * (1.0d * this.data.get(2) / this.data.get(5))));
    }

    public BlockPos getPos(){
        return pos;
    }

    public World getWorld() {
        return world;
    }
}
