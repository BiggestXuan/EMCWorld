package biggestxuan.emcworld.common.blocks;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/26
 */

import biggestxuan.emcworld.api.block.BaseTileBlock;
import biggestxuan.emcworld.common.blocks.tile.SuperEMCTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SuperEMCBlock extends BaseTileBlock {
    public SuperEMCBlock() {
        super(6F);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SuperEMCTileEntity();
    }

    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
        if(!worldIn.isClientSide){
            SuperEMCTileEntity tile = (SuperEMCTileEntity) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player,tile,(PacketBuffer p) -> p.writeBlockPos(pos));
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getVisualShape(BlockState p_230322_1_, IBlockReader p_230322_2_, BlockPos p_230322_3_, ISelectionContext p_230322_4_) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_200123_1_, IBlockReader p_200123_2_, BlockPos p_200123_3_) {
        return true;
    }
}
