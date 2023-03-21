package biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.api.block.EMCWorldBaseContainer;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.Slot.LuckySlot;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.Slot.MaterialSlot;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.Slot.WeaponSlot;
import biggestxuan.emcworld.common.items.Equipment.Weapon.LuckyItem.ILuckyItem;
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


public class WeaponUpgradeContainer extends EMCWorldBaseContainer {
    private final BlockPos pos;
    private final World world;
    private final IIntArray data;

    public WeaponUpgradeContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new IntArray(7));
    }

    public WeaponUpgradeContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity, IIntArray intArray) {
        super(EWContainerTypes.weaponUpgradeContainer.get(),pContainerId);
        this.data = intArray;
        addDataSlots(this.data);
        WeaponUpgradeBlockTileEntity entity = (WeaponUpgradeBlockTileEntity) blockEntity;
        this.addSlot(new WeaponSlot(entity.getInventory(),0,53,29));
        this.addSlot(new MaterialSlot(entity.getInventory(),1,26,7));
        this.addSlot(new MaterialSlot(entity.getInventory(),2,80,7));
        this.addSlot(new MaterialSlot(entity.getInventory(),3,26,53));
        this.addSlot(new MaterialSlot(entity.getInventory(),4,80,53));
        this.addSlot(new LuckySlot(entity.getInventory(),5,116,53));
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

    public int getChance(){
        return this.data.get(0);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index){
        Slot slot = slots.get(index);
        ItemStack s = ItemStack.EMPTY;
        if(slot != null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            s = stack.copy();
            if(index <= 5){
                if(!moveItemStackTo(stack,6,42,true)){
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack,s);
            }else{
                Slot weapon = slots.get(0);
                Slot gem = slots.get(5);
                Item item = stack.getItem().getItem();
                if(weapon == null || gem == null){
                    return ItemStack.EMPTY;
                }
                if(item instanceof IUpgradeableItem && !weapon.hasItem()){
                    if(!moveItemStackTo(stack,0,1,false)){
                        return ItemStack.EMPTY;
                    }
                }else if (item instanceof ILuckyItem && (item.getItem().equals(gem.getItem().getItem()) || !gem.hasItem())){
                    if(!moveItemStackTo(stack,5,6,false)){
                        return ItemStack.EMPTY;
                    }
                }else if (item instanceof IUpgradeableMaterial){
                    if(!moveItemStackTo(stack,1,5,false)){
                        return ItemStack.EMPTY;
                    }
                }else{
                    if(index < 32){
                        if (!moveItemStackTo(stack, 32, 41, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index < 41){
                        if (!moveItemStackTo(stack, 6, 41, false)) {
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
