package biggestxuan.emcworld.common.blocks.InfuserBlock;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/13
 */

import biggestxuan.emcworld.api.block.BaseTileBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InfuserBlock extends BaseTileBlock {

    public InfuserBlock() {
        super(7.5F);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new InfuserBlockTileEntity();
    }

    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit){
        if(!worldIn.isClientSide){
            InfuserBlockTileEntity entity = (InfuserBlockTileEntity) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player,entity,(PacketBuffer p)->{
                p.writeBlockPos(pos);
            });
        }
        return ActionResultType.SUCCESS;
    }
}
