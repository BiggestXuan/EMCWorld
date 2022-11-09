package biggestxuan.emcworld.common.compact.JEI.AdvanceUpdate;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/19
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class JEIAdvancedUpdateRecipe {
    private final ResourceLocation rl;
    private final ItemStack input;
    private final ItemStack output;
    private final long costEMC;
    private final int level;

    public JEIAdvancedUpdateRecipe(ItemStack input, ItemStack output, long costEMC, int level) {
        this.rl = EMCWorld.rl("advanced_update/"+output.getItem().getRegistryName().getPath());
        this.input = input;
        this.output = output;
        this.costEMC = costEMC;
        this.level = level;
    }

    public ItemStack getInput(){return input;}
    public ItemStack getOutput(){return output;}
    public long getCostEMC() {
        return costEMC;
    }
    public int getLevel(){
        return level;
    }
    public ResourceLocation getRl(){
        return rl;
    }
}
