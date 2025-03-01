package biggestxuan.emcworld.common.mixin;

import net.minecraft.tileentity.TileEntityType;
import net.permutated.pylons.tile.AbstractPylonTile;
import net.permutated.pylons.tile.ExpulsionPylonTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/8/2
 */
@Mixin(ExpulsionPylonTile.class)
public abstract class ExpilsionPylonTileMixin extends AbstractPylonTile {
    public ExpilsionPylonTileMixin(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Inject(method = "tick",at = @At("HEAD"),cancellable = true)
    public void _inject(CallbackInfo ci){
        ci.cancel();
    }
}
