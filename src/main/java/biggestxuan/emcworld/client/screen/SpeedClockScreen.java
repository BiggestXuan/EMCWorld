package biggestxuan.emcworld.client.screen;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/31
 */

@OnlyIn(Dist.CLIENT)
@EMCWorldSince("1.0.3")
public class SpeedClockScreen extends Screen {
    public SpeedClockScreen() {
        super(EMCWorld.tc("screen.emcworld.speed_clock"));
    }
}