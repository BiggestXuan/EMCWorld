package biggestxuan.emcworld.common.events.LivingEvent;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWEffects;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID)
public class LivingPotionRemoveEvent {
    @SubscribeEvent
    public static void removeEvent(PotionEvent.PotionRemoveEvent event){
        Effect effect = event.getPotion();
        if(effect.equals(EWEffects.EMC_BROKEN.get()) || effect.equals(EWEffects.EMC_FLAMING.get())){
            event.setCanceled(true);
        }
    }
}
