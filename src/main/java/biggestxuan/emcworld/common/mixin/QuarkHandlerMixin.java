package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/14
 */

import biggestxuan.emcworld.common.utils.LocaleUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.base.handler.ContributorRewardHandler;

@Mixin(ContributorRewardHandler.class)
public abstract class QuarkHandlerMixin {
    @Inject(method = "init",at = @At("HEAD"),remap = false,cancellable = true)
    private static void networkHandler(CallbackInfo ci){
        if(LocaleUtils.isChina()){
            ci.cancel();
        }
    }
}
