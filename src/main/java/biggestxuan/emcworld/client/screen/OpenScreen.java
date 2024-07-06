package biggestxuan.emcworld.client.screen;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/31
 */

@EMCWorldSince("1.0.3")
@OnlyIn(Dist.CLIENT)
public final class OpenScreen {
    public OpenScreen(Screen screen){
        Minecraft.getInstance().setScreen(screen);
    }
}
