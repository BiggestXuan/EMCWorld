package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/08
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.FTBQuests.QuestReward;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.compact.Projecte.KnowledgeHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import dev.ftb.mods.ftbquests.events.CustomRewardEvent;
import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbquests.quest.reward.CustomReward;
import dev.ftb.mods.ftbquests.quest.reward.Reward;
import dev.ftb.mods.ftbteams.FTBTeams;
import dev.ftb.mods.ftbteams.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.data.Team;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerQuestCompletedEvent {
    @SubscribeEvent
    public static void playerCompletedQuestEvent(CustomRewardEvent event){
        if(event.getPlayer().level.isClientSide) return;
        ServerPlayerEntity player = event.getPlayer();
        if(event.getReward().hasTag("sea_lantern")){
            KnowledgeHelper.addPlayerKnowledge(player, new ItemStack(Blocks.SEA_LANTERN.asItem()));
        }
        if(event.getReward().hasTag("hard")){
            player.addItem(new ItemStack(EWItems.MONEY.get(),1));
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
        long baseGet;
        if(getQuestStage(event.getReward()) == null){
            baseGet = MathUtils.doubleToLong(MathUtils.getQuestCompletedRewardBase(player,event.getReward()) * (1.0 / MathUtils.difficultyLoss()));
        }else{
            baseGet = MathUtils.doubleToLong(MathUtils.getQuestCompletedRewardBase(getQuestStage(event.getReward()),event.getReward()) * (1.0 / MathUtils.difficultyLoss()));
        }
        if(baseGet == 0) return;
        Team team = FTBTeamsAPI.getPlayerTeam(player);
        int amt = team.getMembers().size();
        baseGet = baseGet / (amt <= 0 ? 1 : amt);
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

    @Nullable
    private static String getQuestStage(Reward reward){
        Quest quest = reward.quest;
        if(quest.getQuestChapter() == null || quest.getQuestChapter().group == null){
            return null;
        }
        long id = quest.getQuestChapter().group.id;
        String[] ids = new String[]{
                "35CE8D0AFC937D5D","253AC67EEE892A90","1BED04361D91412D","16A68A44AFA4135F","19E8D4A8DD89F202","7E8ECD616F8A4334","5E8F5E0284CC6998","45BD8B4A7A7B384D"
        };
        String[] stages = new String[]{
                "one","two","three","four","five","six","seven","eight"
        };
        int index = 0;
        for(String s : ids){;
            if(id == Long.parseLong(s,16)){
                return stages[index];
            }
            index++;
        }
        return null;
    }

    private static String getStage(String tag){
        return tag.split("_")[1];
    }
}
