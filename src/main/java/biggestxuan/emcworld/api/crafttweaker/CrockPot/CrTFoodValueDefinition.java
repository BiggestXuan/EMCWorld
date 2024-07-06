package biggestxuan.emcworld.api.crafttweaker.CrockPot;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/29
 */

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.sihenzhang.crockpot.recipe.CrockPotRecipeTypes;
import com.sihenzhang.crockpot.recipe.FoodValuesDefinition;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashSet;
import java.util.Set;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.FoodValue.Definition")
public class CrTFoodValueDefinition implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient item, CrTFoodValue values, boolean isTag){
        FoodValuesDefinition definition = new FoodValuesDefinition(EMCWorld.rl(name),rl(item),values.get(),isTag);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,definition,""));
    }

    @Override
    public IRecipeType<FoodValuesDefinition> getRecipeType() {
        return CrockPotRecipeTypes.FOOD_VALUES_RECIPE_TYPE;
    }

    private Set<ResourceLocation> rl(IIngredient stack){
        HashSet<ResourceLocation> out = new HashSet<>();
        for(IItemStack s : stack.getItems()){
            ResourceLocation rl = s.getRegistryName();
            out.add(rl);
        }
        return out;
    }
}
