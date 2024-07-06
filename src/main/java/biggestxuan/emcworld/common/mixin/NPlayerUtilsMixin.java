package biggestxuan.emcworld.common.mixin;

import com.chaosthedude.explorerscompass.util.PlayerUtils;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/26
 */

@Mixin(PlayerUtils.class)
public abstract class NPlayerUtilsMixin {
    @Inject(method = "canTeleport",at = @At("HEAD"),remap = false, cancellable = true)
    private static void __inject(PlayerEntity player, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }
}
