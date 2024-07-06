package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/08
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.trait.ITrait;
import biggestxuan.emcworld.api.trait.TraitType;
import biggestxuan.emcworld.common.compact.FTBQuests.QuestReward;
import biggestxuan.emcworld.common.compact.ScalingHealth.DifficultyHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.data.LotteryData;
import biggestxuan.emcworld.common.traits.TraitUtils;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import net.darkhax.gamestages.event.GameStageEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
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
                DifficultyHelper.addPlayerDifficulty(player,ds.getDifficulty() * ConfigManager.SUNDRY_DIFFICULTY_STAGE.get());
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
        ItemStack stack = player.getMainHandItem();
        for(ITrait trait : TraitUtils.getStackTraits(stack)){
            if(trait.getTraitType() != TraitType.ARMOR){
                trait.onGetStage(player,stack, stage);
            }
        }
        for(ItemStack s : player.inventory.armor){
            for(ITrait trait : TraitUtils.getStackTraits(s)){
                if(trait.getTraitType() == TraitType.ARMOR){
                    trait.onGetStage(player,stack,stage);
                }
            }
        }
    }
    @SubscribeEvent
    public static void playerRemoveStageEvent(GameStageEvent.Remove event){

    }
}
