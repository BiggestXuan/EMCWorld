package biggestxuan.emcworld.common.network.toClient.UtilPacket;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/02
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class UtilNetworking {
    public static SimpleChannel INSTANCE;
    public static final String name = EMCWorld.MODID+"util";
    private static int id = 0;

    public static int addID(){
        return ++id;
    }

    public static void registerMessage(){
        INSTANCE = NetworkRegistry.newSimpleChannel(
                EMCWorld.rl("player_util"),
                () -> name,
                (version) -> version.equals(name),
                (version) -> version.equals(name)
        );
        INSTANCE.messageBuilder(UtilDataPack.class,addID())
                .encoder(UtilDataPack::encode)
                .decoder(UtilDataPack::new)
                .consumer(UtilDataPack::handle)
                .add();
    }
}
