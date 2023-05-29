package biggestxuan.emcworld.common.blocks.Ores;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/30
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class EWStoneOre extends Block {
    public EWStoneOre(int harvestLevel, float strength){
        super(Properties.of(Material.STONE).strength(strength).requiresCorrectToolForDrops().harvestLevel(harvestLevel).harvestTool(ToolType.PICKAXE));
    }
}
