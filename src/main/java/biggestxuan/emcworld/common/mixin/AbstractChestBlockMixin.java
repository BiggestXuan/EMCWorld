package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/24
 */

import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.ContainerBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractChestBlock.class)
public abstract class AbstractChestBlockMixin extends ContainerBlock {
    protected AbstractChestBlockMixin(Properties p_i48446_1_) {
        super(p_i48446_1_);
    }

    @Override
    @Deprecated
    public float getExplosionResistance() {
        return 100F;
    }
}
