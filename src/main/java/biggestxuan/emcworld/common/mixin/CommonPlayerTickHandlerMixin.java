package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/15
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import mekanism.common.CommonPlayerTickHandler;
import mekanism.common.item.gear.ItemJetpack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BooleanSupplier;

@Mixin(CommonPlayerTickHandler.class)
public abstract class CommonPlayerTickHandlerMixin {
    @Inject(method = "isJetpackOn",at = @At("HEAD"),remap = false, cancellable = true)
    @EMCWorldSince("1.0.2")
    private static void __inject(PlayerEntity player, ItemStack chest, CallbackInfoReturnable<Boolean> cir){
        if(yes(player)){
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "handleJetpackMotion",at = @At("HEAD"),remap = false,cancellable = true)
    @EMCWorldSince("1.0.2")
    private static void __inject(PlayerEntity player, ItemJetpack.JetpackMode mode, BooleanSupplier ascendingSupplier, CallbackInfoReturnable<Boolean> cir){
        if(!player.isDeadOrDying() && yes(player)){
            cir.setReturnValue(false);
        }
    }

    private static boolean yes(PlayerEntity player){
        IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(player);
        return cap.gaiaPlayer() >= 1;
    }
}
