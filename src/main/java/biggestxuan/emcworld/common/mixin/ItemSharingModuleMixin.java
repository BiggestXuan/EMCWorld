package biggestxuan.emcworld.common.mixin;

import com.sihenzhang.crockpot.item.CrockPotBlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.content.management.module.ItemSharingModule;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/28
 */
@Mixin(value = ItemSharingModule.class,remap = false)
public class ItemSharingModuleMixin {
    @Inject(method = "linkItem", at = @At("HEAD"),cancellable = true)
    private static void _inject(PlayerEntity player, ItemStack item, CallbackInfo ci){
        if(item.getItem() instanceof CrockPotBlockItem){
            ci.cancel();
        }
    }
}
