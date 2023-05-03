package biggestxuan.emcworld.common.blocks.SteelFurnace;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/16
 */

import biggestxuan.emcworld.api.block.BaseTileBlock;
import biggestxuan.emcworld.common.blocks.MultiBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SteelFurnaceCore extends BaseTileBlock {
    public SteelFurnaceCore() {
        super(7.5F);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SteelFurnaceTileEntity();
    }

    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
        if(!worldIn.isClientSide){
            if(worldIn.getBlockEntity(pos) instanceof SteelFurnaceTileEntity){
                SteelFurnaceTileEntity tile = (SteelFurnaceTileEntity) worldIn.getBlockEntity(pos);
                if(MultiBlock.SteelFurnaceCanCraft(worldIn,pos)){
                    //NetworkHooks.openGui((ServerPlayerEntity) player,tile,(PacketBuffer p) -> p.writeBlockPos(pos));
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
