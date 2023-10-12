package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.patchouli.common.util.ItemStackUtil;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/09
 */

@EMCWorldSince("1.0.5")
@Mixin(ItemStackUtil.class)
public class ItemStackUtilMixin {
    @Inject(method = "loadStackFromString",at = @At("HEAD"),cancellable = true,remap = false)
    private static void _inject(String res, CallbackInfoReturnable<ItemStack> cir){
        if(res.equals("naturesaura:altar")){
            cir.setReturnValue(EMCWorld.getItem("naturesaura:nature_altar"));
        }
    }
}
