package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/30
 */

@Mixin(EnchantmentHelper.class)
@EMCWorldSince("0.9.0")
public abstract class EnchantmentHelperMixin {
    /**
     * @author Biggest_Xuan
     * @reason Disable Sweeping Edge Enchantment.
     */
    @Overwrite
    public static float getSweepingDamageRatio(LivingEntity p_191527_0_) {
        return 0F;
    }
}
