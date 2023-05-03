package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/27
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import com.kwpugh.gobber2.lists.ToolMaterialList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolMaterialList.class)
public abstract class GobberTierMixin {
    @Shadow(remap = false)
    private int enchantability;

    @Shadow(remap = false)
    private float attackDamage;

    @Inject(method = "getAttackDamageBonus",at = @At("RETURN"),cancellable = true)
    public void damage(CallbackInfoReturnable<Float> cir){
        if(ConfigManager.FREE_MODE.get()) return;
        float damage;
        if(enchantability == 20){
            damage = 10;
        }
        else if(enchantability == 25){
            damage = 15;
        }
        else{
            damage = 25;
        }
        cir.setReturnValue(attackDamage + damage);
        cir.cancel();
    }
}
