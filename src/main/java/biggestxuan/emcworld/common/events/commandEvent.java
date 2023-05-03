package biggestxuan.emcworld.common.events;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/04
 */

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class commandEvent {
    @SubscribeEvent
    public void useCommandEvent(CommandEvent event){
        String name = event.getParseResults().getReader().getString();
        CommandSource source = event.getParseResults().getContext().getSource();
        if(name.contains("setemc")){
            event.setCanceled(true);
        }
        if(source.getEntity() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) source.getEntity();
            if(!player.isCreative() && (name.contains("tp") || name.contains("teleport") || name.contains("execute"))){
                event.setCanceled(true);
            }
        }
    }
}
