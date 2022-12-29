package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/23
 */

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow
    private String descriptionId;
    @Inject(method = "getOrCreateDescriptionId",at = @At("TAIL"),cancellable = true)
    //I18n translated non-Chinese items into English, resulting in having to modify their local keys.
    public void name(CallbackInfoReturnable<String> cir){
        if(descriptionId.equals("item.bloodmagic.ingot_hellforged")){
            cir.setReturnValue("item.emcworld.ingot_hellforged");
        }
        if(descriptionId.equals("item.bloodmagic.sand_hellforged")){
            cir.setReturnValue("item.emcworld.sand_hellforged");
        }
    }
}
