package biggestxuan.emcworld.common.mixin;

import biggestxuan.EMCStage.event.ToolTipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ToolTipEvent.class,remap = false)
public class EMCStageTooltipMixin {
    @Inject(method = "tooltipEvent",at = @At("HEAD"),cancellable = true)
    private static void _inject(ItemTooltipEvent event, CallbackInfo ci){
        ci.cancel();
    }
}
