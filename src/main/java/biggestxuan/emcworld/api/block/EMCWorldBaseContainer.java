package biggestxuan.emcworld.api.block;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/11
 */

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class EMCWorldBaseContainer extends Container {

    protected EMCWorldBaseContainer(@Nullable ContainerType<?> p_i50105_1_, int p_i50105_2_) {
        super(p_i50105_1_, p_i50105_2_);
    }

    protected void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }
    protected void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    protected void addBar(PlayerInventory inventory){
        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
    }

    @Override
    public boolean stillValid(@Nonnull PlayerEntity p_75145_1_) {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
