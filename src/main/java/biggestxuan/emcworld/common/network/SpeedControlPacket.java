package biggestxuan.emcworld.common.network;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/28
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.client.Message;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.events.PlayerEvent.PlayerTickEvent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SpeedControlPacket {
    public static void encode(SpeedControlPacket msg, PacketBuffer buf) {}

    public static SpeedControlPacket decode(PacketBuffer buf) {
        return new SpeedControlPacket();
    }

    public static void handle(SpeedControlPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            ServerPlayerEntity player = ctx.get().getSender();
            if(player == null){
                ctx.get().setPacketHandled(true);
                return;
            }
            IUtilCapability util = player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new);
            float maxSpeed = PlayerTickEvent.getPlayerMaxSpeed(player);
            float nowSpeed = util.getSpeed();
            if(nowSpeed < maxSpeed){
                util.setSpeed(Math.min(nowSpeed + maxSpeed / 8f,maxSpeed));
            }else{
                util.setSpeed(0f);
            }
            Message.sendMessage(player, EMCWorld.tc(EMCWorld.PREFIX).append(EMCWorld.tc("message.key_speed",String.format("%.2f",util.getSpeed()))));
        }
        ctx.get().setPacketHandled(true);
    }
}
