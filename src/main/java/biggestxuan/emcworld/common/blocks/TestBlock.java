package biggestxuan.emcworld.common.blocks;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/27
 */

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;

public class TestBlock extends Block {
    public TestBlock() {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).harvestLevel(0).strength(7F));
    }

    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState p_225533_1_, @Nonnull World p_225533_2_, @Nonnull BlockPos p_225533_3_, @Nonnull PlayerEntity p_225533_4_, @Nonnull Hand p_225533_5_, @Nonnull BlockRayTraceResult p_225533_6_) {
        ItemStack s = p_225533_4_.getItemInHand(p_225533_5_);
        if(!p_225533_2_.isClientSide){
        }
        return ActionResultType.SUCCESS;
    }
}
