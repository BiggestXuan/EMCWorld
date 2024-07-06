package biggestxuan.emcworld.common.command.run;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/31
 */

import com.blamejared.crafttweaker.impl.network.PacketHandler;
import com.blamejared.crafttweaker.impl.network.messages.MessageCopy;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.network.PacketDistributor;

public class Hand implements Command<CommandSource> {
    @Override
    public int run(CommandContext<CommandSource> context) {
        try{
            ServerPlayerEntity player = context.getSource().getPlayerOrException();
            ItemStack item = player.getMainHandItem();
            if(item.getItem().getRegistryName() != null){
                String feedback = "EMCWorld.getItem(\""+item.getItem().getRegistryName().toString()+"\")";
                PacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(()->player),new MessageCopy(feedback));
            }
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
