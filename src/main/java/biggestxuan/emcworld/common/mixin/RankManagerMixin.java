package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.common.raid.RaidEffectExecutor;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.raid.RaidManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

@Mixin(RaidManager.class)
public class RankManagerMixin {
    @Inject(method = "createOrExtendRaid",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/raid/RaidManager;getOrCreateRaid(Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/world/raid/Raid;"))
    public void _inject(ServerPlayerEntity p_215170_1_, CallbackInfoReturnable<Raid> cir){
        Raid raid = cir.getReturnValue();
        if(raid != null){
            new RaidEffectExecutor(raid).announcementPlayer(p_215170_1_);
        }
    }
}
