package biggestxuan.emcworld.common.mixin;

import net.mehvahdjukaar.dummmmmmy.entity.TargetDummyEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/26
 */

@Mixin(TargetDummyEntity.class)
public abstract class TargetDummyEntityMixin extends MobEntity {
    protected TargetDummyEntityMixin(EntityType<? extends MobEntity> p_i48576_1_, World p_i48576_2_) {
        super(p_i48576_1_, p_i48576_2_);
    }

    /**
     * @author Biggest_Xuan
     * @reason Fix a bug
     */
    @Overwrite
    @Override
    public ActionResultType interactAt(PlayerEntity player, Vector3d vec, Hand hand) {
        return ActionResultType.FAIL;
    }
}
