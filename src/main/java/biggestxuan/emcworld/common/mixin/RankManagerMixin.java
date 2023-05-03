package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import biggestxuan.emcworld.common.compact.Champions.Rank.RankEnum;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.champions.common.rank.Rank;
import top.theillusivec4.champions.common.rank.RankManager;

import java.util.TreeMap;

@Mixin(RankManager.class)
public abstract class RankManagerMixin {
    @Shadow(remap = false)
    @Final
    private static TreeMap<Integer, Rank> RANKS;

    @Inject(method = "buildRanks",at = @At("RETURN"),remap = false)
    private static void buildRank(CallbackInfo ci){
        for(RankEnum r : RankEnum.values()){
            Rank rank = r.getRank();
            RANKS.put(rank.getTier(),rank);
        }
    }
}
