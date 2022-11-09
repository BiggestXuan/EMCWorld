package biggestxuan.emcworld.common.compact.JEI.SteelFurnace;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/18
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.recipes.SteelFurnaceRecipe;
import biggestxuan.emcworld.common.registry.EWItems;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class SteelFurnaceCategory implements IRecipeCategory<SteelFurnaceRecipe> {
    public final IDrawable background;
    public final IDrawable icon;

    public SteelFurnaceCategory(IGuiHelper helper){
        this.background = helper.createDrawable(EMCWorld.rl("textures/gui/jei/steel_furnace.png"),0,0,130,50);
        this.icon = helper.createDrawableIngredient(new ItemStack(EWItems.STEEL_FURNACE_CORE.get()));
    }

    public static ResourceLocation rl(){
        return EMCWorld.rl("steel_furnace");
    }

    @Override
    public ResourceLocation getUid() {
        return rl();
    }

    @Override
    public Class<? extends SteelFurnaceRecipe> getRecipeClass() {
        return SteelFurnaceRecipe.class;
    }

    @Override
    public String getTitle() {
        return I18n.get("gui.emcworld.steel_furnace");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(SteelFurnaceRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM,recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SteelFurnaceRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup slot = recipeLayout.getItemStacks();
        int index = 0;
        int[] xPos = new int[]{12,30,48};
        int[] yPos = new int[]{8,26};
        for(int i : yPos){
            for(int j : xPos){
                slot.init(index,true,j,i);
                index++;
            }
        }
        slot.init(index,false,104,17);
        slot.set(ingredients);
    }
    @Override
    public void draw(SteelFurnaceRecipe recipe, @Nonnull MatrixStack matrix, double mouseX, double mouseY){
        FontRenderer fontRenderer = Minecraft.getInstance().font;
        String time = I18n.get("jei.emcworld.infuser_time",recipe.getTicks()/20);
        fontRenderer.draw(matrix,time,((background.getWidth() - fontRenderer.width(time))/2F)+35, 3,0x808080);
    }
}
