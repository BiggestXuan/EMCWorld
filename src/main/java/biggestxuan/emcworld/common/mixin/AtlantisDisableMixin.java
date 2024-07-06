package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/13
 */

import biggestxuan.emcworld.common.config.ConfigManager;
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
        if(ConfigManager.SUNDRY_GUARDIAN_DEATH_PORTAL.get()){
            ci.cancel();
        }
    }
}
