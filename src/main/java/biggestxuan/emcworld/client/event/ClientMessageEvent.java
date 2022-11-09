package biggestxuan.emcworld.client.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.Mod;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/08
 */

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientMessageEvent {
    public static void ev(ClientChatEvent event){
        if(event.getMessage().contains("!it would be cool if you can")){
            event.setCanceled(true);
        }
    }
}
