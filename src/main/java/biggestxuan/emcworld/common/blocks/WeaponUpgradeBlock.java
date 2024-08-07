package biggestxuan.emcworld.common.blocks;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.api.block.BaseTileBlock;
import biggestxuan.emcworld.common.blocks.tile.WeaponUpgradeBlockTileEntity;
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

public class WeaponUpgradeBlock extends BaseTileBlock {
    public WeaponUpgradeBlock() {
        super(10.0F);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new WeaponUpgradeBlockTileEntity();
    }

    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit){
        if(!worldIn.isClientSide){
            if(player.isShiftKeyDown()){
                WeaponUpgradeBlockTileEntity entity = (WeaponUpgradeBlockTileEntity) worldIn.getBlockEntity(pos);
                if(entity != null){
                    entity.lastClick = player;
                    entity.setStates(WeaponUpgradeBlockTileEntity.States.STARTING);
                    return ActionResultType.SUCCESS;
                }
            }
            WeaponUpgradeBlockTileEntity entity = (WeaponUpgradeBlockTileEntity) worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player,entity,(PacketBuffer p)-> p.writeBlockPos(pos));
        }
        return ActionResultType.SUCCESS;
    }
}
