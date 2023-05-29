package biggestxuan.emcworld.common.compact.JEI.Update;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/09
 */

import net.minecraft.item.ItemStack;

public class JEIUpdateRecipe {
    private final ItemStack input;
    private final ItemStack output;
    private final long costEMC;

    public JEIUpdateRecipe(ItemStack input, ItemStack output, long costEMC) {
        this.input = input;
        this.output = output;
        this.costEMC = costEMC;
    }

    public ItemStack getInput(){return input;}
    public ItemStack getOutput(){return output;}

    public long getCostEMC() {
        return costEMC;
    }
}