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
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveRecipeByOutput;
import com.blamejared.crafttweaker.impl.item.MCItemStackMutable;
import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.type.IAuraType;
import de.ellpeck.naturesaura.recipes.AltarRecipe;
import de.ellpeck.naturesaura.recipes.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.emcworld.natureAltar")
public class CrTAltarRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IItemStack output, IIngredient input,int type,int aura){
        AltarRecipe recipe = new AltarRecipe(EMCWorld.rl(name),input.asVanillaIngredient(),output.getInternal(),getType(type), Ingredient.EMPTY,aura,100);
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
                    if(recipe instanceof AltarRecipe) {
                        AltarRecipe recipeAR = (AltarRecipe) recipe;
                        ItemStack recipeOutput = recipeAR.getResultItem();
                        if(output.matches(new MCItemStackMutable(recipeOutput))) {
                            toRemove.add(location);
                        }
                    }
                }
                toRemove.forEach(getManager().getRecipes()::remove);
            }
        });
    }

    private static IAuraType getType(int type){
        switch (type){
            case 1:
                return NaturesAuraAPI.TYPE_OVERWORLD;
            case 2:
                return NaturesAuraAPI.TYPE_NETHER;
            case 3:
                return NaturesAuraAPI.TYPE_END;
            default:
                return NaturesAuraAPI.TYPE_OTHER;
        }
    }

    @Override
    public IRecipeType<AltarRecipe> getRecipeType() {
        return ModRecipes.ALTAR_TYPE;
    }
}
