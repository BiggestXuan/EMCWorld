package biggestxuan.emcworld.common.blocks.Ores;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/30
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class EWDirtOre extends Block {
    public EWDirtOre(int harvestLevel, float strength){
        super(Properties.of(Material.DIRT).strength(strength).requiresCorrectToolForDrops().harvestLevel(harvestLevel).harvestTool(ToolType.SHOVEL));
    }
}
