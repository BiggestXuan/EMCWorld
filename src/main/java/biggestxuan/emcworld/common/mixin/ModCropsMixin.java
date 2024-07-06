package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import com.blakebr0.mysticalagradditions.lib.ModCorePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.lib.ModCrops;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/02/14
 */

@EMCWorldSince("1.1.0")
@Mixin(value = ModCrops.class,remap = false)
public abstract class ModCropsMixin {
    @Shadow @Final public static Crop ENDERIUM;

    @Shadow @Final public static Crop EMERALD;

    @Shadow @Final public static Crop GOLD;

    @Shadow @Final public static Crop LAPIS_LAZULI;

    @Shadow @Final public static Crop NETHERITE;

    @Inject(method = "onRegisterCrops",at = @At("TAIL"))
    private static void __inject(ICropRegistry registry, CallbackInfo ci){
        ENDERIUM.setTier(CropTier.THREE);
        EMERALD.setTier(CropTier.FOUR);
        GOLD.setTier(CropTier.THREE);
        LAPIS_LAZULI.setTier(CropTier.THREE);
        NETHERITE.setTier(ModCorePlugin.CROP_TIER_6);
    }
}
