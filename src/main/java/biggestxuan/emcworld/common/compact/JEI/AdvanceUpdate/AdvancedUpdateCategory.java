package biggestxuan.emcworld.common.compact.JEI.AdvanceUpdate;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/19
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

public class AdvancedUpdateCategory implements IRecipeCategory<JEIAdvancedUpdateRecipe> {
    @Nonnull
    private final IDrawable background;
    private final IDrawable icon;

    public AdvancedUpdateCategory(IGuiHelper guiHelper){
        icon = guiHelper.createDrawableIngredient(new ItemStack(EWItems.ADVANCED_UPDATE_CORE.get()));
        background = guiHelper.createDrawable(EMCWorld.rl("textures/gui/jei/advanced_update.png"),3,4,135,48);
    }

    public static ResourceLocation getRL(){
        return EMCWorld.rl("advanced_update");
    }
    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return getRL();
    }

    @Nonnull
    @Override
    public Class<? extends JEIAdvancedUpdateRecipe> getRecipeClass() {
        return JEIAdvancedUpdateRecipe.class;
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
    public void setIngredients(JEIAdvancedUpdateRecipe Recipe, IIngredients iIngredients) {
        iIngredients.setInput(VanillaTypes.ITEM,Recipe.getInput());
        iIngredients.setOutput(VanillaTypes.ITEM,Recipe.getOutput());
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout iRecipeLayout, @Nonnull JEIAdvancedUpdateRecipe jeiAdvancedUpdateRecipe, IIngredients iIngredients) {
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
    public void draw(JEIAdvancedUpdateRecipe recipe, @Nonnull MatrixStack matrix, double mouseX, double mouseY){
        FontRenderer fontRenderer = Minecraft.getInstance().font;
        String costEMC = MathUtils.format((long)(recipe.getCostEMC() * MathUtils.difficultyLoss()))+" EMC";
        String level = I18n.get("jei.emcworld.recipe.level")+recipe.getLevel();
        fontRenderer.draw(matrix,costEMC, (background.getWidth() - fontRenderer.width(costEMC))/2F, 1,0x808080);
        fontRenderer.draw(matrix,level,98, 32,0x808080);
    }
}
