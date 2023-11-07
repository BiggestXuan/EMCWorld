package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.common.utils.MathUtils;
import moze_intel.projecte.gameObjs.tiles.InterdictionTile;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.entity.EntityDoppleganger;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/15
 */

@Mixin(InterdictionTile.class)
public abstract class InterdictionTileMixin extends TileEntity implements ITickableTileEntity {
    public InterdictionTileMixin(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Inject(method = "tick",at = @At("HEAD"),cancellable = true)
    public void __inject(CallbackInfo ci){
        if(level != null && !level.isClientSide){
            if(level.getLoadedEntitiesOfClass(EntityDoppleganger.class,MathUtils.expandAABB(worldPosition,15)).size() >= 1){
                ci.cancel();
            }
        }
    }
}
