package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/23
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.Message;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.api.item.ICostEMCItem;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerPickUpItemEvent {
    @SubscribeEvent
    public static void playerPickUpItemEvent(EntityItemPickupEvent event){
        PlayerEntity player = event.getPlayer();
        ItemStack item = event.getItem().getItem();
        if(player.getCommandSenderWorld().isClientSide) return;
        int amt = item.getCount() * (64 / item.getMaxStackSize());
        double rate = 1d;
        if(item.getItem() instanceof ICostEMCItem){
            rate = ((ICostEMCItem) item.getItem()).getEMCCostRate();
        }
        long costEMC = MathUtils.doubleToLong(amt * rate *  MathUtils.difficultyLoss() * MathUtils.getPickUpItemBaseCost(player));
        if(costEMC == 0) return;
        if(EMCHelper.getPlayerEMC(player) < costEMC){
            event.setCanceled(true);
            Message.MessageDisplay(player, EMCWorld.tc("message.evt.pickupcancel",MathUtils.thousandSign(costEMC)));
        }
        else{
            EMCHelper.modifyPlayerEMC(player,Math.negateExact(costEMC),true);
        }
    }
}
