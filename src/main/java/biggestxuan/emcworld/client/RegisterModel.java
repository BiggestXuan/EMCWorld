package biggestxuan.emcworld.client;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWEntities;
import com.teammetallurgy.atum.client.render.entity.mobs.AtumBipedRender;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterModel {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.biggest_xuan, AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.tulye, AtumBipedRender::new);
        //RenderingRegistry.registerEntityRenderingHandler(EWEntities.chiyuanovo, AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.dctor_0415, AtumBipedRender::new);
        //RenderingRegistry.registerEntityRenderingHandler(EWEntities.depair_anwu, AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.jaoxaono, AtumBipedRender::new);
        //RenderingRegistry.registerEntityRenderingHandler(EWEntities.juefei, AtumBipedRender::new);
        //RenderingRegistry.registerEntityRenderingHandler(EWEntities.maplefung, AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.mcyunxi, AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.wanglaotou, AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.xiangshushumiao, AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.xk9940, AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.xy177, AtumBipedRender::new);
        //RenderingRegistry.registerEntityRenderingHandler(EWEntities.abunana, AtumBipedRender::new);
        //RenderingRegistry.registerEntityRenderingHandler(EWEntities.alfie_zh, AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.btmy, AtumBipedRender::new);
        //RenderingRegistry.registerEntityRenderingHandler(EWEntities.cxk, AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.dytlj7788,AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.lamb_kisara,AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.cmzxymzx,AtumBipedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EWEntities.sdxhop,AtumBipedRender::new);
        //RenderingRegistry.registerEntityRenderingHandler(EWEntities.ammo,);
    }
}
