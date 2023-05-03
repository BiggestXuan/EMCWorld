package biggestxuan.emcworld.api.crafttweaker;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/17
 */

import biggestxuan.emcworld.EMCWorld;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.entity.MCEntityType;
import de.ellpeck.naturesaura.recipes.AnimalSpawnerRecipe;
import de.ellpeck.naturesaura.recipes.ModRecipes;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.Spawner")
public class CrTSpawnerRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, MCEntityType entity, int aura, int time, IIngredient... inputs){
        Ingredient[] i = new Ingredient[inputs.length];
        for (int j = 0; j < inputs.length; j++) {
            i[j] = inputs[j].asVanillaIngredient();
        }
        AnimalSpawnerRecipe recipe = new AnimalSpawnerRecipe(EMCWorld.rl(name),entity.getInternal(),aura,time,i);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @Override
    public IRecipeType<AnimalSpawnerRecipe> getRecipeType() {
        return ModRecipes.ANIMAL_SPAWNER_TYPE;
    }
}
