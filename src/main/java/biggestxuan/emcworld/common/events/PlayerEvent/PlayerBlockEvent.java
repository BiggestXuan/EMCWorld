package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/04
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.DaggerItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.WarHammerItem;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerBlockEvent {
    @SubscribeEvent
    public static void playerWakeUpEvent(PlayerWakeUpEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide) return;
        long baseEMC = MathUtils.doubleToLong(MathUtils.getWakeUpBaseCost(player) * MathUtils.difficultyLoss());
        if(baseEMC == 0) return;
        long costEMC = player.getMainHandItem().getItem().equals(EWItems.XIANGSHUSHUMIAO_PILLOW.get()) ? 0 : Math.min(baseEMC, EMCHelper.getPlayerEMC(player));
        EMCHelper.modifyPlayerEMC(player,new EMCSource.WakeUpEMCSource(Math.negateExact(costEMC),player,null,0),true);
    }

    @SubscribeEvent
    public static void playerUseHoeEvent(UseHoeEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide) return;
        long costEMC = MathUtils.doubleToLong(MathUtils.difficultyLoss() * MathUtils.getUseHoeBaseCost(player));
        if(costEMC == 0) return;
        if(EMCHelper.getPlayerEMC(player) >= costEMC){
            EMCHelper.modifyPlayerEMC(player,new EMCSource.UseHoeEMCSource(Math.negateExact(costEMC),player,event.getContext().getItemInHand(),0),true);
        }
        else{
            event.setCanceled(true);
            if(!player.getCommandSenderWorld().isClientSide) return;
            Message.sendMessage(player, EMCWorld.tc("message.evt.usecancel",MathUtils.format(costEMC)));
        }
    }
    @SubscribeEvent
    public static void playerOpenChestEvent(PlayerContainerEvent.Open event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide) return;
        long costEMC = Math.min(MathUtils.doubleToLong(MathUtils.getChestBaseCost(player,event.getContainer()) * MathUtils.difficultyLoss()),EMCHelper.getPlayerEMC(player));
        if(costEMC == 0) return;
        EMCHelper.modifyPlayerEMC(player,new EMCSource.OpenContainerEMCSource(Math.negateExact(costEMC),player,event.getContainer(),0),true);
    }

    @SubscribeEvent
    public static void playerFillBucketEvent(FillBucketEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide) return;
        long costEMC = MathUtils.doubleToLong(MathUtils.getFillBucketBaseCost(player) * MathUtils.difficultyLoss());
        if(EMCHelper.getPlayerEMC(player) >= costEMC){
            EMCHelper.modifyPlayerEMC(player,new EMCSource.FillBucketEMCSource(Math.negateExact(costEMC),player,event.getFilledBucket(),0),true);
        }
        else{
            event.setCanceled(true);
            if(!player.getCommandSenderWorld().isClientSide) return;
            Message.sendMessage(player, EMCWorld.tc("message.evt.fillcancel",MathUtils.format(costEMC)));
        }
    }
    @SubscribeEvent
    public static void playerCraftItemEvent(final PlayerEvent.ItemCraftedEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.getCommandSenderWorld().isClientSide) return;
        long playerEMC = EMCHelper.getPlayerEMC(player);
        long costEMC = Math.min(MathUtils.doubleToLong(MathUtils.getCraftBaseCost(player) * MathUtils.difficultyLoss() * event.getCrafting().getCount() * (1.0 * 64 / event.getCrafting().getMaxStackSize())),playerEMC);
        if(costEMC == 0) return;
        EMCHelper.modifyPlayerEMC(player,new EMCSource.CraftItemEMCSource(Math.negateExact(costEMC),player,event.getCrafting(),0),true);
    }

    @SubscribeEvent
    public static void blockBreakEvent(BlockEvent.BreakEvent event){
        if(event.getWorld().isClientSide()) return;
        int level = event.getState().getHarvestLevel();
        if(level <= 0) return;
        PlayerEntity player = event.getPlayer();
        long playerEMC = EMCHelper.getPlayerEMC(player);
        long costEMC = Math.min(MathUtils.doubleToLong(MathUtils.getBreakBlockCost(player) * level * MathUtils.difficultyLoss()),playerEMC);
        EMCHelper.modifyPlayerEMC(player,new EMCSource.BreakBlockEMCSource(Math.negateExact(costEMC),player,event.getState(),0),true);
        //Message.sendMessage(event.getPlayer(),EMCWorld.tc("111"));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void spawn(BlockEvent.PortalSpawnEvent event){
        IWorld world = event.getWorld();
        if(ConfigManager.FREE_MODE.get() || world.isClientSide()){
            return;
        }
        boolean cancel = true;
        for(PlayerEntity player : getNearPlayer((World) world,event.getPos())){
            if(GameStageManager.hasStage(player,"nether")){
                cancel = false;
            }
        }
        if(cancel){
            event.setCanceled(true);
            for(PlayerEntity player:getNearPlayer((World) world,event.getPos())){
                Message.sendMessage(player,EMCWorld.tc("message.portal.cancel"));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void PlayerTossEvent(ItemTossEvent event){
        Item item = event.getEntityItem().getItem().getItem();
        if(event.getPlayer().level.isClientSide) return;
        if((item instanceof SwordItem || item instanceof StaffItem || item instanceof WarHammerItem || item instanceof DaggerItem || item instanceof BowItem)){
            //event.setCanceled(true);
        }
    }

    private static List<PlayerEntity> getNearPlayer(World world, BlockPos pos){
        AxisAlignedBB aabb = MathUtils.expandAABB(pos,32);
        return world.getLoadedEntitiesOfClass(PlayerEntity.class,aabb);
    }
}
