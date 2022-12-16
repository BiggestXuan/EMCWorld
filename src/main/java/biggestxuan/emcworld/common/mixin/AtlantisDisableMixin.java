package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/13
 */

import com.mystic.atlantis.event.ElderPortalEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ElderPortalEvent.class)
public abstract class AtlantisDisableMixin {
    @Inject(method = "onDeath",at = @At("HEAD"),remap = false,cancellable = true)
    private static void disabled(LivingDeathEvent event, CallbackInfo ci){
        ci.cancel();
    }
}
