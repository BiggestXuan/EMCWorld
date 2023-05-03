package biggestxuan.emcworld.common.blocks;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/09
 */


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

public class EWBlock extends Block {
    public EWBlock(float strength) {
        super(Properties.of(Material.STONE).strength(strength).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops());
    }

    public EWBlock(Properties properties){
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
