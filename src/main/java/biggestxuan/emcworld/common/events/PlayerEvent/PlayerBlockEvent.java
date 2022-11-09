package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/04
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.Message;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerBlockEvent {
    @SubscribeEvent
    public static void playerWakeUpEvent(PlayerWakeUpEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.getCommandSenderWorld().isClientSide) return;
        long baseEMC = MathUtils.doubleToLong(MathUtils.getWakeUpBaseCost(player) * MathUtils.difficultyLoss());
        if(baseEMC == 0) return;
        long costEMC = Math.min(baseEMC, EMCHelper.getPlayerEMC(player));
        EMCHelper.modifyPlayerEMC(player,Math.negateExact(costEMC),true);
    }

    @SubscribeEvent
    public static void playerUseHoeEvent(UseHoeEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.getCommandSenderWorld().isClientSide) return;
        long costEMC = MathUtils.doubleToLong(MathUtils.difficultyLoss() * MathUtils.getUseHoeBaseCost(player));
        if(costEMC == 0) return;
        if(EMCHelper.getPlayerEMC(player) >= costEMC){
            EMCHelper.modifyPlayerEMC(player,Math.negateExact(costEMC),true);
        }
        else{
            event.setCanceled(true);
            if(!player.getCommandSenderWorld().isClientSide) return;
            Message.sendMessage(player, EMCWorld.tc("message.evt.usecancel",MathUtils.thousandSign(costEMC)));
        }
    }
    @SubscribeEvent
    public static void playerOpenChestEvent(PlayerContainerEvent.Open event){
        PlayerEntity player = event.getPlayer();
        if(player.getCommandSenderWorld().isClientSide) return;
        long costEMC = Math.min(MathUtils.doubleToLong(MathUtils.getChestBaseCost(player,event.getContainer()) * MathUtils.difficultyLoss()),EMCHelper.getPlayerEMC(player));
        if(costEMC == 0) return;
        EMCHelper.modifyPlayerEMC(player,Math.negateExact(costEMC),true);
    }
    @SubscribeEvent
    public static void playerFillBucketEvent(FillBucketEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.getCommandSenderWorld().isClientSide) return;
        long costEMC = MathUtils.doubleToLong(MathUtils.getFillBucketBaseCost(player) * MathUtils.difficultyLoss());
        if(EMCHelper.getPlayerEMC(player) >= costEMC){
            EMCHelper.modifyPlayerEMC(player,Math.negateExact(costEMC),true);
        }
        else{
            event.setCanceled(true);
            if(!player.getCommandSenderWorld().isClientSide) return;
            Message.sendMessage(player, EMCWorld.tc("message.evt.fillcancel",MathUtils.thousandSign(costEMC)));
        }
    }
    @SubscribeEvent
    public static void playerCraftItemEvent(final PlayerEvent.ItemCraftedEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.getCommandSenderWorld().isClientSide) return;
        long playerEMC = EMCHelper.getPlayerEMC(player);
        long costEMC = Math.min(MathUtils.doubleToLong(MathUtils.getCraftBaseCost(player) * MathUtils.difficultyLoss() * event.getCrafting().getCount() * (1.0 * 64 / event.getCrafting().getMaxStackSize())),playerEMC);
        if(costEMC == 0) return;
        EMCHelper.modifyPlayerEMC(player, Math.negateExact(costEMC),true);
    }
}
