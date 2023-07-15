package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.item.ItemKeepIvy;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/06/28
 */

@Mixin(value = ItemKeepIvy.class,remap = false)
@EMCWorldSince("0.9.0")
public abstract class ItemKeepIvyMixin extends Item {
    public ItemKeepIvyMixin(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Inject(method = "onPlayerDrops",at = @At("HEAD"),cancellable = true)
    private static void _inject(LivingDropsEvent event, CallbackInfo ci){
        ci.cancel();
    }

    @Inject(method = "onPlayerDrops",at = @At("HEAD"),cancellable = true)
    private static void __inject(LivingDropsEvent event, CallbackInfo ci){
        ci.cancel();
    }
}
