package biggestxuan.emcworld.api.event;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.IPrefixItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

@EMCWorldSince("1.0.0")
public class PlayerPrefixFreshEvent extends PlayerEvent {
    private final ItemStack stack;
    private IPrefixItem.Prefix prefix;

    public PlayerPrefixFreshEvent(PlayerEntity player, ItemStack stack, IPrefixItem.Prefix prefix){
        super(player);
        this.stack = stack;
        this.prefix = prefix;
    }

    public ItemStack getStack() {
        return stack;
    }

    public IPrefixItem.Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(IPrefixItem.Prefix prefix) {
        this.prefix = prefix;
    }
}
