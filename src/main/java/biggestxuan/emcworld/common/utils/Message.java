package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/31
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class Message {
    public static void MessageDisplay(PlayerEntity player,TranslationTextComponent key){
        player.displayClientMessage(key,true);
    }
    public static void sendMessage(PlayerEntity player, ITextComponent key){
        player.displayClientMessage(key,false);
    }
    public static void sendMessageToThisWorldPlayer(Entity entity,TranslationTextComponent key){
        World world = entity.level;
        List<? extends PlayerEntity> thisWorldPlayer = world.players();
        for(PlayerEntity player:thisWorldPlayer){
            sendMessage(player,key);
        }
    }
    public static void sendMessageToAllPlayer(MinecraftServer server,TranslationTextComponent key){
        PlayerList players = server.getPlayerList();
        for(ServerPlayerEntity player : players.getPlayers()){
            player.displayClientMessage(key,false);
        }
    }
    public static void sendMessageToAllPlayer(Entity entity,TranslationTextComponent key){
        MinecraftServer server = entity.getCommandSenderWorld().getServer();
        assert server != null;
        sendMessageToAllPlayer(server,key);
    }
    public static <T> void addEMCMessage(PlayerEntity player,T emc){
        if(emc instanceof Long || emc instanceof String){
            if(String.valueOf(emc).equals(String.valueOf(0))){
                return;
            }
            String output = MathUtils.format(String.valueOf(emc));
            player.displayClientMessage(EMCWorld.tc("message.evt.addemc",output),true);
        }
    }
    public static <T> void LossEMCMessage(PlayerEntity player,T emc){
        if(emc instanceof Long || emc instanceof String){
            if(String.valueOf(emc).equals(String.valueOf(0))){
                return;
            }
            String output = MathUtils.format(String.valueOf(emc));
            player.displayClientMessage(EMCWorld.tc("message.evt.lossemc",output),true);
        }
    }
    public static <T> void DisplayEMCMessage(PlayerEntity player,T emc){
        if(emc instanceof Long || emc instanceof String){
            if(String.valueOf(emc).equals(String.valueOf(0))){
                return;
            }
            String output = MathUtils.format(String.valueOf(emc));
            player.displayClientMessage(EMCWorld.tc("message.evt.display",output),true);
        }
    }
}
