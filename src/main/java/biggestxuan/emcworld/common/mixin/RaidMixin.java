package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/23
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.capability.IEntityUtilCapability;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.raid.RaidEffectExecutor;
import biggestxuan.emcworld.common.utils.RaidUtils;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.*;

@Mixin(Raid.class)
public abstract class RaidMixin {
    @Mutable
    @Shadow
    @Final
    private final int numGroups;

    @Shadow
    @Final
    private final Map<Integer, Set<AbstractRaiderEntity>> groupRaiderMap = Maps.newHashMap();

    @Shadow
    public int groupsSpawned;

    @Mutable
    @Shadow
    @Final
    private final ServerWorld level;

    @Mutable
    @Shadow
    @Final
    private final ServerBossInfo raidEvent;

    @Shadow
    private long ticksActive;

    @Shadow @Nullable protected abstract BlockPos findRandomSpawnPos(int p_221298_1_, int p_221298_2_);

    protected RaidMixin(int numGroups, ServerWorld level, ServerBossInfo raidEvent) {
        this.numGroups = numGroups;
        this.level = level;
        this.raidEvent = raidEvent;
    }

    @Inject(method = "hasBonusWave",at = @At("HEAD"),cancellable = true)
    public void moreWave(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
        cir.cancel(); 
    }

    @Inject(method = "getDefaultNumSpawns",at = @At("HEAD"),cancellable = true)
    public void getDefaultNumSpawns(Raid.WaveMember p_221330_1_, int p_221330_2_, boolean p_221330_3_, CallbackInfoReturnable<Integer> cir){
        if(groupsSpawned >= 14){
            int i =  groupsSpawned - 14;
            cir.setReturnValue(p_221330_1_.spawnsPerWaveBeforeBonus[i] + p_221330_1_.spawnsPerWaveBeforeBonus[7] * 2);
        }else if(groupsSpawned >= 7){
            int i =  groupsSpawned - 7;
            cir.setReturnValue(p_221330_1_.spawnsPerWaveBeforeBonus[i] + p_221330_1_.spawnsPerWaveBeforeBonus[7]);
        }
    }

    @Redirect(method = "findRandomSpawnPos",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor(F)I",ordinal = 0))
    public int modifyRandomPos1(float p_76141_0_){
        return Math.round(MathHelper.sin(p_76141_0_) * 48);
    }

    @Redirect(method = "findRandomSpawnPos",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor(F)I",ordinal = 1))
    public int modifyRandomPos2(float p_76141_0_){
        return Math.round(MathHelper.cos(p_76141_0_) * 48);
    }

    @Redirect(method = "findRandomSpawnPos",at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
    public int modifyRandomPos3(Random instance, int i){
        return 0;
    }

    @Inject(method = "getTotalRaidersAlive",at = @At("HEAD"),cancellable = true)
    public void getTotalRaidersAlive(CallbackInfoReturnable<Integer> cir){
        Raid raid = (Raid) (Object) this;
        cir.setReturnValue(new RaidUtils(raid).getRaiderCount());
    }

    @Inject(method = "getPotentialBonusSpawns",at = @At("HEAD"),cancellable = true)
    public void getAddonSpawns(Raid.WaveMember p_221335_1_, Random p_221335_2_, int p_221335_3_, DifficultyInstance p_221335_4_, boolean p_221335_5_, CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(1);
        cir.cancel();
    }

    @Inject(method = "tick",at = @At("HEAD"))
    public void _tick_as(CallbackInfo ci){
        Raid raid = (Raid) (Object) this;
        RaidEffectExecutor executor = new RaidEffectExecutor(raid);
        executor.onTick();
        World world = raid.level;
        if(ticksActive == 0 && world.isNight()){
            RaidUtils.getRaidAllPlayers(raid).forEach(inf -> {
                executor.announcementPlayer(inf.getPlayer());
            });
        }
    }

    @Inject(method = "updateRaiders",at = @At("HEAD"))
    public void updateRaiders(CallbackInfo ci){
        for(Set<AbstractRaiderEntity> set : this.groupRaiderMap.values()){
            for(AbstractRaiderEntity raider : set){
                if(raider.level == null){
                    Raid raid = (Raid) (Object) this;
                    raid.removeFromRaid(raider,true);
                }
            }
        }
    }

    @Inject(method = "tick",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/raid/Raid;stop()V",ordinal = 2),cancellable = true)
    public void tick(CallbackInfo ci){
        ci.cancel();
    }

    @Inject(method = "updatePlayers",at = @At(value = "HEAD"))
    public void getRaidTime(CallbackInfo ci){
        Set<ServerPlayerEntity> set = Sets.newHashSet(raidEvent.getPlayers());
        set.forEach(e -> {
            e.getCapability(EMCWorldCapability.UTIL).ifPresent(cap -> {
                cap.setRaidTime((int) ticksActive);
            });
        });
    }

    @Inject(method = "tick",at = @At("HEAD"))
    public void fail(CallbackInfo ci){
        Raid raid = (Raid) (Object) this;
        if(raid.status == Raid.Status.ONGOING){
            if(new RaidUtils(raid).getVillager().size() <= 0){
                raid.status = Raid.Status.LOSS;
            }
        }
    }

    @Inject(method = "joinRaid",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/monster/AbstractRaiderEntity;setPos(DDD)V"),cancellable = true)
    public void spawn(int p_221317_1_, AbstractRaiderEntity p_221317_2_, BlockPos p_221317_3_, boolean p_221317_4_, CallbackInfo ci){
        Raid raid = (Raid) (Object) this;
        BlockPos pos = findRandomSpawnPos(0,20);
        if(pos == null){
            raid.stop();
            ci.cancel();
            return;
        }
        p_221317_2_.setPos((double)pos.getX() + 0.5D, (double)pos.getY() + 1.0D, (double)pos.getZ() + 0.5D);
        p_221317_2_.finalizeSpawn(this.level, this.level.getCurrentDifficultyAt(p_221317_3_), SpawnReason.EVENT, (ILivingEntityData)null, (CompoundNBT)null);
        p_221317_2_.applyRaidBuffs(p_221317_1_, false);
        p_221317_2_.setOnGround(true);
        this.level.addFreshEntity(p_221317_2_);
        p_221317_2_.getCapability(EMCWorldCapability.ENTITY_UTIL).ifPresent(IEntityUtilCapability::setRaidEntity);
        ci.cancel();
    }

    @Inject(method = "getNumGroups",at = @At("HEAD"),cancellable = true)
    public void getGroups(Difficulty p_221306_1_, CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(RaidUtils.getRaidWave());
        cir.cancel();
    }
}
