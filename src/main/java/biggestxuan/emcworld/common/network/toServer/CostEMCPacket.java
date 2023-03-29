package biggestxuan.emcworld.common.network.toServer;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/05
 */

import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.exception.IllegalPacketException;
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
        context.get().enqueueWork(() -> {
            ServerPlayerEntity player = context.get().getSender();
            if(player != null){
                try{
                    long emc = msg.getEmc();
                    if(emc < 0){
                        throw new IllegalPacketException(player.getScoreboardName());
                    }else{
                        EMCHelper.modifyPlayerEMC(player,Math.negateExact(emc),true);
                    }
                }catch (IllegalPacketException e){
                    e.printStackTrace();
                }
            }
        });
        context.get().setPacketHandled(true);
    }

    public long getEmc() {
        return emc;
    }
}
