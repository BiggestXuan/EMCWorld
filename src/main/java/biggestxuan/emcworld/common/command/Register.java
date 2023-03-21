package biggestxuan.emcworld.common.command;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.command.run.ChangeSponsor;
import biggestxuan.emcworld.common.command.run.DumpPlayerInfo;
import biggestxuan.emcworld.common.command.run.Hand;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Register {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) throws CommandSyntaxException {
        CommandDispatcher<CommandSource> source = event.getDispatcher();
        source.register(
                Commands.literal(EMCWorld.MODID)
                .requires((permission) -> permission.hasPermission(4))
                .then(Commands.literal("dump").then(Commands.argument("target", EntityArgument.player()).executes(new DumpPlayerInfo())))
                .then(Commands.literal("hand").executes(new Hand()))
                .requires((permission) -> permission.hasPermission(0))
                .then(Commands.literal("prefix").executes(new ChangeSponsor()))
        );
    }
}
