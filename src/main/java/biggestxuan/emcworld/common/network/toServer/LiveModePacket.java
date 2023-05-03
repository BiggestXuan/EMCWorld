package biggestxuan.emcworld.common.network.toServer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class LiveModePacket {
    public static void encode(LiveModePacket msg, PacketBuffer buf) {}

    public static LiveModePacket decode(PacketBuffer buf) {
        return new LiveModePacket();
    }

    public static void handle(LiveModePacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(ctx.get().getSender());
            cap.setLiveMode(!cap.getLiveMode());
            String text;
            if(cap.getLiveMode()){
                text = "message.cr.2";
            }else{
                text = "message.cr.3";
            }
            ServerPlayerEntity SP = ctx.get().getSender();
            if(SP != null){
               Message.sendMessage(SP, EMCWorld.tc(text));
            }
        }
        ctx.get().setPacketHandled(true);
    }
}
