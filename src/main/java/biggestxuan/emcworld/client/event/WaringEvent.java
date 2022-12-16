package biggestxuan.emcworld.client.event;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/15
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.screen.PCLWarningScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT,modid = EMCWorld.MODID)
public class WaringEvent {
    private static boolean isShow = false;
    @SubscribeEvent
    public static void showPCLWarning(GuiScreenEvent.InitGuiEvent.Post event){
        if(event.getGui() instanceof MainMenuScreen && !isShow && isPCL()){
            isShow = true;
            Minecraft.getInstance().setScreen(new PCLWarningScreen(event.getGui()));
        }
    }

    private static boolean isPCL(){
        String args = System.getProperties().getProperty("minecraft.launcher.brand");
        return args != null && args.equals("PCL2");
    }
}
