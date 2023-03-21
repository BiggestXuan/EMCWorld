package biggestxuan.emcworld.common.network.toServer;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ArcanaDisplayPacket {
    public static void encode(ArcanaDisplayPacket msg, PacketBuffer buf) {}

    public static ArcanaDisplayPacket decode(PacketBuffer buf) {
        return new ArcanaDisplayPacket();
    }

    public static void handle(ArcanaDisplayPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(ctx.get().getSender());
            cap.setShowArcana(!cap.showArcana());
        }
        ctx.get().setPacketHandled(true);
    }
}
