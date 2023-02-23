package biggestxuan.emcworld.common.events;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/04
 */

import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class commandEvent {
    @SubscribeEvent
    public void useCommandEvent(CommandEvent event){
        String name = event.getParseResults().getReader().getString();
        if(name.contains("setemc")){
            event.setCanceled(true);
        }
        if(name.contains("tp") || name.contains("teleport")){
            //event.setCanceled(true);
        }
    }
}
