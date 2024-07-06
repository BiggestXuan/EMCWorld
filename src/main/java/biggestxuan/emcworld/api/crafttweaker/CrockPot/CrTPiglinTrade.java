package biggestxuan.emcworld.api.crafttweaker.CrockPot;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/28
 */

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.sihenzhang.crockpot.recipe.CrockPotRecipeTypes;
import com.sihenzhang.crockpot.recipe.WeightedItem;
import com.sihenzhang.crockpot.recipe.bartering.PiglinBarteringRecipe;
import net.minecraft.item.crafting.IRecipeType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.CrTPiglinTrade")
@SuppressWarnings("unused")
public class CrTPiglinTrade implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient input, CrTWeightItem[] outputs){
        List<WeightedItem> list = new ArrayList<>();
        Arrays.stream(outputs).forEach(w -> list.add(w.get()));
        PiglinBarteringRecipe recipe = new PiglinBarteringRecipe(EMCWorld.rl(name),input.asVanillaIngredient(),list);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @Override
    public IRecipeType<PiglinBarteringRecipe> getRecipeType() {
        return CrockPotRecipeTypes.PIGLIN_BARTERING_RECIPE_TYPE;
    }
}
