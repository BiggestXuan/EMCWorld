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
import biggestxuan.emcworld.api.event.LivingEatFoodEvent;
import biggestxuan.emcworld.api.event.PlayerPrefixFreshEvent;
import biggestxuan.emcworld.api.event.PlayerUpgradeItemEvent;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.common.compact.BloodMagic.BloodMagicHelper;
import biggestxuan.emcworld.common.compact.Curios.PlayerCuriosUtils;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.DaggerItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.WarHammerItem;
import biggestxuan.emcworld.common.items.ModPack.RainFallStar;
import biggestxuan.emcworld.common.items.SponsorsItem.ExceptionApple;
import biggestxuan.emcworld.common.items.SponsorsItem.IceCream;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.api.trait.ITrait;
import biggestxuan.emcworld.api.trait.TraitType;
import biggestxuan.emcworld.common.traits.TraitUtils;
import biggestxuan.emcworld.common.utils.ASUtils;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
                e.afterBreak(player,event.getState(),s);
            }
        });
        for(ItemStack stack : player.inventory.armor){
            TraitUtils.getStackTraits(stack).forEach(e -> {
                if(e.getTraitType() == TraitType.ARMOR){
                    e.afterBreak(player,event.getState(),stack);
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
        for(ItemStack s : player.inventory.armor){
            for(ITrait trait : TraitUtils.getStackTraits(s)){
                if(trait.getTraitType() == TraitType.ARMOR){
                    trait.onFishing(player,s,event.getDrops());
                }
            }
        }
    }

    @SubscribeEvent
    public static void playerRespawnEvent(PlayerEvent.PlayerRespawnEvent event){
        PlayerEntity player = event.getPlayer();
        if(!player.level.isClientSide){
            ItemStack stack = player.getMainHandItem();
            for(ITrait trait : TraitUtils.getStackTraits(stack)){
                if(trait.getTraitType() != TraitType.ARMOR){
                    trait.onPlayerRespawn(player,stack, event.isEndConquered());
                }
            }
            for(ItemStack s : player.inventory.armor){
                for(ITrait trait : TraitUtils.getStackTraits(s)){
                    if(trait.getTraitType() == TraitType.ARMOR){
                        trait.onPlayerRespawn(player,s,event.isEndConquered());
                    }
                }
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

    @EMCWorldSince("1.1.0")
    @SubscribeEvent
    public static void playerAfterUpgradeWeaponEvent(PlayerUpgradeItemEvent.After event){
        PlayerEntity player = event.getPlayer();
        if(event.isSucceed() && !player.level.isClientSide){
            ItemStack stack = PlayerCuriosUtils.getStack(player,EWItems.FXT_XY);
            EMCWorld.DevLogger(stack.toString());
            if(stack.getItem() instanceof RainFallStar){
                //ASUtils.getWorldConstellation(player.level).forEach(e -> EMCWorld.DevLogger(ASUtils.getConstellationName(e)));
                stack.hurtAndBreak(1,player,(c) -> {});
                double chance = EMCWorld.isDevMode ? 1 : 0.01;
                if(MathUtils.isRandom(chance) && ASUtils.itemActiveConstellation(stack,player.level) && player.level.isNight() && !player.level.isRaining()){
                    EMCWorld.DevLogger("up s");
                    ItemStack weapon = event.getItem();
                    if(weapon.getItem() instanceof IUpgradeableItem){
                        IUpgradeableItem item = (IUpgradeableItem) weapon.getItem();
                        item.addLevel(weapon,1);
                    }
                }
            }
        }
    }
    
    @EMCWorldSince("1.1.0")
    @SubscribeEvent
    public static void playerEatEvent(LivingEatFoodEvent event){
        Food food = event.getFood();
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.level;
        if(world instanceof ServerWorld && livingEntity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) livingEntity;
            int i = event.getLevel();
            float f = event.getSaturation();
            float t = i + f * 10;
            long cost = (long) (MathUtils.getCommonBaseCost(player) * t);
            EMCHelper.modifyPlayerEMC(player,new EMCSource.EatSource(-cost,player));
            if(!food.canAlwaysEat()){
                BlockPos pos = player.blockPosition();
                double chance = i * 0.01 + (20 - player.getFoodData().getFoodLevel()) * 0.001;
                if(MathUtils.isRandom(chance)){
                    world.addFreshEntity(new ItemEntity(world,pos.getX(),pos.getY() + 1,pos.getZ(),new ItemStack(EWItems.SCROLL_FEAST.get())));
                }
            }
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
        long cost = -1000;
        if(BloodMagicHelper.isEMCModifyDagger(stack) && EMCHelper.getPlayerEMC(event.player) >= -cost){
            event.shouldDrainHealth = false;
            event.lpAdded = 1000;
            EMCHelper.modifyPlayerEMC(event.player, new EMCSource.EMCTransferBloodSource(cost,event.player));
        }
    }

    @SubscribeEvent
    public static void eat(LivingEatFoodEvent event){
        LivingEntity living = event.getEntityLiving();
        if(event.getFood().equals(IceCream.ICE_FOOD) && living instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) living;
            long base = MathUtils.getEatFoodBase(player,event.getFood())*10000;
            if(!player.level.isClientSide){
                ItemStack stack = PlayerCuriosUtils.getPlayerExceptionApple(player);
                if(!stack.isEmpty()){
                    ((ExceptionApple) stack.getItem()).WhenPlayerEatOtherFood(player);
                }
                for(ItemStack s : player.inventory.armor){
                    TraitUtils.getStackTraits(s).forEach(e -> e.onEat(player,s));
                }
            }
            if(EMCHelper.getPlayerEMC(player) > base){
                player.addEffect(new EffectInstance(Effects.HEAL,EMCWorld.HOMO,4));
                player.addEffect(new EffectInstance(Effects.ABSORPTION,EMCWorld.HOMO,4));
                EMCHelper.modifyPlayerEMC(player,new EMCSource.IceCreamSource(-base,player));
                player.getCooldowns().addCooldown(event.getStack().getItem(),1200);
            }else{
                event.setCanceled(true);
            }
        }
        if(!living.level.isClientSide){
            if(event.getFood().equals(Foods.GOLDEN_APPLE)){
                living.removeEffect(Effects.HEAL);
                living.removeEffect(Effects.ABSORPTION);
            }
        }
    }

    private static List<PlayerEntity> getNearPlayer(World world, BlockPos pos){
        AxisAlignedBB aabb = MathUtils.expandAABB(pos,32);
        return world.getLoadedEntitiesOfClass(PlayerEntity.class,aabb);
    }
}
