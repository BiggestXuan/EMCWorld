package biggestxuan.emcworld.common.blocks.container;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/30
 */

import biggestxuan.emcworld.common.blocks.tile.AdvancedUpdateNumber;
import biggestxuan.emcworld.common.blocks.tile.AdvancedUpdateTileEntity;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class AdvancedUpdateContainer extends Container {
    private final BlockPos pos;
    private final World world;

    public AdvancedUpdateContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData){
        this(pContainerId, inv, extraData.readBlockPos(),inv.player.level, new AdvancedUpdateNumber());
    }

    public AdvancedUpdateContainer(int id, PlayerInventory inventory, BlockPos pos, World world, AdvancedUpdateNumber number){
        super(EWContainerTypes.advancedUpdateContainer.get(), id);
        addDataSlots(number);
        AdvancedUpdateTileEntity tileEntity = (AdvancedUpdateTileEntity) world.getBlockEntity(pos);
        assert tileEntity != null;
        this.addSlot(new Slot(tileEntity.getInventory(),0,130,32));
        disPlayUI(inventory, 84);
        this.pos = pos;
        this.world = world;
    }

    private int addSlotRange(IInventory inventory, int index, int x, int y) {
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += 18;
            index++;
        }
        return index;
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index) {
        ItemStack s = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            s = stack.copy();
            Slot slot1 = slots.get(0);
            if (index == 0){
                if (!moveItemStackTo(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, s);
            }
            if(slot1 != null){
                if(slot1.hasItem()){
                    if(index < 27){
                        if (!moveItemStackTo(stack, 28, 37, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index < 37){
                        if (!moveItemStackTo(stack, 1, 37, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }else{
                    if (!moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
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

    private int addSlotBox(IInventory inventory, int index, int y) {
        for (int j = 0; j < 3; j++) {
            index = addSlotRange(inventory, index, 8, y);
            y += 18;
        }
        return index;
    }

    private void disPlayUI(PlayerInventory inventory, int a2){
        addSlotBox(inventory, 9, a2);
        a2 += 58;
        addSlotRange(inventory, 0, 8, a2);
    }

    @Override
    public boolean stillValid(@Nonnull PlayerEntity p_75145_1_) {
        return true;
    }

    public BlockPos getPos(){
        return pos;
    }

    public World getWorld() {
        return world;
    }
}
