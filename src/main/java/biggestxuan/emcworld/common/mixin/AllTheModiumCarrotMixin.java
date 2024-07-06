package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import com.thevortex.allthemodium.items.Allthemodium_Carrot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/29
 */

@EMCWorldSince("1.0.2")
@Mixin(Allthemodium_Carrot.class)
public abstract class AllTheModiumCarrotMixin {
    @Redirect(method = "finishUsingItem",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/player/PlayerEntity;addEffect(Lnet/minecraft/potion/EffectInstance;)Z"))
    public boolean _loss(PlayerEntity instance, EffectInstance effectInstance){

        return false;
    }
}
