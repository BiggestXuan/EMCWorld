package biggestxuan.emcworld.common.compact.JEI.SuperEMC;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/28
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.recipes.SuperEMCRecipe;
import biggestxuan.emcworld.common.registry.EWItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class SuperEMCCategory implements IRecipeCategory<SuperEMCRecipe> {
    public final IDrawable background;
    public final IDrawable icon;

    public SuperEMCCategory(IGuiHelper helper){
        this.background = helper.createDrawable(EMCWorld.rl("textures/gui/jei/super_emc_jei.png"),0,0,165,90);
        this.icon = helper.createDrawableIngredient(new ItemStack(EWItems.EMC_SUPER.get()));
    }

    public static ResourceLocation rl(){
        return EMCWorld.rl("jei_emc_super");
    }

    @Override
    public ResourceLocation getUid() {
        return rl();
    }

    @Override
    public Class<? extends SuperEMCRecipe> getRecipeClass() {
        return SuperEMCRecipe.class;
    }

    @Override
    public String getTitle() {
        return I18n.get("block.emcworld.emc_super");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(SuperEMCRecipe recipe, IIngredients ingredients) {
        List<ItemStack> list = new ArrayList<>();
        list.add(recipe.getInput());
        list.add(recipe.getInput1());
        ingredients.setInputIngredients(EMCWorld.itemstack2ingredient(list));
        ingredients.setOutput(VanillaTypes.ITEM,recipe.getOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SuperEMCRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup slot = recipeLayout.getItemStacks();
        slot.init(0,true,29,14);
        slot.init(1,true,29,58);
        slot.init(2,true,114,35);
        slot.set(0,recipe.getInput());
        slot.set(1,recipe.getInput1());
        slot.set(2,recipe.getOutput());
    }
}
