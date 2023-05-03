package biggestxuan.emcworld.common.network.toServer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/04
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PickModeChangePacket {
    public static void encode(PickModeChangePacket message, PacketBuffer bf){
    }

    public static PickModeChangePacket decode(PacketBuffer bf){
        return new PickModeChangePacket();
    }

    public static void handle(PickModeChangePacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(()->{
            ServerPlayerEntity player = context.get().getSender();
            if(player != null){
                player.getCapability(EMCWorldCapability.UTIL).ifPresent(c -> {
                    int mode = c.getPickMode();
                    if(++mode > 2){
                        c.setPickMode(0);
                    }else c.setPickMode(mode);
                    String m;
                    switch (c.getPickMode()){
                        case 1:
                            m = "message.pickmode.whitelist";
                            break;
                        case 2:
                            m = "message.pickmode.blacklist";
                            break;
                        default:
                            m = "message.pickmode.all";
                            break;
                    }
                    Message.sendMessage(player, EMCWorld.tc("message.pickmode.setting").append(EMCWorld.tc(m).getString()));
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}
