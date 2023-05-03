package biggestxuan.emcworld.common.events.LivingEvent;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/25
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingDropExperienceEvent {
    @SubscribeEvent
    public static void livingDropExperienceEvent(LivingExperienceDropEvent event){
        if(event.getEntityLiving() instanceof PlayerEntity || event.getEntityLiving().getCommandSenderWorld().isClientSide) return;
        event.setDroppedExperience((int) (event.getOriginalExperience()* MathUtils.difficultyLoss()));
    }
}
