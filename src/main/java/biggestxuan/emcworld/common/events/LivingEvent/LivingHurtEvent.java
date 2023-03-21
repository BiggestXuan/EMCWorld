package biggestxuan.emcworld.common.events.LivingEvent;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/02
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.utils.DifficultySetting;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingHurtEvent {
    @SubscribeEvent
    public static void hurt(net.minecraftforge.event.entity.living.LivingHurtEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.level.isClientSide || event.getSource() instanceof EWDamageSource) return;
        float damage = event.getAmount();
        if(entity.getMobType().equals(CreatureAttribute.UNDEAD) && event.getSource().equals(DamageSource.ON_FIRE) && isSky(entity) && entity.level.dayTime() < 10000){
            damage += entity.getMaxHealth() * 0.05f;
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
                            EMCHelper.modifyPlayerEMC(player,Math.negateExact(costEMC),true);
                        }else{
                            tame.die(EWDamageSource.REALLY);
                            tame.setHealth(0);
                            Message.sendMessage(player, EMCWorld.tc("message.hurt.pet",tame.getName().getString(),MathUtils.thousandSign(costEMC)));
                        }
                    }
                }
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
