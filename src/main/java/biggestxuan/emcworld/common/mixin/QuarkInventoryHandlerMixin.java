package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/01
 */

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.quark.base.handler.InventoryTransferHandler;

@Mixin(InventoryTransferHandler.class)
public abstract class QuarkInventoryHandlerMixin {
    @Inject(
            method = "accepts",
            at = @At(value ="HEAD"),
            remap = false,
            cancellable = true
    )
    private static void accepts(Container container, PlayerEntity player, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
    }
}
