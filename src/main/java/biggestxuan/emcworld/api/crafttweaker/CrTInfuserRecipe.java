package biggestxuan.emcworld.api.crafttweaker;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.recipes.InfuserRecipe;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import org.openzen.zencode.java.ZenCodeType;

@ZenCodeType.Name("mods.emcworld.Infuser")
@ZenRegister
@SuppressWarnings("unused")
public class CrTInfuserRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient[] input, IItemStack output,int level,int time,int costEMC){
        NonNullList<Ingredient> list = NonNullList.create();
        for(IIngredient i:input){
            list.add(i.asVanillaIngredient());
        }
        time = ConfigManager.SUNDRY_INFUSER_QUICK.get() ? Math.max(time/100,1) : time;
        InfuserRecipe recipe = new InfuserRecipe(EMCWorld.rl(name),output.getInternal(),list,level,costEMC,time);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @Override
    public IRecipeType<InfuserRecipe> getRecipeType() {
        return InfuserRecipe.RECIPE;
    }
}
