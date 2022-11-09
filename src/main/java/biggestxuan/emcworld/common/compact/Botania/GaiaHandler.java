/*
package biggestxuan.emcworld.common.compact.Botania;

*/
/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/30
 *//*


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import vazkii.botania.common.entity.EntityDoppleganger;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class GaiaHandler {
    private Field playersWhoAttacked;
    private Field playerCount;

    public GaiaHandler(){
        try{
            this.playersWhoAttacked = EntityDoppleganger.class.getDeclaredField("playersWhoAttacked");
            this.playersWhoAttacked.setAccessible(true);
            this.playerCount = EntityDoppleganger.class.getDeclaredField("playerCount");
            this.playerCount.setAccessible(true);
        }catch (NoSuchFieldException exception){
            exception.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onGaiaHurt(LivingAttackEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(playersWhoAttacked != null && playerCount != null && entity instanceof EntityDoppleganger){
            try{
                final List<UUID> playersWhoAttacked = (List<UUID>) this.playersWhoAttacked.get(entity);
                final int playerCount = this.playerCount.getInt(entity);
                int currentCount = playersWhoAttacked.size();
                if (playerCount <= currentCount) {
                    this.playerCount.setInt(entity, currentCount);
                    final int BASE_MAX_HEALTH = (int) (entity.getMaxHealth() / playerCount);
                    entity.getAttribute(Attributes.MAX_HEALTH)
                            .setBaseValue(BASE_MAX_HEALTH * currentCount);
                    entity.setHealth(entity.getHealth() + BASE_MAX_HEALTH * (currentCount - playerCount));
                }
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
    }
}
*/
