package biggestxuan.emcworld.common.compact.ScalingHealth;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/24
 */

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.silentchaos512.scalinghealth.utils.SHDifficulty;

public class DifficultyHelper {
    public static double getLivingDifficulty(LivingEntity player){
        return SHDifficulty.getDifficultyOf(player);
    }
    public static void setPlayerDifficulty(PlayerEntity player,double value){
        SHDifficulty.setSourceDifficulty(player,value);
    }
    public static void addPlayerDifficulty(PlayerEntity player,double value){
        setPlayerDifficulty(player,Math.min(getLivingDifficulty(player)+value,SHDifficulty.maxValue()));
    }
    public static void lossPlayerDifficulty(PlayerEntity player,double value){
        setPlayerDifficulty(player,Math.max(getLivingDifficulty(player)-value,SHDifficulty.minValue()));
    }
    public static double getAreaDifficulty(World world, BlockPos pos){
        return SHDifficulty.areaDifficulty(world,pos);
    }
}
