package biggestxuan.emcworld.common.potion;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/14
 */

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.awt.*;

public class EMCBrokenEffect extends Effect {
    public EMCBrokenEffect() {
        super(EffectType.HARMFUL, new Color(0x7C49BB).getRGB());
    }
}
