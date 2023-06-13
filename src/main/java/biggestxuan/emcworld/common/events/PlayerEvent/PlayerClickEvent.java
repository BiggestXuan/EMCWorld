package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.event.PlayerAddMaxLevelEvent;
import biggestxuan.emcworld.api.item.base.BaseDifficultyItem;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import biggestxuan.emcworld.api.recipe.IUpdateRecipe;
import biggestxuan.emcworld.common.blocks.MultiBlock;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.CraftTweaker.CrTConfig;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Mekanism.MekUtils;
import biggestxuan.emcworld.common.compact.Projecte.EMCGemsMapping;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.EMCGemItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.DaggerItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Gun.GunItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Staff.StaffItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Sword.InfinitySword;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.WarHammerItem;
import biggestxuan.emcworld.common.items.ProfessionalItem.AddMaxLevelItem;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.toClient.BuyLotteryClientPacket;
import biggestxuan.emcworld.common.network.toServer.LeftClickPacket;
import biggestxuan.emcworld.common.network.toServer.StaffAttackPacket;
import biggestxuan.emcworld.common.recipes.AdvancedUpdateRecipe;
import biggestxuan.emcworld.common.recipes.UpdateRecipe;
import biggestxuan.emcworld.common.registry.*;
import biggestxuan.emcworld.common.skill.PlayerSkillModify;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.utils.SkillUtils;
import hellfirepvp.astralsorcery.common.data.research.PlayerProgress;
import hellfirepvp.astralsorcery.common.data.research.ResearchHelper;
import hellfirepvp.astralsorcery.common.data.research.ResearchManager;
import mekanism.common.item.ItemQIODrive;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerClickEvent {
    @SubscribeEvent
    public static void LeftClickItemEvent(PlayerInteractEvent.LeftClickEmpty event){
        ItemStack stack = event.getItemStack();
        if(stack.getItem() instanceof BaseEMCGodSword){
            BaseEMCGodSword weapon = (BaseEMCGodSword) stack.getItem();
            if(weapon.getLevel(stack) >= 12){
                //PacketHandler.sendToServer(new LeftClickPacket());
            }
        }
        if(stack.getItem() instanceof InfinitySword || MekUtils.isInfinityMekaTool(stack)){
            PacketHandler.sendToServer(new LeftClickPacket());
        }
        if(stack.getItem() instanceof StaffItem){
            PacketHandler.sendToServer(new StaffAttackPacket());
        }
    }

    @SubscribeEvent
    public static void RightClickItemEvent(PlayerInteractEvent.RightClickItem evt){
        World world = evt.getWorld();
        ItemStack itemStack = evt.getItemStack();
        if(world.isClientSide) return;
        int count = itemStack.getCount();
        PlayerEntity player = evt.getPlayer();
        if(itemStack.isEmpty()) return;
        PlayerProgress progress = ResearchHelper.getProgress(player, LogicalSide.SERVER);
        if(itemStack.getItem() instanceof EMCGemItem && progress.isValid() && !progress.hasConstellationDiscovered(EMCWorld.rl("emc"))){
            ResearchManager.discoverConstellation(EWStarlight.EMC_STAR.get(),player);
        }
        for(EMCGemsMapping obj:EMCGemsMapping.values()){
            if(itemStack.getItem().equals(obj.getItem())){
                if(!GameStageManager.hasStage(player,obj.getGameStage()) && !ConfigManager.FREE_MODE.get()) {
                    Message.MessageDisplay(player, EMCWorld.tc("message.evt.gemcancel",obj.getGameStage()));
                    return;
                }
                if(player.isShiftKeyDown()){
                    long getEMC = MathUtils.getEMCWhenUseGem(obj.getBaseEMC()) * count;
                    EMCHelper.modifyPlayerEMC(player,new EMCSource.UseEMCGemEMCSource(getEMC,player,itemStack,0),true);
                    itemStack.setCount(0);
                    return;
                }
                long actEMC = MathUtils.getEMCWhenUseGem(obj.getBaseEMC());
                EMCHelper.modifyPlayerEMC(player, new EMCSource.UseEMCGemEMCSource(actEMC,player,itemStack,0),true);
                itemStack.shrink(1);
            }
        }
        if(itemStack.getItem().equals(Items.PAPER) && ConfigManager.LOTTERY.get()){
            PacketHandler.sendToClient(new BuyLotteryClientPacket(),(ServerPlayerEntity) player);
        }
        if(itemStack.getItem().equals(EWItems.EMC_CHECK.get())){
            double diff = CrTConfig.getWorldDifficulty();
            double dl = MathUtils.difficultyLoss();
            Message.sendMessage(player, EMCWorld.tc("message.check.line"));
            Message.sendMessage(player, EMCWorld.tc("message.check.main",diff,MathUtils.modulus(Math.min(-0.3*diff+1.3,1d)),MathUtils.modulus(dl),MathUtils.modulus(1.0 / MathUtils.difficultyLoss())));
            for(DifficultySetting obj:DifficultySetting.values()){
                if(GameStageManager.hasStage(player, obj.getGameStage())){
                    Message.sendMessage(player, EMCWorld.tc("message.check.stage",obj.getGameStage()));
                    Message.sendMessage(player, EMCWorld.tc("message.check.attack",MathUtils.doubleFormat((obj.getAttackBase() * dl))));
                    Message.sendMessage(player, EMCWorld.tc("message.check.death",MathUtils.doubleFormat((obj.getDeathBase() * dl))));
                    Message.sendMessage(player, EMCWorld.tc("message.check.hurt",MathUtils.doubleFormat(obj.getHurtBase() * dl)));
                    Message.sendMessage(player, EMCWorld.tc("message.check.pickupitem",MathUtils.doubleFormat(MathUtils.getPickUpItemBaseCost(player) * dl)));
                    Message.sendMessage(player, EMCWorld.tc("message.check.wakeup",MathUtils.doubleFormat(MathUtils.getWakeUpBaseCost(player) * dl)));
                    Message.sendMessage(player, EMCWorld.tc("message.check.container",MathUtils.doubleFormat(obj.getChestBase() * dl)));
                    Message.sendMessage(player, EMCWorld.tc("message.check.hoe",MathUtils.doubleFormat(MathUtils.getUseHoeBaseCost(player) * dl)));
                    Message.sendMessage(player, EMCWorld.tc("message.check.fill",MathUtils.doubleFormat(MathUtils.getFillBucketBaseCost(player) * dl)));
                    Message.sendMessage(player, EMCWorld.tc("message.check.craft",MathUtils.doubleFormat(MathUtils.getCraftBaseCost(player) * dl)));
                    Message.sendMessage(player, EMCWorld.tc("message.check.break_block",MathUtils.doubleFormat(MathUtils.getBreakBlockCost(player) * dl)));
                    Message.sendMessage(player, EMCWorld.tc("message.check.tip"));
                    Message.sendMessage(player, EMCWorld.tc("message.check.line"));
                    break;
                }
            }
        }
        BlockPos blockPos = evt.getPlayer().blockPosition();
        BlockPos act = new BlockPos(blockPos.getX(),blockPos.getY()-1,blockPos.getZ());
        if(world.getBlockState(act).equals(EWBlocks.CONTROL_UPDATE_CORE.get().defaultBlockState())){
            MultiBlock.UpdateMath info = MultiBlock.getUpdateInfo(world,act);
            for(UpdateRecipe obj:UpdateRecipe.values()){
                if(itemStack.getItem().equals(obj.getInput().getItem()) && obj.recipeLevel() == 0){
                    craftRecipe(obj,player,info);
                }
            }
        }
        if(world.getBlockState(act).equals(EWBlocks.ADVANCED_UPDATE_CORE.get().defaultBlockState())){
            int level = MultiBlock.getMultiLevel(world,act);
            MultiBlock.UpdateMath info = MultiBlock.getUpdateInfo(world,act);
            for(AdvancedUpdateRecipe obj:AdvancedUpdateRecipe.values()){
                if(itemStack.getItem().equals(obj.getInput().getItem()) && obj.recipeLevel() <= level){
                    craftRecipe(obj,player,info);
                }
            }
        }
        IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
        IUtilCapability util = player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new);
        if(cap.getSkills()[7] == 0 && cap.getSkills()[4] != 0){
            if(itemStack.getItem() instanceof SwordItem && cap.getProfession() == 1){
                player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST,cap.getSkills()[4]/500,0));
            }
            if(itemStack.getItem() instanceof ShieldItem && cap.getProfession() == 2){
                player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE,cap.getSkills()[4]/500,0));
            }
            if(itemStack.getItem() instanceof StaffItem && cap.getProfession() == 3){
                player.addEffect(new EffectInstance(EWEffects.MAGIC_PROTECT.get(),cap.getSkills()[4]/500,0));
            }
            if(itemStack.getItem() instanceof DaggerItem && cap.getProfession() == 4){
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,cap.getSkills()[4]/500,1));
            }
            if(itemStack.getItem() instanceof WarHammerItem && cap.getProfession() == 5){
                player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST,cap.getSkills()[4]/500,0));
                player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE,cap.getSkills()[4]/500,0));
            }
            if(itemStack.getItem() instanceof GunItem && cap.getProfession() == 6 && player.isShiftKeyDown()){
                player.addEffect(new EffectInstance(EWEffects.ACCURACY.get(),cap.getSkills()[4]/500,1));
            }
            cap.setSkills(7,cap.getSkills()[5]/500);
        }
        if(itemStack.getItem().equals(EWItems.SKILL_ITEM1.get())){
            if(cap.getSkills()[16] != 0 && cap.getSkills()[19] == 0){
                itemStack.shrink(1);
                cap.setSkills(19,cap.getSkills()[17]/500);
                if(cap.getProfession() == 1){
                    for(PlayerEntity player1:getPlayers(player,5)){
                        player1.addEffect(new EffectInstance(Effects.DAMAGE_BOOST,cap.getSkills()[16]/500,1));
                    }
                }
                if(cap.getProfession() == 2){
                    for(PlayerEntity player1:getPlayers(player,5)){
                        player1.addEffect(new EffectInstance(Effects.REGENERATION,cap.getSkills()[16]/500,2));
                    }
                }
                if(cap.getProfession() == 3){
                    for(PlayerEntity player1:getPlayers(player,5)){
                        player1.addEffect(new EffectInstance(EWEffects.MAGIC_PROTECT.get(),cap.getSkills()[16]/500,2));
                    }
                }
                if(cap.getProfession() == 4){
                    for(PlayerEntity player1:getPlayers(player,10)){
                        player1.addEffect(new EffectInstance(Effects.INVISIBILITY,cap.getSkills()[16]/500,2));
                    }
                }
                if(cap.getProfession() == 5){
                    for(PlayerEntity player1:getPlayers(player,5)){
                        player1.addEffect(new EffectInstance(EWEffects.ATTACK_RANGE.get(),cap.getSkills()[16]/500,0));
                    }
                }
                if(cap.getProfession() == 6){
                    for(PlayerEntity player1:getPlayers(player,5)){
                        player1.addEffect(new EffectInstance(EWEffects.ATTACK_SPEED.get(),cap.getSkills()[16]/500,0));
                    }
                }
            }
        }
        if(itemStack.getItem().equals(EWItems.SKILL_ITEM2.get())){
            if(cap.getSkills()[43] == 0 && cap.getModify() != 0){
                itemStack.shrink(1);
                if(cap.getProfession() == 1){
                    util.setTimer(cap.getSkills()[32]/500);
                    cap.setSkills(43,cap.getSkills()[35]/500);
                } else if(cap.getProfession() == 2 && cap.getModify() == 1){
                    int timeTick = cap.getSkills()[32]/500;
                    util.setTimer(timeTick);
                    cap.setSkills(43,cap.getSkills()[35]/500);
                    player.heal(player.getMaxHealth() * cap.getSkills()[33]/10000f);
                    player.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,timeTick,cap.getSkills()[34]/10000));
                } else if(cap.getProfession() == 2 && cap.getModify() == 2){
                    List<? extends PlayerEntity> distancePlayers = getPlayers(player,cap.getSkills()[32]/10000d);
                    for(PlayerEntity player1 : distancePlayers){
                        for (int i = 0; i < cap.getSkills()[33]/10000; i++) {
                            giveRandomEffects(player1,cap.getSkills()[34]/500);
                        }
                    }
                    cap.setSkills(43,cap.getSkills()[35]/500);
                } else if(cap.getProfession() == 3){
                    if(cap.getModify() == 1){
                        for(LivingEntity entity:world.getLoadedEntitiesOfClass(LivingEntity.class,MathUtils.expandAABB(player.position(),cap.getSkills()[32]/10000))){
                            if(entity instanceof PlayerEntity){
                                continue;
                            }
                            entity.hurt(new EWDamageSource(player).REALLY_PLAYER,entity.getMaxHealth() * cap.getSkills()[33]/10000f);
                        }
                    } else if(cap.getModify() == 2){
                        for(LivingEntity entity:world.getLoadedEntitiesOfClass(LivingEntity.class,MathUtils.expandAABB(player.position(),cap.getSkills()[32]/10000))){
                            if(entity instanceof PlayerEntity){
                                continue;
                            }
                            entity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,600,cap.getSkills()[33]/10000));
                        }
                    }
                    cap.setSkills(43,cap.getSkills()[34]/500);
                }else if(cap.getProfession() == 4){
                    if(cap.getModify() == 1){
                        for(LivingEntity living : MathUtils.getNearLiving(player,cap.getSkills()[32]/10000,false)){
                            float base = (float) SkillUtils.getPlayerAttackDamage(player,itemStack).total();
                            living.hurt(EWDamageSource.REALLY,(float) (base * cap.getSkills()[33]/10000d));
                        }
                    }else{
                        util.setTimer(cap.getSkills()[32]/500);
                    }
                    cap.setSkills(43,cap.getSkills()[34]/500);
                }else if(cap.getProfession() == 5){
                    int im = cap.getSkills()[32]/10000;
                    int time = cap.getSkills()[33]/500;
                    if(cap.getModify() == 1){
                        player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST,time,4));
                        player.addEffect(new EffectInstance(EWEffects.ATTACK_RANGE.get(),time,im-1));
                    }else{
                        for(PlayerEntity player1 : getPlayers(player,5)){
                            IPlayerSkillCapability ss = EMCWorldAPI.getInstance().getPlayerSkillCapability(player1);
                            if(ss.getProfession() == 5){
                                im *= 2;
                                time *= 2;
                            }
                            player.addEffect(new EffectInstance(EWEffects.ATTACK_RANGE.get(),time,im-1));
                        }
                    }
                    cap.setSkills(43,cap.getSkills()[34]/500);
                }else if(cap.getProfession() == 6){
                    int level = cap.getSkills()[33]/10000;
                    if(cap.getModify() == 1){
                        player.addEffect(new EffectInstance(EWEffects.ATTACK_SPEED.get(),level-1,cap.getSkills()[32]/500));
                    }else{
                        player.addEffect(new EffectInstance(EWEffects.REMOTE_DAMAGE.get(),level-1,cap.getSkills()[32]/500));
                    }
                    cap.setSkills(43,cap.getSkills()[34]/500);
                }
            }
        }
        if(itemStack.getItem() instanceof AddMaxLevelItem && cap.getProfession() != 0){
            AddMaxLevelItem item = (AddMaxLevelItem) itemStack.getItem();
            int level = item.getMaxLevel();
            if(cap.getMaxLevel() <= level || (player.isCreative() && player.hasPermissions(4))){
                itemStack.shrink(1);
                int maxLevel = Math.min(level,100);
                MinecraftForge.EVENT_BUS.post(new PlayerAddMaxLevelEvent(player,cap.getMaxLevel(),maxLevel));
                cap.setMaxLevel(maxLevel);
                if(item.getModify() != 0){
                    cap.setModify(item.getModify());
                }
                PlayerSkillModify.makePlayerGetDefaultSkill(player,item);
            }
        }
        if(itemStack.getItem().equals(EWItems.NONAME_CATFOOD.get())){
            itemStack.shrink(1);
            player.getFoodData().setFoodLevel(20);
            player.getFoodData().setSaturation(20);
        }
        if(itemStack.getItem() instanceof BaseDifficultyItem){
            BaseDifficultyItem item = (BaseDifficultyItem) itemStack.getItem();
            if(CrTConfig.getWorldDifficulty() < item.getDifficulty()){
                Message.sendMessage(player, EMCWorld.tc("message.item.cancel",item.getDifficulty()));
                evt.setCanceled(true);
                return;
            }
            if(player.getCooldowns().isOnCooldown(item)) return;
            if(item.equals(EWItems.RAID_LIGHT.get())){
                ServerWorld serverWorld = Objects.requireNonNull(player.getServer()).overworld();
                if(!serverWorld.isRaided(new BlockPos(player.position()))){
                    Message.sendMessage(player, EMCWorld.tc("message.raid.raid_light"));
                    return;
                }
                if(!player.isCreative()){
                    itemStack.setDamageValue(itemStack.getDamageValue()+1);
                }
                if(itemStack.getDamageValue() >= itemStack.getMaxDamage()){
                    itemStack.shrink(1);
                }
                if(!player.isCreative()){
                    player.getCooldowns().addCooldown(item,ConfigManager.SUNDRY_RAID_LIGHT_CD.get());
                }
                for(AbstractRaiderEntity entity:getNearRaidEntity(player)){
                    entity.addEffect(new EffectInstance(Effects.GLOWING,1200,0));
                    if(player.isShiftKeyDown()){
                        BlockPos pos = entity.blockPosition();
                        Message.sendMessage(player,EMCWorld.tc("message.raid.pos",entity.getDisplayName().getString(),pos.getX(),pos.getY(),pos.getZ()));
                    }
                }
            }
        }
    }

    private static void craftRecipe(IUpdateRecipe recipe, PlayerEntity player, MultiBlock.UpdateMath info){
        ItemStack itemStack = player.getItemInHand(Hand.MAIN_HAND);
        ItemStack output = recipe.getOutput();
        if(PlayerPickUpItemEvent.full(player,output)){
            return;
        }
        if(itemStack.getItem() instanceof ItemQIODrive && output.getItem() instanceof ItemQIODrive){
            output.setTag(itemStack.getTag());
        }
        long costEMC = MathUtils.doubleToLong(recipe.costEMC() * MathUtils.difficultyLoss() * info.getCost());
        long playerEMC = EMCHelper.getPlayerEMC(player);
        if(playerEMC < costEMC) return;
        IUtilCapability c = player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new);
        long time = info.getTime();
        if(c.getCoolDown() == 0){
            int craftAmount = MathUtils.getMaxAmount(playerEMC,costEMC);
            int hasAmount = itemStack.getCount();
            int actAmount = Math.min(craftAmount,hasAmount);
            if(player.isShiftKeyDown()){
                costEMC *= actAmount;
            }else{
                actAmount = 1;
            }
            output.setCount(MathUtils.getAdditionAmount(info.getAddon()) * actAmount);
            player.addItem(output);
            itemStack.setCount(itemStack.getCount()-actAmount);
            EMCHelper.modifyPlayerEMC(player,new EMCSource.CraftItemEMCSource(Math.negateExact(costEMC),player,output,0),true);
            time = recipe.recipeLevel() == 0 ? 0 : time;
            c.setCoolDown(time);
        }
        else {
            Message.sendMessage(player, EMCWorld.tc("message.emcworld.updatecd",c.getCoolDown()/20));
        }
    }

    private static List<AbstractRaiderEntity> getNearRaidEntity(PlayerEntity player){
        List<AbstractRaiderEntity> out = new ArrayList<>();
        BlockPos playerPos = new BlockPos(player.position());
        assert player.level.getServer() != null;
        ServerWorld world = player.level.getServer().overworld();
        if (world.isRaided(playerPos)){
            Raid raid = world.getRaidAt(playerPos);
            assert raid != null;
            int raidID = raid.getId();
            BlockPos centerPos = raid.getCenter();
            int x = centerPos.getX();
            int z = centerPos.getZ();
            AxisAlignedBB aabb = new AxisAlignedBB(x-128,0,z-128,x+128,256,z+128);
            for (AbstractRaiderEntity entity:world.getLoadedEntitiesOfClass(AbstractRaiderEntity.class,aabb)){
                if(!world.isRaided(new BlockPos(entity.position()))){
                    continue;
                }
                if(world.getRaidAt(new BlockPos(entity.position())).getId() == raidID){
                    out.add(entity);
                }
            }
        }
        return out;
    }

    public static List<? extends PlayerEntity> getPlayers(PlayerEntity player,double distance){
        World world = player.getCommandSenderWorld();
        List<PlayerEntity> outputPlayer = new ArrayList<>();
        List<? extends PlayerEntity> allPlayer = world.players();
        for(PlayerEntity player1 : allPlayer){
            if(MathUtils.isTwoLivingDistance(player,player1,distance)){
                outputPlayer.add(player1);
            }
        }
        return outputPlayer;
    }

    public static void giveRandomEffects(PlayerEntity player,int tick){
        double random = Math.random();
        if(random < 0.2){
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST,tick,2));
        }
        else if(random < 0.4){
            player.addEffect(new EffectInstance(Effects.REGENERATION,tick,2));
        }
        else if(random < 0.6){
            player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE,tick,2));
        }
        else if(random < 0.8){
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,tick,2));
        }
        else{
            player.addEffect(new EffectInstance(Effects.NIGHT_VISION,tick*10,2));
        }
    }
}
