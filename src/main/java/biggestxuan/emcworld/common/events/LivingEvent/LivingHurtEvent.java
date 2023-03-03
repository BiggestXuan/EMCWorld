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
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingHurtEvent {
    @SubscribeEvent
    public static void hurt(net.minecraftforge.event.entity.living.LivingHurtEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.level.isClientSide || event.getSource() instanceof EWDamageSource) return;
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
    }
}
