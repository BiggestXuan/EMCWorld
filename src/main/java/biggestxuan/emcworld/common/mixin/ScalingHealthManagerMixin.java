package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.champions.common.integration.scalinghealth.ScalingHealthManager;

@Mixin(ScalingHealthManager.class)
public abstract class ScalingHealthManagerMixin {
    @Inject(method = "getSpawnIncrease",at = @At("HEAD"),remap = false,cancellable = true)
    private static void getIncrease(int tier, LivingEntity livingEntity, CallbackInfoReturnable<Double> cir){
        double avg = MathUtils.getRangePlayerAverageIndex(livingEntity,64);
        double cc = Math.abs(avg - tier);
        double c = 0;
        if(cc >= 5){
            c += 0.01D;
        }
        if(cc >= 4){
            c += 0.025D;
        }
        if(cc >= 3){
            c += 0.05D;
        }
        if(cc >= 2){
            c += 0.075D;
        }
        if(cc >= 1){
            c += 0.1D;
        }
        else c += 0.15D;
        cir.setReturnValue(c);
    }
}
