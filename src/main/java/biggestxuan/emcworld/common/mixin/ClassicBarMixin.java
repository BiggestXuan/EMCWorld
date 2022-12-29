package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import biggestxuan.emcworld.client.render.ArcanaBar;
import biggestxuan.emcworld.client.render.EMCShieldBar;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.classicbar.ClassicBar;
import tfar.classicbar.EventHandler;

@Mixin(ClassicBar.class)
public abstract class ClassicBarMixin {
    @Inject(method = "postInit",at = @At(
            value = "INVOKE",
            target = "Ltfar/classicbar/EventHandler;registerAll([Ltfar/classicbar/overlays/BarOverlay;)V"
    ),remap = false)
    public void postInit(FMLClientSetupEvent event, CallbackInfo info){
        EventHandler.register(new ArcanaBar());
        EventHandler.register(new EMCShieldBar());
    }
}
