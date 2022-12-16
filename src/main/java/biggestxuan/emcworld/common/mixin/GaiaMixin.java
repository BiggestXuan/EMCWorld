package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/16
 */

import biggestxuan.emcworld.common.registry.EWDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityDoppleganger;

@Mixin(EntityDoppleganger.class)
public abstract class GaiaMixin extends LivingEntity {
    @Shadow(remap = false)
    private int tpDelay;
    @Shadow(remap = false)
    private boolean spawnPixies;

    protected GaiaMixin(EntityType<? extends LivingEntity> p_i48577_1_, World p_i48577_2_) {
        super(p_i48577_1_, p_i48577_2_);
    }

    @Inject(method = "actuallyHurt",at = @At("HEAD"), cancellable = true)
    public void actHurt(DamageSource source, float amount, CallbackInfo ci){
        super.actuallyHurt(source, amount);
        Entity attacker = source.getDirectEntity();
        if (attacker != null) {
            Vector3 thisVector = Vector3.fromEntityCenter(this);
            Vector3 playerVector = Vector3.fromEntityCenter(attacker);
            Vector3 motionVector = thisVector.subtract(playerVector).normalize().multiply(0.75);
            if (this.getHealth() > 0.0F) {
                this.setDeltaMovement(-motionVector.x, 0.5, -motionVector.z);
                this.tpDelay = 4;
                this.spawnPixies = true;
            }
        }
        this.invulnerableTime = Math.max(this.invulnerableTime, 20);
        ci.cancel();
    }

    @Inject(method = "hurt",at = @At(value = "RETURN",ordinal = 0), cancellable = true)
    public void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(super.hurt(source,amount));
    }

    @Inject(method = "hurt",at = @At("HEAD"), cancellable = true)
    public void staffAttack(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if(source instanceof EWDamageSource){
            cir.setReturnValue(super.hurt(source,amount));
        }
    }

    @Inject(method = "getDamageAfterArmorAbsorb",at = @At("HEAD"),cancellable = true)
    protected void getDamageAfterArmorAbsorb(DamageSource source, float damage, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(super.getDamageAfterArmorAbsorb(source,damage));
        cir.cancel();
    }
}