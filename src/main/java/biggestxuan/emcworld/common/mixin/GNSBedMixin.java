package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/19
 */

import biggestxuan.emcworld.common.registry.EWItems;
import com.legacy.goodnightsleep.blocks.GNSBedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GNSBedBlock.class)
public abstract class GNSBedMixin {
    @Shadow(remap = false)
    protected abstract void travelToDream(PlayerEntity player, boolean dream);

    @Inject(method = "use",at = @At(value = "INVOKE",target = "Lcom/legacy/goodnightsleep/blocks/GNSBedBlock;travelToDream(Lnet/minecraft/entity/player/PlayerEntity;Z)V",ordinal = 2,remap = false), cancellable = true)
    public void travel(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit, CallbackInfoReturnable<ActionResultType> cir){
        if(player.getMainHandItem().getItem().equals(EWItems.XIANGSHUSHUMIAO_PILLOW.get())){
            travelToDream(player,true);
            cir.setReturnValue(ActionResultType.SUCCESS);
            cir.cancel();
        }
    }
}
