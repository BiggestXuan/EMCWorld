package biggestxuan.emcworld.client.key;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.network.ArcanaDisplayPacket;
import biggestxuan.emcworld.common.network.PacketHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ArcanaDisplay {
    public static final KeyBinding ArcanaKey = new KeyBinding("key.emcworld.arcana",
            KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_EQUAL,
            "key.category." + EMCWorld.MODID);

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if(ArcanaKey.isDown()){
            PacketHandler.sendToServer(new ArcanaDisplayPacket());
        }
    }
}
