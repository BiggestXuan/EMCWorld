package biggestxuan.emcworld.common.network.toClient;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/23
 */

import biggestxuan.emcworld.common.network.ClientPacketHandler;
import biggestxuan.emcworld.common.network.toServer.AdminPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ChangeRotPacket {
    private final float x;
    private final float y;

    public ChangeRotPacket(float x,float y){
        this.x = x;
        this.y = y;
    }

    public static ChangeRotPacket decode(PacketBuffer bf){
        return new ChangeRotPacket(bf.readFloat(),bf.readFloat());
    }

    public static void encode(ChangeRotPacket message, PacketBuffer bf){
        bf.writeFloat(message.getX());
        bf.writeFloat(message.getY());
    }

    public static void handle(ChangeRotPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()-> ()-> ClientPacketHandler.handlePacket(msg,context)));
        context.get().setPacketHandled(true);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
