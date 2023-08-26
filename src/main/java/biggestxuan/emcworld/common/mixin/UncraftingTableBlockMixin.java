package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twilightforest.block.UncraftingTableBlock;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/08/06
 */

@Mixin(UncraftingTableBlock.class)
@EMCWorldSince("1.0.3")
public abstract class UncraftingTableBlockMixin extends Block {
    public UncraftingTableBlockMixin(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Inject(method = "getMenuProvider",at = @At("HEAD"),cancellable = true)
    public void _inject(BlockState state, World world, BlockPos pos, CallbackInfoReturnable<INamedContainerProvider> cir){
        cir.setReturnValue(null);
    }

    @Inject(method = "use",at = @At("HEAD"),cancellable = true)
    public void __inject(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit, CallbackInfoReturnable<ActionResultType> cir){
        cir.setReturnValue(ActionResultType.FAIL);
    }
}
