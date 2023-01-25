package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/16
 */

import biggestxuan.emcworld.common.compact.ScalingHealth.DifficultyHelper;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.lib.ResourceLocationHelper;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Mixin(EntityDoppleganger.class)
public abstract class GaiaMixin extends LivingEntity {
    @Shadow(remap = false)
    private int tpDelay;
    @Shadow(remap = false)
    private boolean spawnPixies;

    @Shadow(remap = false)
    @Mutable
    private int playerCount;

    @Shadow(remap = false)
    public abstract List<PlayerEntity> getPlayersAround();

    @Shadow(remap = false)
    @Final
    public static float MAX_HP;

    @Shadow(remap = false)
    private boolean hardMode;

    @Shadow(remap = false)
    @Final
    @Mutable
    private final List<UUID> playersWhoAttacked;

    protected GaiaMixin(EntityType<? extends LivingEntity> p_i48577_1_, World p_i48577_2_, List<UUID> playersWhoAttacked) {
        super(p_i48577_1_, p_i48577_2_);
        this.playersWhoAttacked = playersWhoAttacked;
    }

    @Inject(method = "getDefaultLootTable",at = @At("HEAD"),cancellable = true)
    public void getLootTable(CallbackInfoReturnable<ResourceLocation> cir){
        cir.setReturnValue(ResourceLocationHelper.prefix(this.hardMode ? "gaia_guardian_2" : "gaia_guardian"));
    }

    @Inject(method = "actuallyHurt",at = @At("HEAD"), cancellable = true)
    public void actHurt(DamageSource source, float amount, CallbackInfo ci){
        super.actuallyHurt(source, amount);
        Entity attacker = source.getDirectEntity();
        EntityDoppleganger gaia = (EntityDoppleganger) (Object) this;
        if(source instanceof EWDamageSource){
            EWDamageSource damage = (EWDamageSource) source;
            if(damage.getPlayer() != null){
                PlayerEntity player = damage.getPlayer();
                if(!playersWhoAttacked.contains(player.getUUID())){
                    playersWhoAttacked.add(player.getUUID());
                }
                this.setLastHurtByPlayer(player);
            }
        }
        if (attacker != null) {
            int actPlayer = getPlayersAround().size();
            double difficulty = DifficultyHelper.getAreaDifficulty(gaia.level,gaia.blockPosition());
            if(playerCount < actPlayer){
                Objects.requireNonNull(gaia.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(MAX_HP * (1 + actPlayer * 0.25) + difficulty);
                gaia.heal((float) ((actPlayer-playerCount)*(320 + difficulty)));
                playerCount = actPlayer;
            }
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