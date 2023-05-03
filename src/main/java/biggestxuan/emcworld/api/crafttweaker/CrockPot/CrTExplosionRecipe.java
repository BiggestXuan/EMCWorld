package biggestxuan.emcworld.api.crafttweaker.CrockPot;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/20
 */

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.sihenzhang.crockpot.recipe.CrockPotRecipeTypes;
import com.sihenzhang.crockpot.recipe.ExplosionCraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.CrTExplosionRecipe")
@SuppressWarnings("unused")
public class CrTExplosionRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient input, IItemStack output,float lossRate,boolean onlyBlock){
        ExplosionCraftingRecipe recipe = new ExplosionCraftingRecipe(EMCWorld.rl(name),input.asVanillaIngredient(),output.getImmutableInternal(),lossRate,onlyBlock);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @Override
    public IRecipeType<ExplosionCraftingRecipe> getRecipeType() {
        return CrockPotRecipeTypes.EXPLOSION_CRAFT_RECIPE_TYPE;
    }
}
