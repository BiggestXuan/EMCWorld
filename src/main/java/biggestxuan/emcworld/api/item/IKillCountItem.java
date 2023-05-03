package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/19
 */

import net.minecraft.item.ItemStack;

public interface IKillCountItem {
    default long getKillCount(ItemStack stack){
        return stack.getOrCreateTag().getLong("kill_count");
    }

    default void addKillCount(ItemStack stack){
        stack.getOrCreateTag().putLong("kill_count",getKillCount(stack)+1);
    }

}
