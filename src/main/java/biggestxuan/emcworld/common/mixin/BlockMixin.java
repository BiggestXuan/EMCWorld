package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/23
 */

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Shadow
    private String descriptionId;

    @Inject(method = "getDescriptionId",at = @At("TAIL"),cancellable = true)
    public void name(CallbackInfoReturnable<String> cir){
        if(descriptionId.equals("block.bloodmagic.dungeon_metal")){
            cir.setReturnValue("block.emcworld.dungeon_metal");
        }
    }
}
