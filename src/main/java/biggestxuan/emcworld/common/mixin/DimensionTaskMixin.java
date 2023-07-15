package biggestxuan.emcworld.common.mixin;

import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbquests.quest.TeamData;
import dev.ftb.mods.ftbquests.quest.task.BooleanTask;
import dev.ftb.mods.ftbquests.quest.task.DimensionTask;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/18
 */

@Mixin(value = DimensionTask.class,remap = false)
public abstract class DimensionTaskMixin extends BooleanTask {
    @Shadow public RegistryKey<World> dimension;

    public DimensionTaskMixin(Quest q) {
        super(q);
    }

    /**
     * @author Biggest_Xuan
     * @reason Fix a bug
     */
    @Overwrite
    @Override
    public boolean canSubmit(TeamData teamData, ServerPlayerEntity player) {
        return !player.isSpectator() && player.level.dimension() == this.dimension && teamData.areDependenciesComplete(this.quest);
    }
}
