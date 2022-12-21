package biggestxuan.emcworld.client.event;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tfar.classicbar.ModUtils;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,value = Dist.CLIENT)
public class RenderEvent {
    @SubscribeEvent
    public void renderBars(RenderGameOverlayEvent.Pre event){
        if(event.getType() == RenderGameOverlayEvent.ElementType.ALL){
            Entity entity = ModUtils.mc.getCameraEntity();
            if (entity instanceof PlayerEntity) {

            }
        }
    }
}
