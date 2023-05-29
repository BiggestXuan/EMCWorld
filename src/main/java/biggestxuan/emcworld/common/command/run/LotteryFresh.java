package biggestxuan.emcworld.common.command.run;

import biggestxuan.emcworld.common.data.LotteryData;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/28
 */

public class LotteryFresh implements Command<CommandSource> {
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        MinecraftServer server = context.getSource().getServer();
        LotteryData.getInstance(server).openLottery();
        return 0;
    }
}
