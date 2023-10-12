package biggestxuan.emcworld.common.blocks.container.slot;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/16
 */

import biggestxuan.emcworld.common.items.Equipment.BaseWeaponGemItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class GemstoneSlot extends Slot {
    public GemstoneSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    @Override
    public boolean mayPlace(ItemStack p_75214_1_) {
        return p_75214_1_.getItem() instanceof BaseWeaponGemItem;
    }
}
