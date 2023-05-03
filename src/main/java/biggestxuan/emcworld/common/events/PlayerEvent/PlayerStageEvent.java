package biggestxuan.emcworld.common.events.PlayerEvent;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/08
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.ScalingHealth.DifficultyHelper;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import net.darkhax.gamestages.event.GameStageEvent;
import net.minecraft.entity.player.PlayerEntity;
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
                break;
            }
        }
    }
    @SubscribeEvent
    public static void playerRemoveStageEvent(GameStageEvent.Remove event){

    }
}
