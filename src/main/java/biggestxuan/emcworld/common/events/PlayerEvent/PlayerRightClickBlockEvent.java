package biggestxuan.emcworld.common.events.PlayerEvent;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/06
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.Message;
import hellfirepvp.astralsorcery.common.block.tile.BlockAltar;
import hellfirepvp.astralsorcery.common.block.tile.BlockRitualPedestal;
import mythicbotany.alfheim.Alfheim;
import net.minecraft.block.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerRightClickBlockEvent {
    @SubscribeEvent
    public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event){
        BlockState state = event.getWorld().getBlockState(event.getPos());
        if(event.getWorld().isClientSide) return;
        if(state.getBlock() instanceof BlockAltar || state.getBlock() instanceof BlockRitualPedestal){
            if(!event.getWorld().dimension().equals(Alfheim.DIMENSION)){
                event.setResult(Event.Result.DENY);
                Message.sendMessage(event.getPlayer(),EMCWorld.tc("message.altar.cancel"));
                event.setCanceled(true);
            }
        }
    }
}
