package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/23
 */

import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.blay09.mods.waystones.api.IWaystone;
import net.blay09.mods.waystones.core.PlayerWaystoneManager;
import net.blay09.mods.waystones.core.WarpMode;
import net.blay09.mods.waystones.core.WaystoneTeleportContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerWaystoneManager.class)
public abstract class PlayerWaystoneManagerMixin {
    @Redirect(method = "tryTeleportToWaystone",
            at = @At(value = "INVOKE", target = "Lnet/blay09/mods/waystones/core/PlayerWaystoneManager;getExperienceLevelCost(Lnet/minecraft/entity/Entity;Lnet/blay09/mods/waystones/api/IWaystone;Lnet/blay09/mods/waystones/core/WarpMode;Lnet/blay09/mods/waystones/core/WaystoneTeleportContext;)I"),
            remap = false)
    private static int cost(Entity xpLevelCost, IWaystone entity, WarpMode waystone, WaystoneTeleportContext warpMode){
        return 0;
    }

    @Inject(method = "tryTeleportToWaystone",
            at = @At(value = "INVOKE",target = "Lnet/blay09/mods/waystones/core/WaystoneTeleportContext;setLeashedEntities(Ljava/util/List;)V"),
            remap = false,
            cancellable = true)
    private static void playerHasEnoughEMC(Entity entity, IWaystone waystone, WarpMode warpMode, IWaystone fromWaystone, CallbackInfoReturnable<Boolean> cir){
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            long costEMC = MathUtils.getTPEMCCost(player,waystone,fromWaystone);
            if(EMCHelper.getPlayerEMC(player) < costEMC){
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "tryTeleportToWaystone",
            at = @At(value = "INVOKE",target = "Lnet/blay09/mods/waystones/core/PlayerWaystoneManager;teleportToWaystone(Lnet/minecraft/entity/Entity;Lnet/blay09/mods/waystones/api/IWaystone;Lnet/blay09/mods/waystones/core/WaystoneTeleportContext;)V"),
            remap = false)
    private static void costEMC(Entity entity, IWaystone waystone, WarpMode warpMode, IWaystone fromWaystone, CallbackInfoReturnable<Boolean> cir){
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            EMCHelper.modifyPlayerEMC(player,Math.negateExact(MathUtils.getTPEMCCost(player,waystone,fromWaystone)),true);
        }
    }
}
