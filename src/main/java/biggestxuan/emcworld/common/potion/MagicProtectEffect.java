package biggestxuan.emcworld.common.potion;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/26
 */

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.awt.*;

public class MagicProtectEffect extends Effect {
    public MagicProtectEffect() {
        super(EffectType.BENEFICIAL,new Color(0x9392C4).getRGB());
    }
}
