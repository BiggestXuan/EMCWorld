package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/13
 */

import net.darkhax.itemstages.ItemStages;
import net.darkhax.itemstages.Restriction;
import net.darkhax.itemstages.RestrictionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStages.class)
public abstract class ItemStageMixin {
    @Inject(method = "onPlayerTick",at = @At("HEAD"),remap = false, cancellable = true)
    public void TickMixin(TickEvent.PlayerTickEvent event, CallbackInfo ci){
        if(event.player.isCreative()){
            ci.cancel();
        }
    }

    @Inject(method = "onItemPickup",at = @At("HEAD"),remap = false, cancellable = true)
    public void PickUpMixin(EntityItemPickupEvent event, CallbackInfo ci){
        PlayerEntity player = event.getPlayer();
        if(player != null && !player.level.isClientSide && !(player instanceof FakePlayer)){
            final ItemStack stack = event.getItem().getItem();
            final Restriction restriction = RestrictionManager.INSTANCE.getRestriction(event.getPlayer(), stack);
            if (restriction != null && restriction.shouldPreventPickup()) {
                event.setCanceled(true);
                event.getItem().setPickUpDelay(restriction.getPickupDelay());
            }
        }
        ci.cancel();
    }
}
