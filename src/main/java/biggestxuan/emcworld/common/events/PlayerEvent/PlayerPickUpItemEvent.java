package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2022/08/23
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.ICostEMCItem;
import biggestxuan.emcworld.api.item.equipment.IEMCGodWeaponLevel;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.Projecte.KnowledgeHelper;
import biggestxuan.emcworld.common.exception.EMCWorldCommonException;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.UnknownPouchUtils;
import net.darkhax.itemstages.Restriction;
import net.darkhax.itemstages.RestrictionManager;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerPickUpItemEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void playerPickUpItemEvent(EntityItemPickupEvent event) {
        PlayerEntity player = event.getPlayer();
        ItemStack item = event.getItem().getItem();
        if (player.getCommandSenderWorld().isClientSide) return;
        int amt = item.getCount() * (64 / item.getMaxStackSize());
        if(item.getMaxStackSize() >= 65) amt *= (EMCWorld.HOMO << 18);
        double rate = 1d;
        IUtilCapability c = player.getCapability(EMCWorldCapability.UTIL).orElseThrow(EMCWorldCommonException::new);
        if(c.getPickMode() == 1 && item.getMaxStackSize() <= 64){
            if(!KnowledgeHelper.itemInAlchemyBag(item,player,DyeColor.WHITE,false)){
                event.setCanceled(true);
                return;
            }
        }
        if(c.getPickMode() == 2 && item.getMaxStackSize() <= 64){
            if(KnowledgeHelper.itemInAlchemyBag(item,player,DyeColor.BLACK,false)){
                event.setCanceled(true);
                if(!(item.getItem() instanceof IEMCGodWeaponLevel)){
                    event.getItem().remove();
                }
                return;
            }
        }
        if (item.getItem() instanceof ICostEMCItem) {
            rate = ((ICostEMCItem) item.getItem()).getEMCCostRate();
        }
        final Restriction restriction = RestrictionManager.INSTANCE.getRestriction(player, item);
        if (restriction != null && restriction.shouldPreventPickup()) {
            ItemEntity entity = event.getItem();
            ItemStack ret = UnknownPouchUtils.putItem(player,entity.getItem());
            if(ret.equals(ItemStack.EMPTY)){
                entity.remove();
                Message.sendMessage(player,EMCWorld.tc("message.unknown_pouch.success",entity.getItem().toString()));
            }else{
                entity.setItem(ret);
                Message.sendMessage(player,EMCWorld.tc("message.unknown_pouch.fail"));
            }
            return;
        }
        if(full(player,item)) return;
        long costEMC = MathUtils.doubleToLong(amt * rate * MathUtils.difficultyLoss() * MathUtils.getPickUpItemBaseCost(player));
        if (costEMC == 0) return;
        if (EMCHelper.getPlayerEMC(player) < costEMC) {
            event.setCanceled(true);
            Message.MessageDisplay(player, EMCWorld.tc("message.evt.pickupcancel", MathUtils.format(costEMC)));
        } else {
            EMCHelper.modifyPlayerEMC(player, new EMCSource.PickItemEMCSource(Math.negateExact(costEMC),player,event.getItem(),0), true);
        }
    }

    public static boolean full(PlayerEntity player,ItemStack stack){
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
