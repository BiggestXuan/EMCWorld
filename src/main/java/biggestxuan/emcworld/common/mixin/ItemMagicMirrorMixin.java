package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/22
 */

import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import cursedflames.bountifulbaubles.common.item.BBItem;
import cursedflames.bountifulbaubles.common.item.items.ItemMagicMirror;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemMagicMirror.class)
public abstract class ItemMagicMirrorMixin extends BBItem {
    public ItemMagicMirrorMixin(String name, Properties props) {
        super(name, props);
    }

    @Inject(method = "doTeleport",at = @At("HEAD"),remap = false, cancellable = true)
    private static void _inject(PlayerEntity player, World origin, World target, double x, double y, double z, CallbackInfo ci){
        long emc = MathUtils.getMirrorTPCost(player,origin,target,x,y,z);
        if(EMCHelper.getPlayerEMC(player) > emc){
            EMCHelper.modifyPlayerEMC(player,new EMCSource.TeleportEMCSource(-emc,player,new BlockPos(x,y,z),0,(long) (MathUtils.getDistance(new BlockPos(player.position()),new BlockPos(x,y,z)))),true);
        }else{
            ci.cancel();
        }
    }
}
