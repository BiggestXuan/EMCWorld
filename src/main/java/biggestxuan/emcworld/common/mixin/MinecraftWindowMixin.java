package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.CalendarUtils;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftWindowMixin {
    @Inject(method = "createTitle", at = @At("HEAD"), cancellable = true)
    public void setTitle(CallbackInfoReturnable<String> cir){
        String title = EMCWorld.TITLE+" - "+ new CalendarUtils().getNowTimeWelcome()+"!";  //Dynamic change, INSTANCE -> static change
        cir.setReturnValue(title);
        cir.cancel();
    }
}