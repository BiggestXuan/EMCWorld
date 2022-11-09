package biggestxuan.emcworld.api.crafttweaker;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/05
 */

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import mythicbotany.ModRecipes;
import mythicbotany.infuser.IInfuserRecipe;
import mythicbotany.infuser.InfuserRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.MythicInfusion")
public class CrTMythicInfusionRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IItemStack output, IIngredient[] inputs, int mana, int fromColor, int toColor){
        InfuserRecipe recipe = new InfuserRecipe(EMCWorld.rl(name),output.getInternal(),mana,fromColor,toColor,getIngredient(inputs));
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @Override
    public IRecipeType<IInfuserRecipe> getRecipeType() {
        return ModRecipes.INFUSER;
    }

    private Ingredient[] getIngredient(IIngredient[] inputs){
        List<Ingredient> cache = new ArrayList<>();
        for(IIngredient ii :inputs){
            cache.add(ii.asVanillaIngredient());
        }
        Ingredient[] out = new Ingredient[cache.size()];
        for (int i = 0; i < cache.size(); i++) {
            out[i] = cache.get(i);
        }
        return out;
    }
}
