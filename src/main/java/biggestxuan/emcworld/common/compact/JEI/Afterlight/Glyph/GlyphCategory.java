package biggestxuan.emcworld.common.compact.JEI.Afterlight.Glyph;

/**
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
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GlyphCategory implements IRecipeCategory<GlyphRecipe> {
    @Nonnull
    private final IDrawable background;
    private final IDrawable icon;
    public static ItemStack item = EMCWorld.getItem("the_afterlight:glyph_altar");
    private static List<ItemStack> stack = new ArrayList<>(Arrays.asList(new ItemStack[]{EMCWorld.getItem("the_afterlight:golden_shards_of_radiance"),EMCWorld.getItem("the_afterlight:glyph_hammer"),EMCWorld.getItem("the_afterlight:blank_glyph")}));

    public GlyphCategory(IGuiHelper helper){
        this.background = helper.createDrawable(EMCWorld.rl("textures/gui/jei/afterlight/glyph.png"),0,0,165,105);
        this.icon = helper.createDrawableIngredient(item);
    }

    public static ResourceLocation rl(){
        return EMCWorld.rl("afterlight_glyph");
    }

    @Override
    public ResourceLocation getUid() {
        return rl();
    }

    @Override
    public Class<? extends GlyphRecipe> getRecipeClass() {
        return GlyphRecipe.class;
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
    public void setIngredients(GlyphRecipe recipe, IIngredients ingredients) {
        List<Ingredient> list = EMCWorld.itemstack2ingredient(stack);
        list.addAll(recipe.getInput());
        ingredients.setInputIngredients(list);
        ingredients.setOutput(VanillaTypes.ITEM,recipe.getOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, GlyphRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();

        stacks.init(0,true,74,10);
        stacks.init(1,true,74,58);
        stacks.init(2,true,49,34);
        stacks.init(3,true,99,34);
        stacks.init(4,true,1,40);
        stacks.init(5,true,145,40);
        stacks.init(6,true,41,83);
        stacks.init(7,false,133,83);
        stacks.init(8,true,74,34);

        for (int i = 0; i < 2; i++) {
            stacks.set(i,recipe.getInputItem()[0]);
        }
        for (int i = 2; i < 4; i++) {
            stacks.set(i,recipe.getInputItem()[1]);
        }
        for (int i = 4; i < 6; i++) {
            stacks.set(i,stack.get(0));
        }
        stacks.set(6,stack.get(1));
        stacks.set(7,recipe.getOutput());
        stacks.set(8,stack.get(2));
    }
}
