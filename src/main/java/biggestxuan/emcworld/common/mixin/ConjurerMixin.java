package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/18
 */

import biggestxuan.emcworld.common.registry.EWDamageSource;
import com.legacy.conjurer_illager.entity.ConjurerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SpellcastingIllagerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ConjurerEntity.class)
public abstract class ConjurerMixin extends SpellcastingIllagerEntity {
    protected ConjurerMixin(EntityType<? extends SpellcastingIllagerEntity> p_i48551_1_, World p_i48551_2_) {
        super(p_i48551_1_, p_i48551_2_);
    }

    @Inject(method = "hurt",at = @At("HEAD"), cancellable = true)
    public void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if(source instanceof EWDamageSource){
            super.hurt(source,amount);
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
