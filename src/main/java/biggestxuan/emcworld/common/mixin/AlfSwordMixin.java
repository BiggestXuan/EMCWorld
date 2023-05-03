package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/28
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import mythicbotany.alftools.AlfsteelSword;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AlfsteelSword.class)
public abstract class AlfSwordMixin extends SwordItem {
    public AlfSwordMixin(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    @Override
    public float getDamage() {
        return ConfigManager.FREE_MODE.get() ? 12.0F : 49.0F;
    }
}
