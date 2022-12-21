package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import divinerpg.capability.Arcana;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Arcana.class)
public abstract class ArcanaMixin {
    @Inject(method = "sendPacket",at = @At("HEAD"),remap = false, cancellable = true)
    public void handle(PlayerEntity player, CallbackInfo ci){
        ci.cancel();
    }
}
