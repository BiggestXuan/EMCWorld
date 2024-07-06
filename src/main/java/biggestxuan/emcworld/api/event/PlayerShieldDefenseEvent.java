package biggestxuan.emcworld.api.event;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/30
 */

@EMCWorldSince("1.0.5")
public class PlayerShieldDefenseEvent extends PlayerEvent {
    public final ItemStack stack;
    public final float damage;

    public PlayerShieldDefenseEvent(PlayerEntity player, ItemStack stack,float damage) {
        super(player);
        this.stack = stack;
        this.damage = damage;
    }
}
