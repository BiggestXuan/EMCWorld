package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
/*import top.theillusivec4.champions.common.rank.Rank;
import top.theillusivec4.champions.common.rank.RankManager;
import top.theillusivec4.champions.common.util.ChampionBuilder;*/

//@Mixin(ChampionBuilder.class)
public abstract class ChampionBuilderMixin {
    /*@Redirect(
            method = "spawn",
            at = @At(value = "INVOKE", target = "Ltop/theillusivec4/champions/common/util/ChampionBuilder;createRank(Lnet/minecraft/entity/LivingEntity;)Ltop/theillusivec4/champions/common/rank/Rank;"),
            remap = false
    )
    private static Rank getRank(LivingEntity living){
        while (true){
            Rank rank = ChampionBuilder.createRank(living);
            double index = MathUtils.getRangePlayerAverageIndex(living,64);
            if(canReachRank(index,rank)){
                return rank;
            }
        }
    }

    private static boolean canReachRank(double avg,Rank rank){
        int tier = rank.getTier();
        if(avg < 3 && tier >= 4){
            return false;
        }
        if(avg < 4 && tier >= 5){
            return false;
        }
        if(avg < 5 && tier >= 6){
            return false;
        }
        if(avg < 6 && tier >= 7){
            return false;
        }
        return !(avg < 7) || tier < 8;
    }*/
}
