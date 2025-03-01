package biggestxuan.emcworld.common.mixin;

import net.minecraft.tileentity.TileEntityType;
import net.permutated.pylons.tile.AbstractPylonTile;
import net.permutated.pylons.tile.InfusionPylonTile;
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
@Mixin(InfusionPylonTile.class)
public abstract class InfusionPylonTileMixin extends AbstractPylonTile {
    protected InfusionPylonTileMixin(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    @Inject(method = "tick",at = @At("HEAD"),cancellable = true)
    public void _inject(CallbackInfo ci){
        ci.cancel();
    }
}
