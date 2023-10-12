package biggestxuan.emcworld.common.compact.JEI.EMCOre;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.blocks.tile.EMCOreCoreTileEntity;
import biggestxuan.emcworld.common.recipes.EMCOreRecipe;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/10
 */

@EMCWorldSince("1.0.5")
public class EMCOreCategory implements IRecipeCategory<EMCOreJEIRecipe> {
    @Nonnull
    private final IDrawable background;
    private final IDrawable icon;
    public static ResourceLocation RL = EMCWorld.rl("emc_ore");

    public EMCOreCategory(IGuiHelper guiHelper){
        icon = guiHelper.createDrawableIngredient(new ItemStack(EWItems.EMC_ORE_CORE.get()));
        background = guiHelper.createDrawable(EMCWorld.rl("textures/gui/jei/emc_ore_core_jei.png"),0,0,176,85);
    }

    @Override
    public ResourceLocation getUid() {
        return RL;
    }

    @Override
    public Class<? extends EMCOreJEIRecipe> getRecipeClass() {
        return EMCOreJEIRecipe.class;
    }

    @Override
    public String getTitle() {
        return EMCOreCoreTileEntity.NAME.getString();
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
    public void setIngredients(EMCOreJEIRecipe recipe, IIngredients ingredients) {
        List<Ingredient> list = new ArrayList<>();
        list.add(EMCOreRecipe.getInput());
        ingredients.setInputIngredients(list);
        ingredients.setOutputs(VanillaTypes.ITEM,recipe.getOutputsByItemStack());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, EMCOreJEIRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup sg = recipeLayout.getItemStacks();
        sg.init(0,true,11,33);
        int x = 61;
        int y = 13;
        for (int i = 1; i <= 15 ; i++) {
            sg.init(i,false,x,y);
            x += 18;
            if(i % 5 == 0){
                x = 61;
                y += 18;
            }
        }

        sg.set(0, Arrays.asList(EMCOreRecipe.getInput().getItems()));
        for (int i = 1; i <= 15; i++) {
            List<EMCOreJEIRecipe.WeightItemStack> l = recipe.getOutputs();
            ItemStack stack;
            try{
                stack = l.get(i-1).stack;
                var nbt = stack.getOrCreateTag();
                nbt.putDouble("emc_ore_chance",l.get(i-1).chance);
            }catch (IndexOutOfBoundsException e){
                stack = ItemStack.EMPTY;
            }
            if(!stack.equals(ItemStack.EMPTY)){
                sg.set(i,stack);
            }
        }
    }

    @Override
    public void draw(EMCOreJEIRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, matrixStack, mouseX, mouseY);
        FontRenderer renderer = Minecraft.getInstance().font;
        renderer.draw(matrixStack,EMCWorld.tc("jei.emcworld.star_need",recipe.getIndex()),5,3,0xffffff);
        renderer.draw(matrixStack,EMCWorld.tc(MathUtils.format(EMCOreCoreTileEntity.getProduceCost(recipe.getIndex(),4))).append(" EMC"),5,12,0xffffff);
    }
}
