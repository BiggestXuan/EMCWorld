package biggestxuan.emcworld.common.compact.JEI.Afterlight.Ritual;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/31
 */

import biggestxuan.emcworld.EMCWorld;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RitualCategory implements IRecipeCategory<RitualRecipe> {
    private final IDrawable background;
    private final IDrawable icon;
    public static ItemStack item = EMCWorld.getItem("the_afterlight:ritual_altar");

    public RitualCategory(IGuiHelper helper){
        this.background = helper.createDrawable(EMCWorld.rl("textures/gui/jei/afterlight/ritual.png"),0,0,103,80);
        this.icon = helper.createDrawableIngredient(item);
    }

    public static ResourceLocation rl(){
        return EMCWorld.rl("afterlight_ritual");
    }

    @Override
    public ResourceLocation getUid() {
        return rl();
    }

    @Override
    public Class<? extends RitualRecipe> getRecipeClass() {
        return RitualRecipe.class;
    }

    @Override
    public String getTitle() {
        return item.getItem().getName(item).getString();
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
    public void setIngredients(RitualRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getInput());
        ingredients.setOutput(VanillaTypes.ITEM,recipe.getOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, RitualRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup stackGroup = recipeLayout.getItemStacks();

        stackGroup.init(0,true,34,3);
        stackGroup.init(1,true,34,51);
        stackGroup.init(2,true,10,11);
        stackGroup.init(3,true,58,11);
        stackGroup.init(4,true,10,43);
        stackGroup.init(5,true,58,43);
        stackGroup.init(6,true,34,27);
        stackGroup.init(7,false,80,58);

        for (int i = 0; i < 2; i++) {
            stackGroup.set(i,recipe.getInputItem()[0]);
        }
        for (int i = 2; i < 6; i++) {
            stackGroup.set(i,recipe.getInputItem()[1]);
        }
        stackGroup.set(6,recipe.getInputItem()[2]);
        stackGroup.set(7,recipe.getOutput());
    }
}
