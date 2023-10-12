package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import moze_intel.projecte.utils.WorldHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.function.Predicate;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/24
 */

@Mixin(WorldHelper.class)
@EMCWorldSince("1.0.5")
public abstract class WorldHelperMixin {
    @Shadow(remap = false)
    private static void repelEntity(Vector3d vec, Entity entity) {
    }

    @Redirect(
            method = "repelEntitiesInterdiction",
            at = @At(value = "INVOKE",target = "Lmoze_intel/projecte/utils/WorldHelper;repelEntity(Lnet/minecraft/util/math/vector/Vector3d;Lnet/minecraft/entity/Entity;)V"),
            remap = false
    )
    private static void __inject(Vector3d vec, Entity entity){
        if(entity instanceof MobEntity){
            repelEntity(vec,entity);
        }
    }
}
