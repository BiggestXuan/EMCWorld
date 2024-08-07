package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/05
 */

import biggestxuan.emcworld.common.utils.LocaleUtils;
import com.github.alexthe666.citadel.web.WebHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;

@Mixin(WebHelper.class)
public abstract class WebHelperDisableMixin {
    @Inject(method = "getURLContents",at = @At("HEAD"), cancellable = true, remap = false)
    private static void getURLContents(String urlString, String backupFileLoc, CallbackInfoReturnable<BufferedReader> cir){
        if(LocaleUtils.isChina()){
            cir.setReturnValue(null);
        }

    }
}
