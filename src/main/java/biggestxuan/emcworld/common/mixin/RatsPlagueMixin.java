package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/16
 */

import com.github.alexthe666.rats.client.event.ClientEvents;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientEvents.class)
public abstract class RatsPlagueMixin {
    @Inject(method = "onRenderOverlay",at = @At("HEAD"),remap = false,cancellable = true)
    public void onRenderOverlay(RenderGameOverlayEvent.Pre event, CallbackInfo ci){
        ci.cancel();
    }
}
