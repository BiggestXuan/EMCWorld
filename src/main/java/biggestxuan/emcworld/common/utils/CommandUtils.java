package biggestxuan.emcworld.common.utils;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/04
 */

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nonnull;
import java.util.Optional;

@SuppressWarnings("unused")
public class CommandUtils {
    private final Optional<MinecraftServer> server;
    private ServerPlayerEntity serverPlayer;

    public CommandUtils(@Nonnull Entity entity){
        server = Optional.ofNullable(entity.getServer());
        serverPlayer = null;
    }

    public CommandUtils(@Nonnull ServerPlayerEntity serverPlayer){
        this((Entity) serverPlayer);
        this.serverPlayer = serverPlayer;
    }

    public void awardAdvancement(Advancement adv){
        if(serverPlayer != null){
            AdvancementProgress advancementprogress = serverPlayer.getAdvancements().getOrStartProgress(adv);
            if(advancementprogress.isDone()){
                return;
            }
            server.ifPresent(s -> executeCommand(s,"advancement grant "+serverPlayer.getName().getString()+" only "+adv.getId().toString()));
        }
    }

    private static void executeCommand(MinecraftServer server,String command){
        server.getCommands().performCommand(server.createCommandSourceStack(),command);
    }
}
