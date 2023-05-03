
package biggestxuan.emcworld.common.events.LivingEvent;


/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/02
 */


import biggestxuan.emcworld.EMCWorld;
import com.github.alexthe666.rats.server.blocks.RatlantisBlockRegistry;
import com.github.alexthe666.rats.server.entity.EntityRat;
import net.minecraft.entity.item.ItemEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingDropEvent {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void LivingDropsEvent(LivingDropsEvent event){
        if(event.getEntityLiving() instanceof EntityRat){
            Collection<ItemEntity> drops = event.getDrops();
            for(ItemEntity d :drops){
                if(d.getItem().getItem().equals(RatlantisBlockRegistry.CHUNKY_CHEESE_TOKEN.asItem())){
                    event.setCanceled(true);
                }
            }
        }
    }
}

