package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/03
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.api.item.ICostEMCItem;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.equipment.dagger.BaseEMCGodDagger;
import biggestxuan.emcworld.api.item.equipment.weapon.IAdditionsDamageWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.ICriticalWeapon;
import biggestxuan.emcworld.api.item.equipment.weapon.IRangeAttackWeapon;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.DaggerItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.WarHammerItem;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import net.mehvahdjukaar.dummmmmmy.setup.Registry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerAttackEvent {
    @SubscribeEvent
    public static void playerAttackEvent(LivingHurtEvent event){
        if(event.getEntityLiving().getCommandSenderWorld().isClientSide) return;
        DamageSource source = event.getSource();
        LivingEntity livingEntity = event.getEntityLiving();
        if(source.getDirectEntity() instanceof PlayerEntity && !source.equals(EWDamageSource.REALLY)){
            PlayerEntity player = (PlayerEntity) source.getDirectEntity();
            float damage = event.getAmount();
            IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
            IUtilCapability util = player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new);
            ItemStack stack = player.getItemInHand(Hand.MAIN_HAND);
            ServerWorld world = player.getServer().overworld();
            if(stack.getItem() instanceof IEMCInfuserItem){
                IEMCInfuserItem emcItem = (IEMCInfuserItem) stack.getItem();
                emcItem.cost(stack);
            }
            if(stack.getItem() instanceof IAdditionsDamageWeapon){
                damage += ((IAdditionsDamageWeapon) stack.getItem()).getAdditionsDamage(stack);
            }
            damage = damage > 0 ? damage : 0;
            if(stack.getItem() instanceof ICriticalWeapon){
                ICriticalWeapon weapon = (ICriticalWeapon) stack.getItem();
                if(MathUtils.isRandom(weapon.getActCriticalChance(stack))){
                    damage *= weapon.getActCriticalRate(stack);
                }
            }
            int level = cap.getLevel();
            int[] skills = cap.getSkills();
            double chance = skills[8]/10000d;
            if(cap.getProfession() == 1){
                damage *= Math.pow(1+skills[0]/10000f,level);
                int modify = cap.getModify();
                if(modify == 1){
                    if(skills[36] !=0 && skills[37] !=0){
                        if(livingEntity.getHealth() / livingEntity.getMaxHealth() <= skills[36]/10000f){
                            livingEntity.hurt(EWDamageSource.REALLY,livingEntity.getMaxHealth());
                        }
                    }
                    if(skills[40] != 0 && skills[41] != 0){
                        chance = 1.0d;
                    }
                    if(util.getTimer() >0){
                        damage *= 1+(cap.getSkills()[33]/10000f);
                    }
                }
                if(skills[8] != 0){
                    if(MathUtils.isRandom(chance)){
                        if(skills[12] >=0){
                            damage *= skills[12]/10000d+1;
                        }
                        else damage+=3;
                    }
                }
                if(skills[24] != 0){
                    float base = 1 - skills[24] /10000f;
                    livingEntity.hurt(EWDamageSource.REALLY,damage * skills[24]/10000f);
                    damage *= base;
                }
                if(skills[28] != 0){
                    damage += skills[28] /10000f;
                }
                if(modify == 2){
                    float healRate = skills[36] / 10000f;
                    if(skills[40] != 0 && skills[41] != 0){
                        healRate = 1.0f;
                    }
                    if(skills[36] != 0 && skills[37] != 0){
                        player.heal(healRate * damage);
                    }
                    if(util.getTimer() > 0){
                        player.heal(damage * cap.getSkills()[33]/10000f);
                    }
                }
            }
            if(world.isRaided(new BlockPos(player.position()))){
                damage *= util.getRaidRate();
            }
            double rate = 1d;
            if(stack.getItem() instanceof ICostEMCItem){
                ICostEMCItem item = (ICostEMCItem) stack.getItem();
                rate = item.costEMCWhenAttack(stack);
            }
            long damageCost = MathUtils.doubleToLong(MathUtils.getAttackBaseCost(player) * damage *  MathUtils.difficultyLoss());
            long costEMC = damageCost;
            if(event.getEntityLiving().getType().equals(Registry.TARGET_DUMMY.get())){
                costEMC = 0;
            }
            if(stack.getItem() instanceof IRangeAttackWeapon && livingEntity.hurtTime <= 0){
                IRangeAttackWeapon weapon = (IRangeAttackWeapon) stack.getItem();
                if(weapon.getAttackRange(stack) > 0d){
                    List<? extends LivingEntity> canRangeAttack = getNearEntity(player,event.getEntityLiving(),weapon.getAttackRange(stack));
                    if(canRangeAttack.size() != 0){
                        for(LivingEntity entity:canRangeAttack){
                            if(costEMC > EMCHelper.getPlayerEMC(player)) break;
                            entity.hurt(new EWDamageSource.ReallyDamage(player),damage);
                            if(event.getEntityLiving().getType().equals(Registry.TARGET_DUMMY.get())){
                                continue;
                            }
                            costEMC += damageCost;
                        }
                    }
                }
            }
            costEMC =(long) (costEMC * rate);
            CostPlayer(player,costEMC,event);
            event.setAmount(damage);
        }
        if(event.getEntityLiving().getType().equals(Registry.TARGET_DUMMY.get())){
            return;
        }
        if(source.getDirectEntity() instanceof TameableEntity){
            TameableEntity entity = (TameableEntity) source.getDirectEntity();
            LivingEntity owner = entity.getOwner();
            if(owner == null) return;
            if(owner instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) owner;
                float damage = event.getAmount();
                long cost = MathUtils.doubleToLong(MathUtils.getAttackBaseCost(player) * damage *  MathUtils.difficultyLoss());
                CostPlayer(player,cost,event);
            }
        }
        if(source.getDirectEntity() instanceof ProjectileEntity){
            ProjectileEntity proEntity = (ProjectileEntity) source.getDirectEntity();
            Entity entity = proEntity.getOwner();
            if(entity instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) entity;
                float damage = event.getAmount();
                long cost = MathUtils.doubleToLong(MathUtils.getAttackBaseCost(player) * damage *  MathUtils.difficultyLoss());
                CostPlayer(player,cost,event);
            }
        }
    }

    private static void CostPlayer(PlayerEntity player,long emc,LivingHurtEvent event){
        if (emc != 0){
            if(EMCHelper.getPlayerEMC(player)>emc){
                EMCHelper.modifyPlayerEMC(player,Math.negateExact(emc),true);
            }
            else{
                event.setCanceled(true);
                Message.sendMessage(player, EMCWorld.tc("message.evt.attackcancel",MathUtils.format(emc)));
            }
        }
    }

    public static List<? extends LivingEntity> getNearEntity(PlayerEntity attacker,LivingEntity entity,double distance){
        List<LivingEntity> list = new ArrayList<>();
        ItemStack stack = attacker.getMainHandItem();
        World world = entity.level;
        int x = entity.blockPosition().getX();
        int y = entity.blockPosition().getY();
        int z = entity.blockPosition().getZ();
        AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(x+64,y+64,z+64),new BlockPos(x-64,y-64,z-64));
        List<LivingEntity> cache = world.getLoadedEntitiesOfClass(LivingEntity.class,aabb);
        for(LivingEntity entity1:cache){
            if(stack.getItem() instanceof IRangeAttackWeapon){
                IRangeAttackWeapon weapon = (IRangeAttackWeapon) stack.getItem();
                if(weapon.getAttackMode(stack) == IRangeAttackWeapon.AttackMode.ONLY_MOB){
                    if(!(entity1 instanceof MonsterEntity)){
                        continue;
                    }
                }
                if(weapon.getAttackMode(stack) == IRangeAttackWeapon.AttackMode.ONLY_ANIMAL){
                    if(!(entity1 instanceof AnimalEntity)){
                        continue;
                    }
                }
                if(weapon.getAttackMode(stack) == IRangeAttackWeapon.AttackMode.EXCEPT_PLAYER){
                    if(entity1 instanceof PlayerEntity){
                        continue;
                    }
                }
                if(weapon.getAttackMode(stack) == IRangeAttackWeapon.AttackMode.ONLY_PLAYER){
                    if(!(entity1 instanceof PlayerEntity)){
                        continue;
                    }
                }
                if(weapon.getAttackMode(stack) == IRangeAttackWeapon.AttackMode.DUMMY){
                    continue;
                }
            }
            if(MathUtils.isTwoLivingDistance(entity,entity1,distance) && !(entity1.equals(entity)) && !(entity1.equals(attacker))){
                if(entity1 instanceof TameableEntity){
                    TameableEntity entity2 = (TameableEntity) entity1;
                    if(entity2.getOwnerUUID() != null){
                        continue;
                    }
                }
                list.add(entity1);
            }
        }
        return list;
    }

    @SubscribeEvent
    public static void attackEvent(AttackEntityEvent event){
        PlayerEntity entity = event.getPlayer();
        Entity entity1 = entity.getEntity();
        if(entity.getMainHandItem().getItem() instanceof WarHammerItem && entity.getAttackStrengthScale(0) != 1){
            event.setCanceled(true);
        }
        if(entity1 instanceof LivingEntity){
            LivingEntity livingEntity = (LivingEntity) entity1;
            if(entity.getMainHandItem().getItem() instanceof DaggerItem){
                DaggerItem daggerItem = (DaggerItem) entity.getMainHandItem().getItem();
                if(daggerItem.getTier().getSpeed() >= -1.6){
                    return;
                }
                livingEntity.hurtTime -= 8;
                livingEntity.invulnerableTime -= 3;
                if(daggerItem instanceof BaseEMCGodDagger){
                    BaseEMCGodDagger emcGodDagger = (BaseEMCGodDagger) daggerItem;
                    livingEntity.hurtTime -= Math.round(0.5 * emcGodDagger.getLevel(entity.getMainHandItem()));
                    livingEntity.invulnerableTime -= Math.round(0.5 * emcGodDagger.getLevel(entity.getMainHandItem()));
                }
            }
        }
    }
}
