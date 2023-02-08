package biggestxuan.emcworld.api.item;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/04
 */

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public interface IOwnerItem {
    String name = "emcworld_owner";

    default UUID getOwner(ItemStack stack){
        return stack.getOrCreateTag().getUUID(name);
    }

    default void setOwner(ItemStack stack,UUID uuid){
        stack.getOrCreateTag().putUUID(name,uuid);
    }

    default void setOwner(ItemStack stack, PlayerEntity player){
        setOwner(stack,player.getUUID());
    }
}
