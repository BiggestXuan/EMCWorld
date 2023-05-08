package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/04
 */

import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Shadow
    public abstract float getCurrentItemAttackStrengthDelay();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> p_i48577_1_, World p_i48577_2_) {
        super(p_i48577_1_, p_i48577_2_);
    }

    @Inject(method = "tick",at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        PlayerEntity player = (PlayerEntity) (Object) this;
        player.getCapability(EMCWorldCapability.UTIL).ifPresent(c -> {
            player.getCapability(EMCWorldCapability.PLAYER_LEVEL).ifPresent(s -> {
                if(c.getTimer() > 0 && s.getProfession() == 5){
                    c.setTimer(0);
                    this.attackStrengthTicker = (int) this.getCurrentItemAttackStrengthDelay();
                }
            });
        });
    }
}
