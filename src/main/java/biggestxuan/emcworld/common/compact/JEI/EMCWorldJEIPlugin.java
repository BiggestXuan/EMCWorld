package biggestxuan.emcworld.common.compact.JEI;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/09
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.JEI.AdvanceUpdate.AdvancedUpdateCategory;
import biggestxuan.emcworld.common.compact.JEI.AdvanceUpdate.JEIAdvancedUpdateRecipe;
import biggestxuan.emcworld.common.compact.JEI.Afterlight.Ancient.AncientCategory;
import biggestxuan.emcworld.common.compact.JEI.Afterlight.Ancient.AncientRecipe;
import biggestxuan.emcworld.common.compact.JEI.Afterlight.Glyph.GlyphCategory;
import biggestxuan.emcworld.common.compact.JEI.Afterlight.Glyph.GlyphRecipe;
import biggestxuan.emcworld.common.compact.JEI.Afterlight.Ritual.RitualCategory;
import biggestxuan.emcworld.common.compact.JEI.Afterlight.Ritual.RitualRecipe;
import biggestxuan.emcworld.common.compact.JEI.Infuser.InfuserCategory;
import biggestxuan.emcworld.common.compact.JEI.PiglinRecipe.JEIPiglinRecipe;
import biggestxuan.emcworld.common.compact.JEI.PiglinRecipe.PiglinCategory;
import biggestxuan.emcworld.common.compact.JEI.SteelFurnace.SteelFurnaceCategory;
import biggestxuan.emcworld.common.compact.JEI.Update.JEIUpdateRecipe;
import biggestxuan.emcworld.common.compact.JEI.Update.UpdateCategory;
import biggestxuan.emcworld.common.recipes.AdvancedUpdateRecipe;
import biggestxuan.emcworld.common.recipes.InfuserRecipe;
import biggestxuan.emcworld.common.recipes.SteelFurnaceRecipe;
import biggestxuan.emcworld.common.recipes.UpdateRecipe;
import biggestxuan.emcworld.common.registry.EWItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@JeiPlugin
public class EMCWorldJEIPlugin implements IModPlugin {

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return EMCWorld.rl("jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new UpdateCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new AdvancedUpdateCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new InfuserCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new SteelFurnaceCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new PiglinCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new GlyphCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new RitualCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new AncientCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration){
        registration.addRecipeCatalyst(new ItemStack(EWItems.CONTROL_UPDATE_CORE.get()), UpdateCategory.getRL());
        List<ItemStack> ar = new ArrayList<>();
        ar.add(getIS(EWItems.ADVANCED_UPDATE_CORE.get()));
        ar.add(getIS(EWItems.UPDATE_BASE_PURPLE.get()));
        ar.add(getIS(EWItems.UPDATE_BASE_BX_PURPLE.get()));
        ar.add(getIS(EWItems.UPDATE_BASE_BLUE.get()));
        ar.add(getIS(EWItems.UPDATE_BASE_CYAN.get()));
        ar.add(getIS(EWItems.UPDATE_BASE_GREEN.get()));
        ar.add(getIS(EWItems.UPDATE_BASE_YELLOW.get()));
        ar.add(getIS(EWItems.UPDATE_BASE_ORANGE.get()));
        ar.add(getIS(EWItems.UPDATE_BASE_RED.get()));
        for(ItemStack item :ar){
            registration.addRecipeCatalyst(item,AdvancedUpdateCategory.getRL());
        }

        registration.addRecipeCatalyst(new ItemStack(EWItems.INFUSER_CORE.get()),InfuserCategory.getRL());
        registration.addRecipeCatalyst(new ItemStack(EWItems.STEEL_FURNACE_CORE.get()),SteelFurnaceCategory.rl());
        registration.addRecipeCatalyst(new ItemStack(EWItems.STEEL_FURNACE_BRICK.get()),SteelFurnaceCategory.rl());
        registration.addRecipeCatalyst(GlyphCategory.item,GlyphCategory.rl());
        registration.addRecipeCatalyst(RitualCategory.item,RitualCategory.rl());
        registration.addRecipeCatalyst(AncientCategory.item,AncientCategory.rl());
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registry){
        List<JEIUpdateRecipe> recipes = new ArrayList<>();
        for(UpdateRecipe obj:UpdateRecipe.values()){
            recipes.add(new JEIUpdateRecipe(obj.getInput(),obj.getOutput(),obj.costEMC()));
        }
        registry.addRecipes(recipes, UpdateCategory.getRL());

        List<JEIAdvancedUpdateRecipe> advanced_recipes = new ArrayList<>();
        for(AdvancedUpdateRecipe obj:AdvancedUpdateRecipe.values()){
            advanced_recipes.add(new JEIAdvancedUpdateRecipe(obj.getInput(),obj.getOutput(),obj.costEMC(),obj.recipeLevel()));
        }
        registry.addRecipes(advanced_recipes, AdvancedUpdateCategory.getRL());

        List<JEIPiglinRecipe> piglin_recipes = new ArrayList<>(Arrays.asList(JEIPiglinRecipe.values()));
        registry.addRecipes(piglin_recipes, PiglinCategory.rl());

        List<GlyphRecipe> glyphRecipes = new ArrayList<>(Arrays.asList(GlyphRecipe.values()));
        registry.addRecipes(glyphRecipes,GlyphCategory.rl());

        List<RitualRecipe> ritualRecipes = new ArrayList<>(Arrays.asList(RitualRecipe.values()));
        registry.addRecipes(ritualRecipes,RitualCategory.rl());

        List<AncientRecipe> ancientRecipes = new ArrayList<>(Arrays.asList(AncientRecipe.values()));
        registry.addRecipes(ancientRecipes,AncientCategory.rl());

        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registry.addRecipes(recipeManager.getAllRecipesFor(InfuserRecipe.Type.INSTANCE),InfuserCategory.getRL());
        registry.addRecipes(recipeManager.getAllRecipesFor(SteelFurnaceRecipe.Type.INSTANCE),SteelFurnaceCategory.rl());
    }

    private static ItemStack getIS(Item item){
        return new ItemStack(item,1);
    }
}
