package biggestxuan.emcworld.common.compact.JEI;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/09
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.recipes.MatterFixRecipe;
import biggestxuan.emcworld.common.registry.EWItems;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;

public class CollectorFixCategory implements IRecipeCategory<MatterFixRecipe> {
    @Nonnull
    private final IDrawable background;
    private final IDrawable icon;

    public CollectorFixCategory(IGuiHelper guiHelper){
        icon = guiHelper.createDrawableIngredient(new ItemStack(EWItems.BLUE_MATTER.get()));
        background = guiHelper.createDrawable(EMCWorld.rl("textures/gui/jei/collector_fix.png"),0,0,135,48);
    }

    public static ResourceLocation getRL(){
        return EMCWorld.rl("collector_fix");
    }

    @Nonnull
    @Override
    public ResourceLocation getUid() {
        return getRL();
    }

    @Nonnull
    @Override
    public Class<MatterFixRecipe> getRecipeClass() {
        return MatterFixRecipe.class;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return EMCWorld.tc("jei.emcworld.collector_fix.title").getString();
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
    public void setIngredients(MatterFixRecipe Recipe, IIngredients ingredients) {
        ItemStack stack = EMCWorld.getItem("projectex:"+Recipe.matter().name+"_collector");
        stack.setCount(1);
        ingredients.setInput(VanillaTypes.ITEM,Recipe.getMatter());
        ingredients.setOutput(VanillaTypes.ITEM,stack);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout iRecipeLayout, @Nonnull MatterFixRecipe Recipe, IIngredients iIngredients) {
        for (List<ItemStack> s : iIngredients.getInputs(VanillaTypes.ITEM)) {
            iRecipeLayout.getItemStacks().init(0, true, 14, 12);
            iRecipeLayout.getItemStacks().set(0, s);
        }
        for (List<ItemStack> s : iIngredients.getOutputs(VanillaTypes.ITEM)) {
            iRecipeLayout.getItemStacks().init(1, false, 99, 12);
            iRecipeLayout.getItemStacks().set(1, s);
        }
    }
    @Override
    public void draw(MatterFixRecipe recipe, @Nonnull MatrixStack matrix, double mouseX, double mouseY){
        FontRenderer fontRenderer = Minecraft.getInstance().font;
        String rate = EMCWorld.tc("jei.emcworld.collector_fix.efficiency").getString()+String.format("%.0f",recipe.getRate()*100)+"%";
        fontRenderer.draw(matrix,rate,95,30,0x808080);
    }
}
