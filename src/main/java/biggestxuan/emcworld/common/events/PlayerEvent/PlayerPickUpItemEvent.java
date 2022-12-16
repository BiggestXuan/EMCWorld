package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2022/08/23
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.ICostEMCItem;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.darkhax.itemstages.Restriction;
import net.darkhax.itemstages.RestrictionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerPickUpItemEvent {
    @SubscribeEvent
    public static void playerPickUpItemEvent(EntityItemPickupEvent event) {
        PlayerEntity player = event.getPlayer();
        ItemStack item = event.getItem().getItem();
        if (player.getCommandSenderWorld().isClientSide) return;
        int amt = item.getCount() * (64 / item.getMaxStackSize());
        if(item.getMaxStackSize() >= 65) amt *= 1145141919;
        double rate = 1d;
        if (item.getItem() instanceof ICostEMCItem) {
            rate = ((ICostEMCItem) item.getItem()).getEMCCostRate();
        }
        final Restriction restriction = RestrictionManager.INSTANCE.getRestriction(player, item);
        if (restriction != null && restriction.shouldPreventPickup()) {
            return;
        }
        if(full(player,item)) return;
        long costEMC = MathUtils.doubleToLong(amt * rate * MathUtils.difficultyLoss() * MathUtils.getPickUpItemBaseCost(player));
        if (costEMC == 0) return;
        if (EMCHelper.getPlayerEMC(player) < costEMC) {
            event.setCanceled(true);
            Message.MessageDisplay(player, EMCWorld.tc("message.evt.pickupcancel", MathUtils.format(costEMC)));
        } else {
            EMCHelper.modifyPlayerEMC(player, Math.negateExact(costEMC), true);
        }
    }

    private static boolean full(PlayerEntity player,ItemStack stack){
        for(ItemStack s : player.inventory.items){
            if(s.equals(ItemStack.EMPTY)){
                return false;
            }
            if(s.getItem().equals(stack.getItem()) && s.getCount() + stack.getCount() <= s.getMaxStackSize()){
                return false;
            }
        }
        return true;
    }
}
