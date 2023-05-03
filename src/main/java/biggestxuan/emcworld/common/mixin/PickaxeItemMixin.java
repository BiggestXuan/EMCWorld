package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/19
 */

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(PickaxeItem.class)
public abstract class PickaxeItemMixin extends ToolItem {
    public PickaxeItemMixin(float p_i48512_1_, float p_i48512_2_, IItemTier p_i48512_3_, Set<Block> p_i48512_4_, Properties p_i48512_5_) {
        super(p_i48512_1_, p_i48512_2_, p_i48512_3_, p_i48512_4_, p_i48512_5_);
    }

    @Inject(method = "getDestroySpeed",at = @At("HEAD"),cancellable = true)
    public void speed(ItemStack p_150893_1_, BlockState p_150893_2_, CallbackInfoReturnable<Float> cir){
        cir.setReturnValue(super.getDestroySpeed(p_150893_1_,p_150893_2_));
        cir.cancel();
    }
}
