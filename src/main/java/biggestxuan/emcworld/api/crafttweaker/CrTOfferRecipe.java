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
import de.ellpeck.naturesaura.recipes.ModRecipes;
import de.ellpeck.naturesaura.recipes.OfferingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.offer")
public class CrTOfferRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient input, IIngredient input1, IItemStack output){
        OfferingRecipe recipe = new OfferingRecipe(EMCWorld.rl(name),input.asVanillaIngredient(),input1.asVanillaIngredient(),output.getInternal());
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @ZenCodeType.Method
    public void removeRecipe(IItemStack output){
        CraftTweakerAPI.apply(new ActionRemoveRecipeByOutput(this,output));
    }

    @Override
    public IRecipeType<OfferingRecipe> getRecipeType() {
        return ModRecipes.OFFERING_TYPE;
    }
}
