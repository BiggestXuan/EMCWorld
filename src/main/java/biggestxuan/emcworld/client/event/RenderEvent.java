package biggestxuan.emcworld.client.event;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import biggestxuan.emcworld.EMCWorld;
import mcp.mobius.waila.api.ICommonAccessor;
import mcp.mobius.waila.api.event.WailaRenderEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tfar.classicbar.ModUtils;
//import top.theillusivec4.champions.common.capability.ChampionCapability;

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

    @SubscribeEvent
    public static void renderEvent(WailaRenderEvent.Pre event){
        ICommonAccessor accessor = event.getAccessor();
        if(accessor.getEntity() != null){
            if(accessor.getEntity() instanceof LivingEntity){
                LivingEntity living = (LivingEntity) accessor.getEntity();
                /*ChampionCapability.getCapability(living).ifPresent(iChampion -> iChampion.getClient().getRank().ifPresent(rank -> {
                    if(rank.getA() >= 1){
                        event.setCanceled(true);
                    }
                }));
                return;*/
            }
            event.setCanceled(true);
        }
    }
}
