package biggestxuan.emcworld.common.blocks.SteelFurnace;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/16
 */

import biggestxuan.emcworld.api.block.BaseTileBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

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
}
