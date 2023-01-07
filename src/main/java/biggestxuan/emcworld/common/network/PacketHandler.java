package biggestxuan.emcworld.common.network;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/04
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraftforge.fml.network.NetworkRegistry;
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
    }

    public static <T> void sendToServer(T msg){
        HANDLER.sendToServer(msg);
    }
}
