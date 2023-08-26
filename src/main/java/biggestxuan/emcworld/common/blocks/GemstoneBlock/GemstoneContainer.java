package biggestxuan.emcworld.common.blocks.GemstoneBlock;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/16
 */

import biggestxuan.emcworld.api.block.EMCWorldBaseContainer;
import biggestxuan.emcworld.api.item.equipment.IGemInlaidItem;
import biggestxuan.emcworld.common.blocks.GemstoneBlock.Slot.GemstoneSlot;
import biggestxuan.emcworld.common.blocks.GemstoneBlock.Slot.WeaponSlot;
import biggestxuan.emcworld.common.items.Equipment.BaseWeaponGemItem;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;

public class GemstoneContainer extends EMCWorldBaseContainer {
    public GemstoneContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public GemstoneContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity){
        super(EWContainerTypes.gemstoneContainer.get(),pContainerId);
        GemstoneTileEntity tile = (GemstoneTileEntity) blockEntity;
        this.addSlot(new WeaponSlot(tile.getInventory(),0,45,31));
        this.addSlot(new GemstoneSlot(tile.getInventory(),1,114,31));
        addBar(inv);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index) {
        ItemStack s = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack stack = slot.getItem();
            s = stack.copy();
            if(index <= 1){
                if (!moveItemStackTo(stack, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, s);
            }
            Item item = stack.getItem();
            if(item instanceof IGemInlaidItem && slots.get(0) != null && !slots.get(0).hasItem()){
                if (!moveItemStackTo(stack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            }else if(item instanceof BaseWeaponGemItem && slots.get(1) != null && !slots.get(1).hasItem()){
                if (!moveItemStackTo(stack, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            }else{
                if(index < 28){
                    if (!moveItemStackTo(stack, 28, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 38){
                    if (!moveItemStackTo(stack, 2, 28, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if(stack.getCount() == s.getCount()){
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn,stack);
        }
        return s;
    }
}
