package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/23
 */

import biggestxuan.emcworld.common.utils.RaidUtils;
import com.google.common.collect.Maps;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Shadow
    private long ticksActive;

    protected RaidMixin(int numGroups, ServerWorld level) {
        this.numGroups = numGroups;
        this.level = level;
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
            List<Raid.WaveMember> members = new ArrayList<>(Arrays.asList(Raid.WaveMember.values()));
            for(Raid.WaveMember m : members){
                cir.setReturnValue(m.spawnsPerWaveBeforeBonus[i] + m.spawnsPerWaveBeforeBonus[7] * 2);
                cir.cancel();
            }
        }else if(groupsSpawned >= 7){
            int i =  groupsSpawned - 7;
            List<Raid.WaveMember> members = new ArrayList<>(Arrays.asList(Raid.WaveMember.values()));
            for(Raid.WaveMember m : members){
                cir.setReturnValue(m.spawnsPerWaveBeforeBonus[i] + m.spawnsPerWaveBeforeBonus[7]);
                cir.cancel();
            }
        }
    }

    @Inject(method = "getTotalRaidersAlive",at = @At("HEAD"),cancellable = true)
    public void getTotalRaidersAlive(CallbackInfoReturnable<Integer> cir){
        Raid raid = (Raid) (Object) this;
        cir.setReturnValue(new RaidUtils(raid).getRaiderCount());
    }

    @Inject(method = "getPotentialBonusSpawns",at = @At("HEAD"),cancellable = true)
    public void getAddonSpawns(Raid.WaveMember p_221335_1_, Random p_221335_2_, int p_221335_3_, DifficultyInstance p_221335_4_, boolean p_221335_5_, CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(0);
        cir.cancel();
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

    @Inject(method = "joinRaid",at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/monster/AbstractRaiderEntity;setPos(DDD)V"),cancellable = true)
    public void spawn(int p_221317_1_, AbstractRaiderEntity p_221317_2_, BlockPos p_221317_3_, boolean p_221317_4_, CallbackInfo ci){
        Raid raid = (Raid) (Object) this;
        BlockPos pos = new RaidUtils(raid).getRandomPos(p_221317_3_);
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
        ci.cancel();
    }

    @Inject(method = "getNumGroups",at = @At("HEAD"),cancellable = true)
    public void getGroups(Difficulty p_221306_1_, CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(RaidUtils.getRaidWave());
        cir.cancel();
    }
}
