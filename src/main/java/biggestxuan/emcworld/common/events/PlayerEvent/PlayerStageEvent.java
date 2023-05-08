package biggestxuan.emcworld.common.events.PlayerEvent;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/08
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.FTBQuests.QuestReward;
import biggestxuan.emcworld.common.compact.ScalingHealth.DifficultyHelper;
import biggestxuan.emcworld.common.data.LotteryData;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import net.darkhax.gamestages.event.GameStageEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerStageEvent {
    @SubscribeEvent
    public static void playerAddStageEvent(GameStageEvent.Add event){

    }

    @SubscribeEvent
    public static void playerAddedStageEvent(GameStageEvent.Added event){
        PlayerEntity player = event.getPlayer();
        String stage = event.getStageName();
        for(DifficultySetting ds : DifficultySetting.values()){
            if(stage.equals(ds.getGameStage().toLowerCase())){
                DifficultyHelper.addPlayerDifficulty(player,ds.getDifficulty());
                if(player instanceof ServerPlayerEntity){
                    ServerPlayerEntity p = (ServerPlayerEntity) player;
                    MinecraftServer server = p.server;
                    long emc = (long) (ds.getFtbBase() * QuestReward.HARD.getBaseEMC()) * 100L;
                    LotteryData data = LotteryData.getInstance(server);
                    data.setStoredEMC(data.getStoredEMC()+emc);
                }
                break;
            }
        }
    }
    @SubscribeEvent
    public static void playerRemoveStageEvent(GameStageEvent.Remove event){

    }
}
