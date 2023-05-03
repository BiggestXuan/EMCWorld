package biggestxuan.emcworld.common.compact.GameStage;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/03
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.Message;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public class GameStageManager {
    public static void syncStage(PlayerEntity player){
        if(player instanceof ServerPlayerEntity){
            GameStageHelper.syncPlayer((ServerPlayerEntity) player);
        }
    }

    public static boolean hasStage(PlayerEntity player,String stage){
        return GameStageHelper.hasStage(player,stage);
    }

    public static void addStage(PlayerEntity player,String stage){
        if(player instanceof ServerPlayerEntity){
            GameStageHelper.addStage((ServerPlayerEntity) player,stage);
            Message.sendMessage(player, EMCWorld.tc("message.stage.unlock",stage));
            syncStage(player);
        }
    }

    public static void removeStage(PlayerEntity player,String name){
        if(player instanceof ServerPlayerEntity){
            GameStageHelper.removeStage((ServerPlayerEntity) player,name);
            syncStage(player);
        }
    }
}
