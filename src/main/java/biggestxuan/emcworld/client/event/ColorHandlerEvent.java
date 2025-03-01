package biggestxuan.emcworld.client.event;

import biggestxuan.emcworld.client.EMCCoreItemColor;
import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        value = {Dist.CLIENT},
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ColorHandlerEvent {
    @SubscribeEvent
    public static void colorEvent(net.minecraftforge.client.event.ColorHandlerEvent.Item event){
        event.getItemColors().register(new EMCCoreItemColor(), EWItems.EMC_TRAIT_CORE.get());
    }
}
