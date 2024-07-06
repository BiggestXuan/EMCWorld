package biggestxuan.emcworld.common.network.toClient;

import biggestxuan.emcworld.common.network.ClientPacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/08
 */

public class BuyLotteryClientPacket {
    public BuyLotteryClientPacket(){}

    public static BuyLotteryClientPacket decode(PacketBuffer bf){
        return new BuyLotteryClientPacket();
    }

    public static void encode(BuyLotteryClientPacket message, PacketBuffer bf){

    }

    public static void handle(BuyLotteryClientPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()-> ()-> ClientPacketHandler.handlePacket(msg,context)));
        context.get().setPacketHandled(true);
    }
}
