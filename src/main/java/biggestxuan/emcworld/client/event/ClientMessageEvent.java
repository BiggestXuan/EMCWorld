package biggestxuan.emcworld.client.event;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.config.ClientConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/08
 */

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientMessageEvent {
    @SubscribeEvent
    public static void ev(RenderGameOverlayEvent event){
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(player != null && event.getType() == RenderGameOverlayEvent.ElementType.CHAT && !player.isDeadOrDying()){
            try{
                IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(player);
                if(cap.getLiveMode()){
                    event.setCanceled(true);
                }
                //EMCWorld.LOGGER.info(cap.getLiveMode());
            }catch (NullPointerException e){
                EMCWorld.LOGGER.error("Can't get player's capability!");
            }
        }
    }
}
