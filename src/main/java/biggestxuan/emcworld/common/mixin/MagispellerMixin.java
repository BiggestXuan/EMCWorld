package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/18
 */

import biggestxuan.emcworld.common.registry.EWDamageSource;
import com.yellowbrossproductions.illageandspillage.entities.MagispellerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MagispellerEntity.class)
public abstract class MagispellerMixin extends AbstractIllagerEntity {
    protected MagispellerMixin(EntityType<? extends AbstractIllagerEntity> p_i48556_1_, World p_i48556_2_) {
        super(p_i48556_1_, p_i48556_2_);
    }

    @Inject(method = "hurt",at = @At("HEAD"), cancellable = true)
    public void hurt(DamageSource p_70097_1_, float p_70097_2_, CallbackInfoReturnable<Boolean> cir){
        if(p_70097_1_ instanceof EWDamageSource){
            super.hurt(p_70097_1_,p_70097_2_);
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
