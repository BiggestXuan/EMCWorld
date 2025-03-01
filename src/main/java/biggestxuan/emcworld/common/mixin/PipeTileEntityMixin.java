package biggestxuan.emcworld.common.mixin;

import de.maxhenkel.pipez.blocks.tileentity.PipeTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;


/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/15
 */
@Mixin(PipeTileEntity.class)
public abstract class PipeTileEntityMixin extends TileEntity {
    @Shadow(remap = false)
    @Nullable
    protected List<PipeTileEntity.Connection> connectionCache;

    public PipeTileEntityMixin(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Unique
    private int invalidateCountdown;

    @Inject(method = "tick",at = @At("HEAD"))
    public void _tick(CallbackInfo ci){
        if (invalidateCountdown >= 0) {
            invalidateCountdown--;
            if (invalidateCountdown <= 0) {
                connectionCache = null;
            }
        }
    }

    @Inject(method = "isExtracting()Z",at = @At("TAIL"),remap = false)
    public void _isExtracting(CallbackInfoReturnable<Boolean> cir){
        invalidateCountdown = 10;
    }
}
