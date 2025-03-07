package biggestxuan.emcworld.common.compact.JEI;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/09
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.JEI.AdvanceUpdate.AdvancedUpdateCategory;
import biggestxuan.emcworld.common.compact.JEI.AdvanceUpdate.JEIAdvancedUpdateRecipe;
import biggestxuan.emcworld.common.compact.JEI.EMCOre.EMCOreCategory;
import biggestxuan.emcworld.common.compact.JEI.EMCOre.EMCOreJEIRecipe;
import biggestxuan.emcworld.common.compact.JEI.FTBQ.FTBQCategory;
import biggestxuan.emcworld.common.compact.JEI.Infuser.InfuserCategory;
import biggestxuan.emcworld.common.compact.JEI.PiglinRecipe.JEIPiglinRecipe;
import biggestxuan.emcworld.common.compact.JEI.PiglinRecipe.PiglinCategory;
import biggestxuan.emcworld.common.compact.JEI.Star.StarCategory;
import biggestxuan.emcworld.common.compact.JEI.SteelFurnace.SteelFurnaceCategory;
import biggestxuan.emcworld.common.compact.JEI.SuperEMC.SuperEMCCategory;
import biggestxuan.emcworld.common.compact.JEI.Update.JEIUpdateRecipe;
import biggestxuan.emcworld.common.compact.JEI.Update.UpdateCategory;
import biggestxuan.emcworld.common.recipes.*;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.FTBQJeiUtils;
import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.item.FTBQuestsItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
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

@SuppressWarnings("unused")
@JeiPlugin
public class EMCWorldJEIPlugin implements IModPlugin {

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return EMCWorld.rl("jei");
    }

    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(EWItems.EMCWORLD_QUEST_ITEM.get());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new UpdateCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new AdvancedUpdateCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new InfuserCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new SteelFurnaceCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new PiglinCategory(registry.getJeiHelpers().getGuiHelper()));
        /**registry.addRecipeCategories(new GlyphCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new RitualCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new AncientCategory(registry.getJeiHelpers().getGuiHelper()));*/
        registry.addRecipeCategories(new StarCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new SuperEMCCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new CollectorFixCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new EMCOreCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new FTBQCategory(registry.getJeiHelpers().getGuiHelper()));
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

        registration.addRecipeCatalyst(getIS(EWItems.INFUSER_CORE.get()),InfuserCategory.getRL());
        registration.addRecipeCatalyst(getIS(EWItems.STEEL_FURNACE_CORE.get()),SteelFurnaceCategory.rl());
        registration.addRecipeCatalyst(getIS(EWItems.STEEL_FURNACE_BRICK.get()),SteelFurnaceCategory.rl());
        registration.addRecipeCatalyst(getIS(EWItems.EMC_ORE_CORE.get()),EMCOreCategory.RL);
        /**registration.addRecipeCatalyst(GlyphCategory.item,GlyphCategory.rl());
        registration.addRecipeCatalyst(RitualCategory.item,RitualCategory.rl());
        registration.addRecipeCatalyst(AncientCategory.item,AncientCategory.rl());*/
        for(ItemStack s : new ItemStack[]{EMCWorld.getItem("emcworld:star_pedestal"),EMCWorld.getItem("astralsorcery:rock_collector_crystal"),EMCWorld.getItem("naturesaura:wood_stand"),EMCWorld.getItem("astralsorcery:starmetal"),EMCWorld.getItem("extendedcrafting:pedestal"),EMCWorld.getItem("minecraft:netherite_block"),EMCWorld.getItem("undergarden:cloggrum_block"),EMCWorld.getItem("projecte:dm_pedestal"),EMCWorld.getItem("projecte:red_matter_block"),EMCWorld.getItem("astralsorcery:infuser")}){
            registration.addRecipeCatalyst(s,StarCategory.rl());
        }
        registration.addRecipeCatalyst(getIS(EWItems.EMC_SUPER.get()),SuperEMCCategory.rl());
        registration.addRecipeCatalyst(getIS(FTBQuestsItems.BOOK.get()),FTBQCategory.rl());
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

        /**List<GlyphRecipe> glyphRecipes = new ArrayList<>(Arrays.asList(GlyphRecipe.values()));
        registry.addRecipes(glyphRecipes,GlyphCategory.rl());

        List<RitualRecipe> ritualRecipes = new ArrayList<>(Arrays.asList(RitualRecipe.values()));
        registry.addRecipes(ritualRecipes,RitualCategory.rl());

        List<AncientRecipe> ancientRecipes = new ArrayList<>(Arrays.asList(AncientRecipe.values()));
        registry.addRecipes(ancientRecipes,AncientCategory.rl());*/

        List<StarPedestalRecipe> starPedestalRecipe = new ArrayList<>(Arrays.asList(StarPedestalRecipe.values()));
        registry.addRecipes(starPedestalRecipe,StarCategory.rl());

        List<SuperEMCRecipe> superEMCRecipes = new ArrayList<>(Arrays.asList(SuperEMCRecipe.values()));
        registry.addRecipes(superEMCRecipes,SuperEMCCategory.rl());

        List<MatterFixRecipe> recipes1 = new ArrayList<>(Arrays.asList(MatterFixRecipe.values()));
        registry.addRecipes(recipes1,CollectorFixCategory.getRL());

        List<EMCOreJEIRecipe> recipes2 = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            recipes2.add(new EMCOreJEIRecipe(i));
        }
        registry.addRecipes(recipes2,EMCOreCategory.RL);

        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registry.addRecipes(recipeManager.getAllRecipesFor(InfuserRecipe.Type.INSTANCE),InfuserCategory.getRL());
        registry.addRecipes(recipeManager.getAllRecipesFor(SteelFurnaceRecipe.Type.INSTANCE),SteelFurnaceCategory.rl());

        if(ClientQuestFile.INSTANCE != null){
            registry.addRecipes(FTBQJeiUtils.getAllQuests(true),FTBQCategory.rl());
        }
    }

    private static ItemStack getIS(Item item){
        return new ItemStack(item,1);
    }
}
