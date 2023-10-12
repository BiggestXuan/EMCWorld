package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.classicbar.overlays.vanilla.Absorption;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/07
 */

@EMCWorldSince("1.0.5")
@Mixin(Absorption.class)
public abstract class AbsorptionMixin {
    @Inject(method = "renderBar",at = @At("HEAD"),remap = false,cancellable = true)
    public void _inject(MatrixStack stack, PlayerEntity player, int screenWidth, int screenHeight, CallbackInfo ci){
        ci.cancel();
    }

    @Inject(method = "renderText",at = @At("HEAD"),remap = false,cancellable = true)
    public void _inject1(MatrixStack stack, PlayerEntity player, int screenWidth, int screenHeight, CallbackInfo ci){
        ci.cancel();
    }

    @Inject(method = "renderIcon",at = @At("HEAD"),remap = false,cancellable = true)
    public void _inject2(MatrixStack stack, PlayerEntity player, int screenWidth, int screenHeight, CallbackInfo ci){
        ci.cancel();
    }
}
