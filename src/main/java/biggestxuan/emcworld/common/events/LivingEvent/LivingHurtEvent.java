package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/02
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.raid.RaidEffectExecutor;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import net.mehvahdjukaar.dummmmmmy.entity.TargetDummyEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wayoftime.bloodmagic.ritual.RitualManager;

import javax.annotation.Nonnull;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingHurtEvent {
    @SubscribeEvent
    public static void hurt(net.minecraftforge.event.entity.living.LivingHurtEvent event){
        LivingEntity entity = event.getEntityLiving();
        World world = entity.level;
        if(world.isClientSide || event.getSource() instanceof EWDamageSource) return;
        float damage = event.getAmount();
        if(entity.getMobType().equals(CreatureAttribute.UNDEAD) && event.getSource().equals(DamageSource.ON_FIRE) && isSky(entity) && entity.level.isDay()){
            damage += entity.getMaxHealth() * 0.05f;
        }
        if(entity instanceof TargetDummyEntity && event.getSource().equals(RitualManager.RITUAL_DAMAGE)){
            entity.kill();
            entity.remove();
        }
        ServerWorld world1 = (ServerWorld) world;
        DamageSource source = event.getSource();
        if(source.getDirectEntity() instanceof AbstractIllagerEntity){
            AbstractIllagerEntity attacker = (AbstractIllagerEntity) source.getDirectEntity();
            BlockPos pos = attacker.blockPosition();
            if(world1.isRaided(pos)){
                Raid raid = world1.getRaidAt(pos);
                assert raid != null;
                damage = new RaidEffectExecutor(raid).onIllagerAttack(attacker,entity,damage);
            }
        }
        if(entity instanceof AbstractIllagerEntity){
            BlockPos pos = entity.blockPosition();
            if(world1.isRaided(pos)){
                Raid raid = world1.getRaidAt(pos);
                assert raid != null;
                damage = new RaidEffectExecutor(raid).onIllagerHurt(entity,source,damage);
            }
        }
        if(entity instanceof TameableEntity){
            TameableEntity tame = (TameableEntity) entity;
            LivingEntity owner = tame.getOwner();
            if(owner instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) owner;
                for(DifficultySetting obj:DifficultySetting.values()){
                    if(GameStageManager.hasStage(player, obj.getGameStage())){
                        long costEMC = MathUtils.doubleToLong(obj.getHurtBase() * event.getAmount() * MathUtils.difficultyLoss());
                        if(EMCHelper.getPlayerEMC(player) >= costEMC){
                            EMCHelper.modifyPlayerEMC(player,new EMCSource.HurtEMCSource(Math.negateExact(costEMC),player, event.getSource().getEntity(), damage,tame),true);
                        }else{
                            tame.die(EWDamageSource.TRUE);
                            tame.setHealth(0);
                            Message.sendMessage(player, EMCWorld.tc("message.hurt.pet",tame.getName().getString(),MathUtils.thousandSign(costEMC)));
                        }
                        break;
                    }
                }
            }
        }
        if(entity instanceof PhantomEntity && (event.getSource().equals(DamageSource.ON_FIRE) || event.getSource().equals(DamageSource.IN_FIRE)) && !entity.level.isNight()){
            event.setAmount(event.getAmount() + entity.getMaxHealth() * 0.05F);
        }
        if(entity instanceof WitchEntity && event.getSource().equals(RitualManager.RITUAL_DAMAGE)){
            ModifiableAttributeInstance instance = entity.getAttribute(Attributes.MAX_HEALTH);
            if(instance != null){
                instance.addPermanentModifier(new AttributeModifier(UUID.randomUUID(),"emcworld_bm_ritual_loss",-1, AttributeModifier.Operation.ADDITION));
            }
        }
        event.setAmount(damage);
    }

    private static boolean isSky(@Nonnull LivingEntity entity){
        World world = entity.level;
        BlockPos pos = entity.blockPosition();
        if(world != null && !world.isClientSide){
            for (int i = pos.getY()+1; i < 256; i++) {
                if(!world.getBlockState(new BlockPos(pos.getX(),i,pos.getZ())).getBlock().equals(Blocks.AIR)){
                    return false;
                }
            }
        }
        return true;
    }
}
