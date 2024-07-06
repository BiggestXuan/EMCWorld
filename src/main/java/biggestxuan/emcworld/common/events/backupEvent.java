package biggestxuan.emcworld.common.events;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/27
 */

import biggestxuan.emcworld.EMCWorld;
import com.feed_the_beast.mods.ftbbackups.BackupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID)
public class backupEvent {
    @SubscribeEvent
    public static void pre(BackupEvent.Pre event){
        EMCWorld.isBackingUp = true;
    }

    @SubscribeEvent
    public static void post(BackupEvent.Post event){
        EMCWorld.isBackingUp = false;
    }

    @SubscribeEvent
    public static void close(FMLServerStoppingEvent event){
        EMCWorld.isBackingUp = false;
    }
}
