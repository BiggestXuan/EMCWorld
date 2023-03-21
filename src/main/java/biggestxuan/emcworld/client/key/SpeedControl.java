package biggestxuan.emcworld.client.key;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/28
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.toServer.SpeedControlPacket;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class SpeedControl {
    public static final KeyBinding SPEED_KEY = new KeyBinding("key.emcworld.speed",
            KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM,
            336,
            "key.category." + EMCWorld.MODID);

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if(SPEED_KEY.isDown()){
            PacketHandler.sendToServer(new SpeedControlPacket());
        }
    }
}
