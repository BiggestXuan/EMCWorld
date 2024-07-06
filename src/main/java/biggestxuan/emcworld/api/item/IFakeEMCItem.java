package biggestxuan.emcworld.api.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2023/06/01
 */

public interface IFakeEMCItem {
    long getFakeEMC(ItemStack stack);

    long getActEMC(ItemStack stack);

    default void doSomething(PlayerEntity player,ItemStack stack){

    }
}
