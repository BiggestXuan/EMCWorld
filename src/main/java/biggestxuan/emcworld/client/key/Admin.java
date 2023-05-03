package biggestxuan.emcworld.client.key;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/27
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.screen.AdminScreen;
import biggestxuan.emcworld.common.config.ConfigManager;
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
public class Admin {
    public static final KeyBinding ADMIN_KEY = new KeyBinding("key.emcworld.admin",
            KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM,
            336,
            "key.category." + EMCWorld.MODID);

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if(ADMIN_KEY.isDown() && ConfigManager.ADMIN_MENU.get()){
            DistExecutor.safeCallWhenOn(Dist.CLIENT,()-> AdminScreen.open::new);
        }
    }
}
