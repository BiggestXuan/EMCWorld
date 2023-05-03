package biggestxuan.emcworld.api.item.equipment;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/01
 */

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public interface IPurityItem {
    String name = "purity";
    default void setPurity(ItemStack stack,int level){
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.putInt(name,level);
    }

    default int getPurity(ItemStack stack){
        CompoundNBT nbt = stack.getOrCreateTag();
        return nbt.getInt(name);
    }

    default void addPurity(ItemStack stack){
        setPurity(stack,getPurity(stack));
    }

    int getMaxPurity();

}
