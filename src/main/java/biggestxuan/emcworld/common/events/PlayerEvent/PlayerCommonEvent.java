package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/04
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.event.PlayerEatFoodEvent;
import biggestxuan.emcworld.api.event.PlayerPrefixFreshEvent;
import biggestxuan.emcworld.api.event.PlayerUpgradeItemEvent;
import biggestxuan.emcworld.common.compact.BloodMagic.BloodMagicHelper;
import biggestxuan.emcworld.common.compact.Curios.PlayerCurios;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.DaggerItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.WarHammerItem;
import biggestxuan.emcworld.common.items.SponsorsItem.ExceptionApple;
import biggestxuan.emcworld.common.items.SponsorsItem.IceCream;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.traits.ITrait;
import biggestxuan.emcworld.common.traits.TraitType;
import biggestxuan.emcworld.common.traits.TraitUtils;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
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
import wayoftime.bloodmagic.event.SacrificeKnifeUsedEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerCommonEvent {
    @SubscribeEvent
    public static void playerWakeUpEvent(PlayerWakeUpEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide || !ConfigManager.EMC_WAKE.get()) return;
        long baseEMC = MathUtils.doubleToLong(MathUtils.getWakeUpBaseCost(player) * MathUtils.difficultyLoss());
        if(baseEMC == 0) return;
        long costEMC = player.getMainHandItem().getItem().equals(EWItems.XIANGSHUSHUMIAO_PILLOW.get()) ? 0 : Math.min(baseEMC, EMCHelper.getPlayerEMC(player));
        EMCHelper.modifyPlayerEMC(player,new EMCSource.WakeUpEMCSource(Math.negateExact(costEMC),player,null,0),true);
    }

    @SubscribeEvent
    public static void playerUseHoeEvent(UseHoeEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide || !ConfigManager.EMC_HOE.get()) return;
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
        if(player.level.isClientSide || !ConfigManager.EMC_CONTAINER.get()) return;
        long costEMC = Math.min(MathUtils.doubleToLong(MathUtils.getChestBaseCost(player,event.getContainer()) * MathUtils.difficultyLoss()),EMCHelper.getPlayerEMC(player));
        if(costEMC == 0) return;
        EMCHelper.modifyPlayerEMC(player,new EMCSource.OpenContainerEMCSource(Math.negateExact(costEMC),player,event.getContainer(),0),true);
    }

    @SubscribeEvent
    public static void playerFillBucketEvent(FillBucketEvent event){
        PlayerEntity player = event.getPlayer();
        if(player.level.isClientSide || !ConfigManager.EMC_BUCKET.get()) return;
        if(event.getEmptyBucket().equals(event.getFilledBucket())){
            return;
        }
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
        if(player.level.isClientSide || !ConfigManager.EMC_CRAFT.get()) return;
        long playerEMC = EMCHelper.getPlayerEMC(player);
        long costEMC = Math.min(MathUtils.doubleToLong(MathUtils.getCraftBaseCost(player) * MathUtils.difficultyLoss() * event.getCrafting().getCount() * (1.0 * 64 / event.getCrafting().getMaxStackSize())),playerEMC);
        if(costEMC == 0) return;
        EMCHelper.modifyPlayerEMC(player,new EMCSource.CraftItemEMCSource(Math.negateExact(costEMC),player,event.getCrafting(),0),true);
    }

    @SubscribeEvent
    public static void blockBreakEvent(BlockEvent.BreakEvent event){
        if(event.getWorld().isClientSide() || !ConfigManager.EMC_DESTROY.get()) return;
        int level = event.getState().getHarvestLevel();
        if(level <= 0) return;
        PlayerEntity player = event.getPlayer();
        ItemStack s = player.getMainHandItem();
        long playerEMC = EMCHelper.getPlayerEMC(player);
        long costEMC = Math.min(MathUtils.doubleToLong(MathUtils.getBreakBlockCost(player) * level * MathUtils.difficultyLoss()),playerEMC);
        EMCHelper.modifyPlayerEMC(player,new EMCSource.BreakBlockEMCSource(Math.negateExact(costEMC),player,event.getState(),0),true);
        //Message.sendMessage(event.getPlayer(),EMCWorld.tc("111"));
        TraitUtils.getStackTraits(s).forEach(e -> {
            if(e.getTraitType() == TraitType.TOOL){
                e.beforeBreak(player,event.getState(),s);
            }
        });
        for(ItemStack stack : player.inventory.armor){
            TraitUtils.getStackTraits(stack).forEach(e -> {
                if(e.getTraitType() == TraitType.ARMOR){
                    e.beforeBreak(player,event.getState(),stack);
                }
            });
        }
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

    @EMCWorldSince("0.9.0")
    @SubscribeEvent
    public static void FishEvent(ItemFishedEvent event){
        PlayerEntity player = event.getPlayer();
        int count = 0;
        if(!player.level.isClientSide){
            for(ItemStack s : event.getDrops()){
                count += s.getCount() * 64 / s.getMaxStackSize();
            }
        }
        for(DifficultySetting setting : DifficultySetting.values()){
            if(GameStageManager.hasStage(player,setting.getGameStage())){
                double diff = setting.getCommonBase();
                EMCHelper.modifyPlayerEMC(player,new EMCSource.FishEMCSource((long) (-count*diff*5),player,event.getDrops()));
            }
        }
    }

    @SubscribeEvent
    public static void upgradeWeaponEvent(PlayerUpgradeItemEvent.Pre event){
        PlayerEntity player = event.getPlayer();
        double chance = event.getSuccessChance();
        for(ITrait trait : TraitUtils.getStackTraits(event.getItem())){
            if(trait.getTraitType() != TraitType.ARMOR){
                trait.onUpgradeItem(player,event, event.getItem());
            }
        }
        for(ItemStack s : player.inventory.armor){
            for(ITrait trait : TraitUtils.getStackTraits(s)){
                if(trait.getTraitType() == TraitType.ARMOR){
                    trait.onUpgradeItem(player,event,s);
                }
            }
        }
        try{
            IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(player);
            if(cap.getMV() > 1 && ConfigManager.LOTTERY.get()){
                long mv = cap.getMV();
                double c = Math.max(MathUtils.log(1000,mv) / 100d,0.025d * ConfigManager.DIFFICULTY.get());
                event.setSuccessChance(chance + c);
                cap.setMV(0);
            }
        }catch (NullPointerException ignored){

        }
    }

    @EMCWorldSince("1.0.0")
    @SubscribeEvent
    public static void prefix(PlayerPrefixFreshEvent event){
        PlayerEntity player = event.getPlayer();
        for(ITrait trait : TraitUtils.getStackTraits(event.getStack())){
            if(trait.getTraitType() != TraitType.ARMOR){
                trait.onPrefixFresh(player,event, event.getStack());
            }
        }
        for(ItemStack s : player.inventory.armor){
            for(ITrait trait : TraitUtils.getStackTraits(s)){
                if(trait.getTraitType() == TraitType.ARMOR){
                    trait.onPrefixFresh(player,event,s);
                }
            }
        }
    }

    @SubscribeEvent
    @EMCWorldSince("1.0.2")
    public static void playerUseDaggerEvent(SacrificeKnifeUsedEvent event){
        ItemStack stack = event.player.getMainHandItem();
        if(event.player.level.isClientSide){
            return;
        }
        long cost = BloodMagicHelper.getEMCDaggerEMCCost(stack);
        if(BloodMagicHelper.isEMCModifyDagger(stack) && EMCHelper.getPlayerEMC(event.player) >= -cost){
            event.shouldDrainHealth = false;
            event.lpAdded *= Math.pow(2,BloodMagicHelper.getEMCDaggerLevel(stack));
            EMCHelper.modifyPlayerEMC(event.player, new EMCSource.EMCTransferBloodSource(cost,event.player));
        }
    }

    @SubscribeEvent
    public static void eat(PlayerEatFoodEvent event){
        PlayerEntity player = event.getPlayer();
        if(event.getFood().equals(Foods.GOLDEN_APPLE)){
            player.removeEffect(Effects.HEAL);
            player.removeEffect(Effects.ABSORPTION);
        }
        if(event.getFood().equals(IceCream.ICE_FOOD)){
            long base = MathUtils.getEatFoodBase(player,event.getFood())*10000;
            if(EMCHelper.getPlayerEMC(player) > base){
                player.addEffect(new EffectInstance(Effects.HEAL,EMCWorld.HOMO,4));
                player.addEffect(new EffectInstance(Effects.ABSORPTION,EMCWorld.HOMO,4));
                EMCHelper.modifyPlayerEMC(player,new EMCSource.IceCreamSource(-base,player));
                player.getCooldowns().addCooldown(event.getStack().getItem(),1200);
            }else{
                event.setCanceled(true);
            }
        }
        if(!player.level.isClientSide){
            ItemStack stack = PlayerCurios.getPlayerExceptionApple(player);
            if(!stack.isEmpty()){
                ((ExceptionApple) stack.getItem()).WhenPlayerEatOtherFood(player);
            }
            for(ItemStack s : player.inventory.armor){
                TraitUtils.getStackTraits(s).forEach(e -> e.onEat(player,s));
            }
        }
    }

    private static List<PlayerEntity> getNearPlayer(World world, BlockPos pos){
        AxisAlignedBB aabb = MathUtils.expandAABB(pos,32);
        return world.getLoadedEntitiesOfClass(PlayerEntity.class,aabb);
    }
}
