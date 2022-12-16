package biggestxuan.emcworld.api.crafttweaker.CrockPot;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/16
 */

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.sihenzhang.crockpot.recipe.CrockPotRecipeTypes;
import com.sihenzhang.crockpot.recipe.cooking.CrockPotCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.CookPotRecipe")
@SuppressWarnings("unused")
public class CrTCookPotRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, CrTRequirement requirement, IItemStack output,int priority, int weight, int cookingTime, int potLevel){
        ResourceLocation rl = EMCWorld.rl(name);
        CrockPotCookingRecipe recipe = new CrockPotCookingRecipe(rl,requirement.get(),output.getImmutableInternal(),priority,weight,cookingTime,potLevel);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @ZenCodeType.Method
    public void addRecipe(String name, CrTRequirement requirement, IItemStack output,int priority){
        addRecipe(name,requirement,output,priority,1,200,1);
    }

    @Override
    public IRecipeType<CrockPotCookingRecipe> getRecipeType() {
        return CrockPotRecipeTypes.CROCK_POT_COOKING_RECIPE_TYPE;
    }
}
