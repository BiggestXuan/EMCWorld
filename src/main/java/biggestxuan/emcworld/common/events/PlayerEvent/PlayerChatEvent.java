package biggestxuan.emcworld.common.events.PlayerEvent;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/18
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerChatEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void playerChatEvent(ServerChatEvent event){
        LazyOptional<IUtilCapability> sponsorCap = event.getPlayer().getCapability(EMCWorldCapability.UTIL);
        MinecraftServer server = event.getPlayer().server;
        assert server != null;
        sponsorCap.ifPresent((cap) -> {
            String name = event.getUsername();
            String saying = event.getMessage();
            int level = cap.getDisplayIndex();
            if(!cap.getOnline()) return;
            switch (level){
                case 1:
                    event.setComponent(EMCWorld.tc("message.chat.base1",name,saying));
                    break;
                case 2:
                    event.setComponent(EMCWorld.tc("message.chat.base2",name,saying));
                    break;
                case 3:
                case 4:
                case 5:
                    event.setComponent(EMCWorld.tc("message.chat.base3",name,saying));
                    break;
                case 6:
                    event.setComponent(EMCWorld.tc("message.chat.tulye",name,saying));
                    break;
                default:
                    event.setComponent(EMCWorld.tc("message.chat.base",name,saying));
                    break;
            }
        });
    }
}
