package biggestxuan.emcworld.common.network;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/04
 */

import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodWeapon;
import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class LeftClickPacket {
    public static void encode(LeftClickPacket msg, PacketBuffer buf) {}

    public static LeftClickPacket decode(PacketBuffer buf) {
        return new LeftClickPacket();
    }

    public static void handle(LeftClickPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            ctx.get().enqueueWork(() -> ((BaseEMCGodWeapon) EWItems.ICE_SWORD.get()).spawnManaBurst(ctx.get().getSender()));
        }
        ctx.get().setPacketHandled(true);
    }
}
