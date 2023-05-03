package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/15
 */

import noobanidus.mods.lootr.repack.shoulders.data.ShoulderList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShoulderList.class)
public abstract class LootrMixin {
    @Inject(method = "load",at = @At("HEAD"),remap = false,cancellable = true)
    private static void disableLoad(CallbackInfo ci){
        ci.cancel();
    }
}
