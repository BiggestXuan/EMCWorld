package biggestxuan.emcworld.common.compact.JEI.Update;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/09
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
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
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;

public class UpdateCategory implements IRecipeCategory<JEIUpdateRecipe> {
    @Nonnull
    private final IDrawable background;
    private final IDrawable icon;

    public UpdateCategory(IGuiHelper guiHelper){
        icon = guiHelper.createDrawableIngredient(new ItemStack(EWItems.CONTROL_UPDATE_CORE.get()));
        background = guiHelper.createDrawable(EMCWorld.rl("textures/gui/jei/update.png"),3,4,135,48);
    }
    public static ResourceLocation getRL(){
        return EMCWorld.rl("update");
    }
    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return getRL();
    }

    @Nonnull
    @Override
    public Class<JEIUpdateRecipe> getRecipeClass() {
        return JEIUpdateRecipe.class;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return I18n.get("jei.emcworld.recipe.update");
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nonnull
    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(JEIUpdateRecipe Recipe, IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM,Recipe.getInput());
        ingredients.setOutput(VanillaTypes.ITEM,Recipe.getOutput());
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout iRecipeLayout, @Nonnull JEIUpdateRecipe Recipe, IIngredients iIngredients) {
        for (List<ItemStack> s : iIngredients.getInputs(VanillaTypes.ITEM)) {
            iRecipeLayout.getItemStacks().init(0, true, 13, 8);
            iRecipeLayout.getItemStacks().set(0, s);
        }
        for (List<ItemStack> s : iIngredients.getOutputs(VanillaTypes.ITEM)) {
            iRecipeLayout.getItemStacks().init(1, false, 98, 8);
            iRecipeLayout.getItemStacks().set(1, s);
        }
    }
    @Override
    public void draw(JEIUpdateRecipe recipe, @Nonnull MatrixStack matrix, double mouseX, double mouseY){
        FontRenderer fontRenderer = Minecraft.getInstance().font;
        String costEMC = MathUtils.format((long)(recipe.getCostEMC() * MathUtils.difficultyLoss()))+" EMC";
        fontRenderer.draw(matrix,costEMC, (background.getWidth() - fontRenderer.width(costEMC))/2F, 1,0x808080);
    }
}
