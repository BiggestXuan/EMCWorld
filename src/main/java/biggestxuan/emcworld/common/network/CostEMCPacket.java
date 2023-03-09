package biggestxuan.emcworld.common.network;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/05
 */

import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CostEMCPacket {
    private final long emc;

    public CostEMCPacket(long emc){
        this.emc = emc;
    }

    public static void encode(CostEMCPacket message, PacketBuffer bf){
        bf.writeLong(message.emc);
    }

    public static CostEMCPacket decode(PacketBuffer bf){
        return new CostEMCPacket(bf.readLong());
    }

    public static void handle(CostEMCPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() ->{
            ServerPlayerEntity player = context.get().getSender();
            if(player != null){
                EMCHelper.modifyPlayerEMC(player,Math.negateExact(msg.getEmc()),true);
            }
        });
        context.get().setPacketHandled(true);
    }

    public long getEmc() {
        return emc;
    }
}
