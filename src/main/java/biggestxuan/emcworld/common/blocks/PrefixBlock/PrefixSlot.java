package biggestxuan.emcworld.common.blocks.PrefixBlock;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/01
 */

import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.common.items.Equipment.PrefixScroll;
import biggestxuan.emcworld.common.items.Equipment.Scroll.ScrollItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class PrefixSlot {
    public static class ScrollSlot extends Slot{

        public ScrollSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
            super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        }

        @Override
        public boolean mayPlace(ItemStack p_75214_1_) {
            return p_75214_1_.getItem() instanceof ScrollItem || p_75214_1_.getItem() instanceof PrefixScroll;
        }
    }

    public static class WeaponSlot extends Slot{

        public WeaponSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
            super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        }

        @Override
        public boolean mayPlace(ItemStack p_75214_1_) {
            return p_75214_1_.getItem() instanceof IPrefixItem || p_75214_1_.getItem() instanceof PrefixScroll;
        }
    }
}
