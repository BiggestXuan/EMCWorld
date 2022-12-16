package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/08
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.FTBQuests.QuestReward;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.compact.Projecte.KnowledgeHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import dev.ftb.mods.ftbquests.events.CustomRewardEvent;
import dev.ftb.mods.ftbquests.quest.reward.CustomReward;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerQuestCompletedEvent {
    @SubscribeEvent
    public static void playerCompletedQuestEvent(CustomRewardEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.getCommandSenderWorld().isClientSide) return;
        if(event.getReward().hasTag("sea_lantern")){
            KnowledgeHelper.addPlayerKnowledge(player, new ItemStack(Blocks.SEA_LANTERN.asItem()));
        }
        for(String tag:event.getReward().getTags()){
            if(tag.contains("stage_")){
                if(ConfigManager.FREE_MODE.get()){
                    Message.sendMessage(player,EMCWorld.tc("message.evt.stage.cancel"));
                }else GameStageManager.addStage(player,getStage(tag));
            }
        }
        if(event.getReward().hasTag("first_final")){
            if(getActDiff(player) >= 1){
                player.addItem(new ItemStack(EWItems.UNIVERSAL_BALL.get()));
                send(player,"message.doing_diff1");
            }else final_pack(player,"message.final_diff1");
        }
        if(event.getReward().hasTag("second_final")){
            if(getActDiff(player) >= 2){
                player.addItem(new ItemStack(EWItems.EMC_CORE.get()));
                send(player,"message.doing_diff2");
            }else final_pack(player,"message.final_diff2");
        }
        if(event.getReward().hasTag("third_final")){
            if(getActDiff(player) == 3){
                player.addItem(new ItemStack(EWItems.INFINITY_CATALYST.get()));
                send(player,"message.doing_diff3");
            }else final_pack(player,"message.final_diff3");
        }
        if(event.getReward().hasTag("fourth_final")){
            if(getActDiff(player) == 3){
                final_pack(player,"message.final_diff4");
            }
        }
        long baseGet = MathUtils.doubleToLong(MathUtils.getQuestCompletedRewardBase(player,event.getReward()) * (1.0 / MathUtils.difficultyLoss()));
        if(baseGet == 0) return;
        EMCHelper.modifyPlayerEMC(player,baseGet,true);
        for(QuestReward obj : QuestReward.values()){
            CustomReward reward = event.getReward();
            if(reward.hasTag(obj.getTag())){
                Message.sendMessage(player, EMCWorld.tc("message.evt.questcompleted",obj.getTag(),MathUtils.format(baseGet)));
                break;
            }
        }
    }

    private static double getActDiff(PlayerEntity player){
        return Math.min(ConfigManager.DIFFICULTY.get(),player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new).getDifficulty());
    }

    private static void send(PlayerEntity player, String info){
        Message.sendMessage(player,EMCWorld.tc(info));
    }

    private static void final_pack(PlayerEntity player,String info){
        send(player,"message.final");
        send(player,info);
        send(player,"");
        Message.sendMessage(player,EMCWorld.tc("message.final.thanks",player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new).getDifficulty()));
    }

    private static String getStage(String tag){
        return tag.split("_")[1];
    }
}
