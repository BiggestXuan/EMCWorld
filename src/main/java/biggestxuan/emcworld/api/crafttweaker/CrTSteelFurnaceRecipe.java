package biggestxuan.emcworld.api.crafttweaker;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/19
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.recipe.ioRecipe.BaseIORecipe;
import biggestxuan.emcworld.common.recipes.SteelFurnaceRecipe;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveRecipeByName;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.SteelFurnace")
public class CrTSteelFurnaceRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient[] inputs, IItemStack output,int ticks){
        SteelFurnaceRecipe recipe = new SteelFurnaceRecipe(EMCWorld.rl(name), BaseIORecipe.getList(inputs),output.getInternal(),ticks);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @ZenCodeType.Method
    public void removeRecipeByName(String recipeName){
        String[] names = recipeName.split(":");
        ResourceLocation rl = new ResourceLocation(names[0],names[1]);
        CraftTweakerAPI.apply(new ActionRemoveRecipeByName(this,rl));
    }

    @Override
    public IRecipeType<SteelFurnaceRecipe> getRecipeType() {
        return SteelFurnaceRecipe.Type.INSTANCE;
    }
}
