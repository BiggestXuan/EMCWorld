package biggestxuan.emcworld.common.blocks.VisConversionBlock;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/30
 */

import biggestxuan.emcworld.api.block.BaseTileBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class VisConversionBlock extends BaseTileBlock {
    public VisConversionBlock(){
        super(10.0f);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new VisConversionTileEntity();
    }
}
