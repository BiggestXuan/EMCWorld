package biggestxuan.emcworld.common.blocks.container;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.api.block.EMCWorldBaseContainer;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.common.blocks.tile.TopCoreTileEntity;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TopCoreContainer extends EMCWorldBaseContainer {
    private final BlockPos pos;
    private final World world;

    public TopCoreContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public TopCoreContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity) {
        super(EWContainerTypes.topCoreContainer.get(),pContainerId);
        TopCoreTileEntity entity = (TopCoreTileEntity) blockEntity;
        this.addSlot(new WeaponSlot(entity.getInventory(),0,53,29));
        this.addSlot(new WeaponSlot(entity.getInventory(),1,26,7));
        this.addSlot(new WeaponSlot(entity.getInventory(),2,80,7));
        this.addSlot(new WeaponSlot(entity.getInventory(),3,26,53));
        this.addSlot(new WeaponSlot(entity.getInventory(),4,80,53));
        addBar(inv);
        this.pos = entity.getBlockPos();
        this.world = entity.getLevel();
    }

    public BlockPos getPos(){
        return this.pos;
    }

    public World getWorld(){
        return this.world;
    }

    private boolean weaponIsEmpty(){
        for (int i = 0; i <= 4; i++) {
            if(slots.get(i).hasItem()){
                return false;
            }
        }
        return true;
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index){
        Slot slot = slots.get(index);
        ItemStack s = ItemStack.EMPTY;
        if(slot != null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            s = stack.copy();
            if(index <= 4){
                if(!moveItemStackTo(stack,5,41,true)){
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack,s);
            }else{
                Slot weapon = slots.get(0);
                if(weapon == null){
                    return ItemStack.EMPTY;
                }
                if(isValidItem(stack) && weaponIsEmpty()){
                    if(!moveItemStackTo(stack,0,4,false)){
                        return ItemStack.EMPTY;
                    }
                }else{
                    if(index < 31){
                        if (!moveItemStackTo(stack, 32, 41, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index < 40){
                        if (!moveItemStackTo(stack, 5, 32, false)) {
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
            if(stack.getCount() == s.getCount()){
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn,stack);
        }
        return s;
    }

    public static boolean isValidItem(ItemStack stack){
        Item item = stack.getItem();
        if(item instanceof IUpgradeableItem){
            var i = (IUpgradeableItem) item;
            return i.getLevel(stack) >= i.getMaxLevel();
        }
        return false;
    }

    static class WeaponSlot extends Slot{
        public WeaponSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
            super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        }

        public boolean mayPlace(ItemStack p_75214_1_) {
            return isValidItem(p_75214_1_);
        }
    }
}
