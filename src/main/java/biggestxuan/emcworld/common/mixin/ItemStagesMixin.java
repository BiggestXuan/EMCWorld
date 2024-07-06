package biggestxuan.emcworld.common.mixin;

import net.darkhax.itemstages.ItemStages;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/19
 */

@Mixin(value = ItemStages.class,remap = false)
public abstract class ItemStagesMixin {
    @Inject(method = "onPlayerTick", at = @At("HEAD"),cancellable = true)
    public void _inject(CallbackInfo ci){
        ci.cancel();
    }
}
