package biggestxuan.emcworld.common.compact.JEI.PiglinRecipe;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/06
 */

import biggestxuan.emcworld.EMCWorld;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;

public class PiglinCategory implements IRecipeCategory<JEIPiglinRecipe> {
    @Nonnull
    private final IDrawable background;
    private final IDrawable icon;

    public PiglinCategory(IGuiHelper helper){
        this.background = helper.createDrawable(EMCWorld.rl("textures/gui/jei/piglin_jei.png"),0,0,135,48);
        this.icon = helper.createDrawableIngredient(new ItemStack(Items.GOLD_INGOT));
    }

    public static ResourceLocation rl(){
        return EMCWorld.rl("piglin_category");
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return rl();
    }

    @Nonnull
    @Override
    public Class<? extends JEIPiglinRecipe> getRecipeClass() {
        return JEIPiglinRecipe.class;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return I18n.get("jei.emcworld.piglin.title");
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }
    @Override

    public void setIngredients(JEIPiglinRecipe recipe, IIngredients ingredients){
        ingredients.setInput(VanillaTypes.ITEM,recipe.getInput());
        ingredients.setOutput(VanillaTypes.ITEM,recipe.getOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, JEIPiglinRecipe recipe, IIngredients ingredients) {
        for (List<ItemStack> s : ingredients.getInputs(VanillaTypes.ITEM)) {
            recipeLayout.getItemStacks().init(0, true, 12, 25);
            recipeLayout.getItemStacks().set(0, s);
        }
        for (List<ItemStack> s : ingredients.getOutputs(VanillaTypes.ITEM)) {
            recipeLayout.getItemStacks().init(1, false, 97, 25);
            recipeLayout.getItemStacks().set(1, s);
        }
    }

    @Override
    public void draw(JEIPiglinRecipe recipe, @Nonnull MatrixStack matrix, double mouseX, double mouseY){
        FontRenderer fontRenderer = Minecraft.getInstance().font;
        String level = recipe.getChance();
        fontRenderer.draw(matrix,level,98, 10,0x808080);
    }
}
