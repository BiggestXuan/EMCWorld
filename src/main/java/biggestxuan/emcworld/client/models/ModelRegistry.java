package biggestxuan.emcworld.client.models;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/20
 */

import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.item.ItemModelsProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static biggestxuan.emcworld.EMCWorld.rl;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelRegistry {
    @SubscribeEvent
    public static void ItemModelRegistry(FMLClientSetupEvent event){
        event.enqueueWork(()-> ItemModelsProperties.register(EWItems.EMC_CHARGE_GEM.get(),rl("level"),new EMCLevelModel()));
        event.enqueueWork(()-> ItemModelsProperties.register(EWItems.SHENG_XUAN.get(),rl("god_gun"),new GodGunModel()));
    }
}
