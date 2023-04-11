package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/30
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.exception.EMCWorldNotFinalException;
import com.mojang.authlib.minecraft.SocialInteractionsService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.client.GameConfiguration;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(method = "createSocialInteractions",
            at = @At(value = "INVOKE",target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V",remap = false))
    public void createSocialInteractions(YggdrasilAuthenticationService p_244735_1_, GameConfiguration p_244735_2_, CallbackInfoReturnable<SocialInteractionsService> cir){
        EMCWorld.isOffline = true;
        EMCWorld.LOGGER.warn("Your Minecraft instance is offline!");
        //Minecraft.crash(new CrashReport("\u8bf7\u4f7f\u7528\u6b63\u7248\u767b\u5f55\uff01",new EMCWorldNotFinalException("\u56e0\u4e3a\u6574\u5408\u5305\u672a\u5b8c\u5de5\uff0c\u4ec5\u9650\u5185\u6d4b\u73a9\u5bb6\u8fdb\u5165\u3002\u5f3a\u5236\u6b63\u7248\u9a8c\u8bc1\u4e3a\u4e86\u9a8c\u8bc1\u60a8\u7684\u6743\u9650\u3002")));
    }
}
