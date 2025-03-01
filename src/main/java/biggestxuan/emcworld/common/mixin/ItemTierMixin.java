package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/27
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.ItemTier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemTier.class)
public abstract class ItemTierMixin {
    @Shadow
    @Final
    private float damage;

    @Shadow
    @Final
    private int level;

    @Shadow
    @Final
    private int enchantmentValue;

    @Inject(method = "getAttackDamageBonus",at = @At("RETURN"),cancellable = true)
    public void damage(CallbackInfoReturnable<Float> cir){
        if(ConfigManager.FREE_MODE.get()) return;
        float base = level == 4 ? 2.25F : 1;
        if(level == 4){
            cir.setReturnValue(26 * 1.3F);
        }
        cir.setReturnValue(damage + damage * level * base * enchantmentValue / 30);
    }
}
