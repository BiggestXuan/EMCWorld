package biggestxuan.emcworld.common.network;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/04
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.network.toClient.BuyLotteryClientPacket;
import biggestxuan.emcworld.common.network.toClient.ChangeRotPacket;
import biggestxuan.emcworld.common.network.toServer.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL = "packet";
    public static SimpleChannel HANDLER;

    public static void init(){
        HANDLER = NetworkRegistry.newSimpleChannel(
                EMCWorld.rl("packet_handler"),
                () -> PROTOCOL,
                (version) -> version.equals(PROTOCOL),
                (version) -> version.equals(PROTOCOL));

        int id = 0;
        HANDLER.registerMessage(
                id++,
                LeftClickPacket.class,
                LeftClickPacket::encode,
                LeftClickPacket::decode,
                LeftClickPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                WeaponCoreButtonPacket.class,
                WeaponCoreButtonPacket::encode,
                WeaponCoreButtonPacket::decode,
                WeaponCoreButtonPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                AdminPacket.class,
                AdminPacket::encode,
                AdminPacket::decode,
                AdminPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                SpeedControlPacket.class,
                SpeedControlPacket::encode,
                SpeedControlPacket::decode,
                SpeedControlPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                StaffAttackPacket.class,
                StaffAttackPacket::encode,
                StaffAttackPacket::decode,
                StaffAttackPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                ArcanaDisplayPacket.class,
                ArcanaDisplayPacket::encode,
                ArcanaDisplayPacket::decode,
                ArcanaDisplayPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                LastShieldPacket.class,
                LastShieldPacket::encode,
                LastShieldPacket::decode,
                LastShieldPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                RangeAttackKeyPacket.class,
                RangeAttackKeyPacket::encode,
                RangeAttackKeyPacket::decode,
                RangeAttackKeyPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                PickModeChangePacket.class,
                PickModeChangePacket::encode,
                PickModeChangePacket::decode,
                PickModeChangePacket::handle
        );
        HANDLER.registerMessage(
                id++,
                CostEMCPacket.class,
                CostEMCPacket::encode,
                CostEMCPacket::decode,
                CostEMCPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                LiveModePacket.class,
                LiveModePacket::encode,
                LiveModePacket::decode,
                LiveModePacket::handle
        );
        HANDLER.registerMessage(
                id++,
                ChangeRotPacket.class,
                ChangeRotPacket::encode,
                ChangeRotPacket::decode,
                ChangeRotPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                BuyLotteryPacket.class,
                BuyLotteryPacket::encode,
                BuyLotteryPacket::decode,
                BuyLotteryPacket::handle
        );
        HANDLER.registerMessage(
                id++,
                BuyLotteryClientPacket.class,
                BuyLotteryClientPacket::encode,
                BuyLotteryClientPacket::decode,
                BuyLotteryClientPacket::handle
        );
    }

    public static <T> void sendToServer(T msg){
        HANDLER.sendToServer(msg);
    }

    public static <T> void sendToClient(T msg, ServerPlayerEntity player){
        HANDLER.send(PacketDistributor.PLAYER.with(()->player),msg);
    }
}
