package biggestxuan.emcworld.common.network.toServer;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/05
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.exception.IllegalPacketException;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CostEMCPacket {
    private int index;
    private long emc;

    public CostEMCPacket(long emc,int index){
        this.index = index;
    }

    public static void encode(CostEMCPacket msg, PacketBuffer buf) {
        buf.writeLong(msg.emc);
        buf.writeInt(msg.index);
    }

    public static CostEMCPacket decode(PacketBuffer buf) {
        return new CostEMCPacket(buf.readLong(),buf.readInt());
    }

    public static void handle(CostEMCPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayerEntity player = context.get().getSender();
            if(player != null){
                try{
                    long emc = msg.getEmc();
                    EMCSource<?> source;
                    if(msg.index == 1){
                        source = new EMCSource.LocateEMCSource(emc,player,null,0);
                    }else{
                        source = new EMCSource.TeleportEMCSource(emc,player);
                    }
                    if(emc < 0){
                        throw new IllegalPacketException(player.getScoreboardName());
                    }else{
                        EMCHelper.modifyPlayerEMC(player, source, true);
                    }
                }catch (IllegalPacketException e){
                    EMCWorld.LOGGER.fatal("EMCWorld received a illegal packet!");
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
