package biggestxuan.emcworld.client.event;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/27
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientTickEvent {
    public static final boolean isCrash = true;
    //@SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void clientTick(TickEvent.ClientTickEvent event){
        //GLFW.glfwSetWindowTitle(Minecraft.getInstance().getWindow().getWindow(), EMCWorld.TITLE);
    }

    /**
     * If you are not a sponsor or developer, the Minecraft will crash when you join the world.
     * The EMCWorld has not been completed yet. I don't want others to play it.
     * The main reason is that if anyone can play the EMCWorld, I will receive a lot of negative or garbage feedback (because the EMCWorld is not completed).
     * You can find it here, probably through the crash report.
     * If you want to delete the crash code and rebuild the mod, please do so.
     * I just hope not to make too many comments on some unfinished things.
     *
     * Of course, if you want to join our development, we are very welcome.
     * Please leave me a message at GitHub or other places.
     */

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void clientLowTick(TickEvent.ClientTickEvent event){
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(player == null || player.level == null){
            return;
        }
        if(player.level.isClientSide && !player.isDeadOrDying() && !player.level.dimension().getRegistryName().equals(new ResourceLocation("nether"))){
            IUtilCapability c = EMCWorldAPI.getInstance().getUtilCapability(player);
            if(c.getLevel() <2){
                //CrashReport crashReport = new CrashReport("EMCWorld is not completed!", new EMCWorldNotFinalException());
                //Minecraft.crash(crashReport);
            }
        }
    }
}
