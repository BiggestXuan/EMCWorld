package biggestxuan.emcworld.common.blocks.container;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.block.EMCWorldBaseContainer;
import biggestxuan.emcworld.common.blocks.tile.EMCOreCoreTileEntity;
import biggestxuan.emcworld.common.items.EMCGemItem;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/09
 */

@EMCWorldSince("1.0.5")
public class EMCOreCoreContainer extends EMCWorldBaseContainer {
    public final World world;
    public final BlockPos pos;

    public EMCOreCoreContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public EMCOreCoreContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity) {
        super(EWContainerTypes.emcOreCoreContainer.get(), pContainerId);
        EMCOreCoreTileEntity tile = (EMCOreCoreTileEntity) blockEntity;
        world = tile.getLevel();
        pos = tile.getBlockPos();
        this.addSlot(new Slot(tile, 0, 12, 34) {
            @Override
            public boolean mayPlace(ItemStack p_75214_1_) {
                return p_75214_1_.getItem() instanceof EMCGemItem;
            }
        });
        int x = 62;
        int y = 14;
        for (int i = 1; i <= 15; i++) {
            this.addSlot(new Slot(tile, i, x, y) {
                @Override
                public boolean mayPlace(ItemStack p_75214_1_) {
                    return false;
                }
            });
            x += 18;
            if (i % 5 == 0) {
                x = 62;
                y += 18;
            }
        }
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
            if (index == 0) {
                if (!moveItemStackTo(stack, 16, 52, false)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, s);
            }
            Item item = stack.getItem();
            if (item instanceof EMCGemItem) {
                if (!moveItemStackTo(stack, 0, 1, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if(index < 16){
                    if (!moveItemStackTo(stack, 16, 52, false)) {
                        return ItemStack.EMPTY;
                    }
                }else if (index < 43) {
                    if (!moveItemStackTo(stack, 43, 52, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 52) {
                    if (!moveItemStackTo(stack, 16, 43, false)) {
                        return ItemStack.EMPTY;
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