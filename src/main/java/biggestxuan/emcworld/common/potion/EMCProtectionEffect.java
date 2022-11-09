package biggestxuan.emcworld.common.potion;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/10
 */

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.awt.*;

public class EMCProtectionEffect extends Effect {
    public EMCProtectionEffect() {
        super(EffectType.BENEFICIAL,new Color(0xB70CC7).getRGB());
    }
}
