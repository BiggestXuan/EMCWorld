package biggestxuan.emcworld.client.key;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.toServer.ArcanaDisplayPacket;
import biggestxuan.emcworld.common.network.toServer.LiveModePacket;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class LiveMode {
    public static final KeyBinding LiveMode = new KeyBinding("key.emcworld.live_mode",
            KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM,
            336,
            "key.category." + EMCWorld.MODID);

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if(LiveMode.isDown()){
            PacketHandler.sendToServer(new LiveModePacket());
        }
    }
}
