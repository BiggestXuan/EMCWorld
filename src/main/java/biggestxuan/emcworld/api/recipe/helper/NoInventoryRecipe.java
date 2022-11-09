package biggestxuan.emcworld.api.recipe.helper;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/15
 */

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class NoInventoryRecipe implements IInventory {
    public static final NoInventoryRecipe INSTANCE = new NoInventoryRecipe();

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public ItemStack getItem(int p_70301_1_) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItemNoUpdate(int p_70304_1_) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int p_70299_1_, ItemStack p_70299_2_) {

    }

    @Override
    public void setChanged() {

    }

    @Override
    public boolean stillValid(PlayerEntity p_70300_1_) {
        return false;
    }

    @Override
    public void clearContent() {

    }
}
