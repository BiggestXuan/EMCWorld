package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/30
 */

import biggestxuan.emcworld.common.registry.EWBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import org.spongepowered.asm.mixin.Mixin;
import twilightforest.block.MazestoneBlock;
import twilightforest.item.MazebreakerPickItem;

@Mixin(MazebreakerPickItem.class)
public abstract class MazePickaxeMixin extends PickaxeItem {
    public MazePickaxeMixin(IItemTier p_i48478_1_, int p_i48478_2_, float p_i48478_3_, Properties p_i48478_4_) {
        super(p_i48478_1_, p_i48478_2_, p_i48478_3_, p_i48478_4_);
    }

    @Override
    public float getDestroySpeed(ItemStack p_150893_1_, BlockState p_150893_2_){
        float speed = super.getDestroySpeed(p_150893_1_,p_150893_2_);
        return p_150893_2_.getBlock() instanceof MazestoneBlock || p_150893_2_.getBlock().equals(EWBlocks.HARDCORE_STONE.get()) ? speed *16f : speed;
    }
}
