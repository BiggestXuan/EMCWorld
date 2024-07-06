package biggestxuan.emcworld.common.mixin;

import biggestxuan.EMCStage.event.CommonEvent;
import moze_intel.projecte.api.event.PlayerAttemptCondenserSetEvent;
import moze_intel.projecte.api.event.PlayerAttemptLearnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CommonEvent.class,remap = false)
public class EMCStageCommonMixin {
    @Inject(method = "playerTryToGetKnowledgeEvent",at = @At("HEAD"), cancellable = true)
    private static void _inject(PlayerAttemptLearnEvent event, CallbackInfo ci){
        ci.cancel();
    }

    @Inject(method = "playerSetCondenserEvent",at = @At("HEAD"), cancellable = true)
    private static void __inject(PlayerAttemptCondenserSetEvent event, CallbackInfo ci){
        ci.cancel();
    }
}
