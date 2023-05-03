package biggestxuan.emcworld.common.network.toServer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OfflinePacket {
    public static void encode(OfflinePacket msg, PacketBuffer buf) {}

    public static OfflinePacket decode(PacketBuffer buf) {
        return new OfflinePacket();
    }

    public static void handle(OfflinePacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            ServerPlayerEntity player = ctx.get().getSender();
            if(player != null){
                player.getCapability(EMCWorldCapability.UTIL).ifPresent(c -> c.setOnline(false));
            }
        }
        ctx.get().setPacketHandled(true);
    }
}
