package biggestxuan.emcworld.common.compact.JEI.EMCOre;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.recipes.EMCOreRecipe;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/10
 */

@EMCWorldSince("1.0.5")
public class EMCOreJEIRecipe {
    public final List<EMCOreRecipe> allRecipe;
    private final int index;

    public EMCOreJEIRecipe(int level){
        allRecipe = EMCOreRecipe.getRequireLevelRecipe(level);
        this.index = level;
    }

    public int getIndex() {
        return index;
    }

    public List<WeightItemStack> getOutputs(){
        List<WeightItemStack> list = new ArrayList<>();
        int weight = EMCOreRecipe.getTotalWeight(index);
        for(var r : allRecipe){
            list.add(new WeightItemStack(r.getOutput(),1.0D*r.getWeight()/weight));
        }
        return list;
    }

    public List<ItemStack> getOutputsByItemStack(){
        List<ItemStack> list = new ArrayList<>();
        allRecipe.forEach(e -> list.add(e.getOutput()));
        return list;
    }

    public static final class WeightItemStack{
        public final ItemStack stack;
        public final double chance;

        private WeightItemStack(ItemStack stack,double chance){
            this.stack = stack;
            this.chance = chance;
        }
    }
}
