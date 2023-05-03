package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.Message;
import journeymap.common.network.data.model.Location;
import journeymap.common.util.JourneyMapTeleport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(JourneyMapTeleport.class)
public abstract class JourneyMapTeleportMixin {
    @Inject(method = "attemptTeleport",at = @At("HEAD"),remap = false, cancellable = true)
    public void disable(Entity entity, Location location, CallbackInfoReturnable<Boolean> cir){
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            Message.sendMessage(player, EMCWorld.tc("message.tp.deny"));
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
