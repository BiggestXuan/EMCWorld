package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.IItemHandler;
import net.permutated.pylons.tile.AbstractPylonTile;
import net.permutated.pylons.tile.HarvesterPylonTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/8/2
 */
@Mixin(value = HarvesterPylonTile.class)
public abstract class HarvesterPylonTileMixin extends AbstractPylonTile {
    protected HarvesterPylonTileMixin(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    @Inject(method = "insertItemOrDiscard",at = @At("HEAD"),remap = false)
    private void _inject(IItemHandler itemHandler, ItemStack itemStack, CallbackInfoReturnable<Boolean> cir){
        var uuid = getOwner();
        if(uuid != null && getLevel() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)getLevel();
            PlayerEntity player = serverWorld.getPlayerByUUID(uuid);
            long cost = - (long) (MathUtils.getPickUpItemBaseCost(player) * ConfigManager.DIFFICULTY.get() * itemStack.getCount() * 64 / itemStack.getMaxStackSize());
            //EMCHelper.modifyPlayerEMC(player,new EMCSource.PickItemEMCSource(cost,player,itemStack,0));
        }
    }

    @Inject(method = "tick",at = @At("HEAD"),cancellable = true)
    public void __tick(CallbackInfo ci){
        ci.cancel();
    }
}
