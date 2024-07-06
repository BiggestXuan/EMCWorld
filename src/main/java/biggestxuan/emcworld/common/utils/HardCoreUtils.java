package biggestxuan.emcworld.common.utils;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/08/20
 */

@EMCWorldSince("1.0.4")
public final class HardCoreUtils {
    public static boolean isHardCoreMode(MinecraftServer server){
        return server.isHardcore();
    }

    public static boolean isHardCoreMode(ServerPlayerEntity player){
        return isHardCoreMode(player.getServer());
    }
}
