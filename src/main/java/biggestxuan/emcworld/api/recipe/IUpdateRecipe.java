package biggestxuan.emcworld.api.recipe;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/24
 */

import net.minecraft.item.ItemStack;

/**
 * Use enum implements this interface to add UpdateRecipe.
 * If recipe level reach 1,this recipe only craft by Advanced Update Core.
 * Input and Output amount always only 1.
 *
 * Not supported JEI Plugin.
 */

public interface IUpdateRecipe {
    ItemStack getInput();

    ItemStack getOutput();

    long costEMC();

    default int recipeLevel(){
        return 0;
    }
}
