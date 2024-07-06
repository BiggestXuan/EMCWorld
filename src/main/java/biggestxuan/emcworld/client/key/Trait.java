package biggestxuan.emcworld.client.key;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/27
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.toServer.LastShieldPacket;
import biggestxuan.emcworld.common.network.toServer.TraitActivePacket;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
@EMCWorldSince("1.1.0")
public class Trait {
    public static final KeyBinding Trait = new KeyBinding("key.emcworld.trait",
            KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM,
            336,
            "key.category." + EMCWorld.MODID); //todo translation key

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        if(Trait.isDown()){
            PacketHandler.sendToServer(new TraitActivePacket());
        }
    }
}
