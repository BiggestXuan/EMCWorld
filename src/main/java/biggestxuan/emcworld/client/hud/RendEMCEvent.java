package biggestxuan.emcworld.client.hud;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/31
 */

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class RendEMCEvent {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onOverlayRender(RenderGameOverlayEvent event){
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (player == null || player.isDeadOrDying()) {
            return;
        }
        EMCWorldHUD gui = new EMCWorldHUD(event.getMatrixStack());
        gui.render(player);
    }
}
