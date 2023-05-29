package biggestxuan.emcworld.common.command.run;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/05
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

public class ChangeSponsor implements Command<CommandSource> {
    @Override
    public int run(CommandContext<CommandSource> context) {
        try {
            ServerPlayerEntity player = context.getSource().getPlayerOrException();
            MinecraftServer server = player.server;
            IUtilCapability c = EMCWorldAPI.getInstance().getUtilCapability(player);
            if(!c.getOnline()){
                Message.sendMessage(player,EMCWorld.tc("message.sponsor.change_offline"));
                return 0;
            }
            if(c.getLevel()[0] == 0){
                Message.sendMessage(player,EMCWorld.tc("message.sponsor.change_deny"));
                return 0;
            }
            List<Integer> i = new ArrayList<>();
            for(int p : c.getLevel()){
                i.add(p);
            }
            int index = i.indexOf(c.getDisplayIndex());
            int k = ++index;
            if(k >= i.size() && i.size() >= 1){
                k = i.get(0);
            }else k = i.get(index);
            c.setDisplayIndex(k);
            Message.sendMessage(player,EMCWorld.tc("message.sponsor.change_success",Sponsors.getSponsorName(k)));
        }catch (CommandSyntaxException e){
            e.printStackTrace();
        }
        return 0;
    }
}
