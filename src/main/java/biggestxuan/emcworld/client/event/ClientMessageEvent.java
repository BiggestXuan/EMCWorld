package biggestxuan.emcworld.client.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/08
 */

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientMessageEvent {
    @SubscribeEvent
    public static void ev(ClientChatEvent event){
        /*if(Minecraft.getInstance().player != null && (event.getMessage().contains("it would be cool if you can") || event.getMessage().equals("Thank you " + Minecraft.getInstance().player.getName()))){
            event.setCanceled(true);
        }*/
    }
}
