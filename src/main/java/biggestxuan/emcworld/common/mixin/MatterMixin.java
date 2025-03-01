package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/25
 */

import dev.latvian.mods.projectex.Matter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Matter.class)
public abstract class MatterMixin {

    @Shadow(remap = false)
    public long collectorOutput;

    /**
     * @author Biggest_Xuan
     * @reason None
     */
    @Overwrite(remap = false)
    public long getPowerFlowerOutput() {
        return (long) (this.collectorOutput / 4D);
    }
}
