package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/25
 */

import dev.latvian.mods.projectex.Matter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Matter.class)
public abstract class MatterMixin {

    /**
     * @author Biggest_Xuan
     * @reason Disable Power Flower
     */
    @Overwrite(remap = false)
    public long getPowerFlowerOutput() {
        return 0L;
    }
}
