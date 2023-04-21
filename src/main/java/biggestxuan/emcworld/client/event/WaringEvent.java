package biggestxuan.emcworld.client.event;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/15
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.screen.AddModScreen;
import biggestxuan.emcworld.client.screen.PCLWarningScreen;
import biggestxuan.emcworld.common.utils.ModUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT,modid = EMCWorld.MODID)
public class WaringEvent {
    private static boolean isShowPCL = false;
    private static boolean isShowAddMod = false;
    private static boolean isShowAlpha = false;
    @SubscribeEvent
    public static void showMenu(GuiScreenEvent.InitGuiEvent.Post event){
        Screen mainMenu = event.getGui();
        if(event.getGui() instanceof MainMenuScreen){
            Screen PCLScreen = new PCLWarningScreen(mainMenu);
            if(addMod() && !isShowAddMod){
                Screen s = !isPCL() ? PCLScreen : mainMenu;
                Screen modMenu = new AddModScreen(s);
                Minecraft.getInstance().setScreen(modMenu);
                isShowAddMod = true;
                return;
            }
            if(!isShowPCL){
                Minecraft.getInstance().setScreen(new PCLWarningScreen(event.getGui()));
            }
            isShowPCL = true;
        }
    }

    private static boolean isPCL(){
        String args = System.getProperties().getProperty("minecraft.launcher.brand");
        return args != null && args.equals("PCL2");
    }

    private static boolean addMod(){
        return ModUtils.addMod();
    }

    private static boolean isAlpha(){
        return Integer.parseInt(EMCWorld.PackVersion.split("\\.")[0]) == 0;
    }
}
