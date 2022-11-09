package biggestxuan.emcworld.common.network.SkillPacket;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/02
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class SkillNetworking {
    public static SimpleChannel INSTANCE;
    public static final String name = EMCWorld.MODID+"skill";
    private static int id = 0;

    public static int addID(){
        return ++id;
    }

    public static void registerMessage(){
        INSTANCE = NetworkRegistry.newSimpleChannel(
                EMCWorld.rl("player_skill"),
                () -> name,
                (version) -> version.equals(name),
                (version) -> version.equals(name)
        );
        INSTANCE.messageBuilder(DataPack.class,addID())
                .encoder(DataPack::encode)
                .decoder(DataPack::new)
                .consumer(DataPack::handle)
                .add();
    }
}
