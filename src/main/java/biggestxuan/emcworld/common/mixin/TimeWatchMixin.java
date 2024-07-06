package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/04
 */

import moze_intel.projecte.gameObjs.items.rings.PEToggleItem;
import moze_intel.projecte.gameObjs.items.rings.TimeWatch;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = TimeWatch.class)
public abstract class TimeWatchMixin extends PEToggleItem {

    public TimeWatchMixin(Properties props) {
        super(props);
    }

    private void speedUpTileEntities(World world, int bonusTicks, AxisAlignedBB bBox) {

    }

    private void speedUpRandomTicks(World world, int bonusTicks, AxisAlignedBB bBox) {

    }
}
