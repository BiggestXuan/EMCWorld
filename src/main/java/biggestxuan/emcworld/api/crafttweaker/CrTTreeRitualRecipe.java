package biggestxuan.emcworld.api.crafttweaker;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/17
 */

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveRecipeByOutput;
import com.blamejared.crafttweaker.impl.item.MCItemStackMutable;
import de.ellpeck.naturesaura.recipes.ModRecipes;
import de.ellpeck.naturesaura.recipes.TreeRitualRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.treeRitual")
public class CrTTreeRitualRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient tree,IItemStack output,IIngredient... ingredients){
        Ingredient[] act = new Ingredient[ingredients.length];
        for (int i = 0; i < ingredients.length; i++) {
            act[i] = ingredients[i].asVanillaIngredient();
        }
        TreeRitualRecipe recipe = new TreeRitualRecipe(EMCWorld.rl(name),tree.asVanillaIngredient(),output.getInternal(),100,act);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @ZenCodeType.Method
    public void removeRecipe(IItemStack output){
        CraftTweakerAPI.apply(new ActionRemoveRecipeByOutput(this,output){
            @Override
            public void apply(){
                List<ResourceLocation> toRemove = new ArrayList<>();
                for(ResourceLocation location : getManager().getRecipes().keySet()) {
                    IRecipe<?> recipe = getManager().getRecipes().get(location);
                    if(recipe instanceof TreeRitualRecipe) {
                        TreeRitualRecipe recipeTR = (TreeRitualRecipe) recipe;
                        ItemStack recipeOutput = recipeTR.getResultItem();
                        if(output.matches(new MCItemStackMutable(recipeOutput))) {
                            toRemove.add(location);
                        }
                    }
                }
                toRemove.forEach(getManager().getRecipes()::remove);
            }
        });
    }

    @Override
    public IRecipeType<TreeRitualRecipe> getRecipeType() {
        return ModRecipes.TREE_RITUAL_TYPE;
    }
}
