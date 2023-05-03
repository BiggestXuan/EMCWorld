package biggestxuan.emcworld.common.events;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/08
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.EMCLog.EMCWriter;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID)
public class serverEvent {
    @SubscribeEvent
    public static void ServerStartEvent(FMLServerStartedEvent event){
        EMCWriter.Init();
    }
}
