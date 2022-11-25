package biggestxuan.emcworld.common.blocks.GemstoneBlock;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/16
 */

import biggestxuan.emcworld.api.block.BaseTileBlock;
import biggestxuan.emcworld.common.blocks.EWBlock;
import biggestxuan.emcworld.common.utils.Network.Network;
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

public class GemstoneBlock extends BaseTileBlock {
    public GemstoneBlock() {
        super(12f);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GemstoneTileEntity();
    }

    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
        if(!worldIn.isClientSide){
            GemstoneTileEntity tile = (GemstoneTileEntity) worldIn.getBlockEntity(pos);
            if(player.isShiftKeyDown() && tile != null){
                tile.setState(GemstoneTileEntity.STATE.START);
                return ActionResultType.SUCCESS;
            }
            NetworkHooks.openGui((ServerPlayerEntity) player,tile,(PacketBuffer p) -> p.writeBlockPos(pos));
        }
        return ActionResultType.SUCCESS;
    }
}