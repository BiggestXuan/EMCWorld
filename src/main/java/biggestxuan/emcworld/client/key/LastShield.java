package biggestxuan.emcworld.client.key;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/27
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.screen.AdminScreen;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.network.LastShieldPacket;
import biggestxuan.emcworld.common.network.PacketHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class LastShield {
    public static final KeyBinding Last_Shield = new KeyBinding("key.emcworld.last_shield",
            KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM,
            336,
            "key.category." + EMCWorld.MODID);

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if(Last_Shield.isDown()){
            PacketHandler.sendToServer(new LastShieldPacket());
        }
    }
}
