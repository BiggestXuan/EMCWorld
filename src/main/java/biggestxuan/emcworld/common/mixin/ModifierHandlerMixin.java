package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/30
 */

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.IronGolemEntity;
import net.silentchaos512.scalinghealth.utils.ModifierHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModifierHandler.class)
public abstract class ModifierHandlerMixin {
    @Inject(method = "addAttackDamage",
            at = @At(value = "INVOKE",
                    target = "Lnet/silentchaos512/scalinghealth/utils/ModifierHandler;setModifier(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/ai/attributes/Attribute;Ljava/util/UUID;Ljava/lang/String;DLnet/minecraft/entity/ai/attributes/AttributeModifier$Operation;)V"),
            cancellable = true,remap = false)
    private static void disableBonus(LivingEntity entity, double amount, AttributeModifier.Operation op, CallbackInfo ci){
        if(entity instanceof IronGolemEntity){
            ci.cancel();
        }
    }

    @Inject(method = "setMaxHealth",at = @At("HEAD"),cancellable = true,remap = false)
    private static void disableHealth(LivingEntity entity, double amount, AttributeModifier.Operation op, CallbackInfo ci){
        if(entity instanceof IronGolemEntity){
            ci.cancel();
        }
    }
}
