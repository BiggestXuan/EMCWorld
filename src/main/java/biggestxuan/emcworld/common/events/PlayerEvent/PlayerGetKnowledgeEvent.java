package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/25
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWItems;
import moze_intel.projecte.api.event.PlayerAttemptLearnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerGetKnowledgeEvent {
    @SubscribeEvent
    public static void playerGetKnowledgeEvent(PlayerAttemptLearnEvent event){
        if(event.getSourceInfo().getItem().equals(EWItems.VOUCHER.get())){
            event.setCanceled(true);
        }
    }
}
