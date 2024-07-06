package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/12/14
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.compact.MysticalAgriculture.EMCWorldMysticalAgriculturePlugin;
import com.blakebr0.mysticalagradditions.lib.ModCorePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@EMCWorldSince("1.1.0")
@Mixin(value = ModCorePlugin.class,remap = false)
public class ModCorePluginMixin {
    @Shadow @Final public static Crop NETHER_STAR;

    @Shadow @Final public static Crop DRAGON_EGG;

    @Inject(method = "onRegisterCrops",at = @At("TAIL"))
    public void __inject(ICropRegistry registry, CallbackInfo ci){
        NETHER_STAR.setTier(EMCWorldMysticalAgriculturePlugin.CROP_TIER_7);
        DRAGON_EGG.setTier(EMCWorldMysticalAgriculturePlugin.CROP_TIER_8);
    }
}
