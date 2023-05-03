package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import divinerpg.events.ArcanaRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArcanaRenderer.class)
public abstract class ArcanaRenderMixin {
    @Inject(method = "onTickRender",at = @At("HEAD"),cancellable = true,remap = false)
    public void onTickRender(RenderGameOverlayEvent.Post event, CallbackInfo ci){
        ci.cancel();
    }
}
