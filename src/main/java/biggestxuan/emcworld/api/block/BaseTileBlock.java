package biggestxuan.emcworld.api.block;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/30
 */

import biggestxuan.emcworld.common.blocks.EWBlock;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class BaseTileBlock extends EWBlock {
    public BaseTileBlock(Properties properties) {
        super(properties);
    }

    public BaseTileBlock(float strengthen){
        super(strengthen);
    }

    @Override
    public boolean hasTileEntity(BlockState state){
        return true;
    }

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

    @Override
    public void onRemove(BlockState pState, World pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            TileEntity blockEntity = pLevel.getBlockEntity(pPos);
            if(blockEntity instanceof BaseContainerTileEntity){
                BaseContainerTileEntity tile = (BaseContainerTileEntity) blockEntity;
                drop(tile);
            }
        }
        super.onRemove(pState,pLevel,pPos,pNewState,pIsMoving);
    }

    private static <T extends BaseContainerTileEntity> void drop(T tile){
        if(tile.getLevel() == null) return;
        InventoryHelper.dropContents(tile.getLevel(),tile.getBlockPos(),tile.getInventory());
    }
}
