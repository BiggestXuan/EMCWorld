package biggestxuan.emcworld.common.compact.JEI.Afterlight.Ancient;

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
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AncientCategory implements IRecipeCategory<AncientRecipe> {
    @Nonnull
    private final IDrawable background;
    private final IDrawable icon;
    public static ItemStack item = EMCWorld.getItem("the_afterlight:workbench_of_ancients");
    private static List<ItemStack> stack = new ArrayList<>(Collections.singletonList(EMCWorld.getItem("the_afterlight:radiant_shards")));

    public AncientCategory(IGuiHelper helper){
        this.background = helper.createDrawable(EMCWorld.rl("textures/gui/jei/afterlight/ancient.png"),0,0,111,134);
        this.icon = helper.createDrawableIngredient(item);
    }

    public static ResourceLocation rl(){
        return EMCWorld.rl("afterlight_ancient");
    }

    @Override
    public ResourceLocation getUid() {
        return rl();
    }

    @Override
    public Class<? extends AncientRecipe> getRecipeClass() {
        return AncientRecipe.class;
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
    public void setIngredients(AncientRecipe recipe, IIngredients ingredients) {
        List<Ingredient> list = recipe.getInput();
        list.addAll(EMCWorld.itemstack2ingredient(stack));
        ingredients.setInputIngredients(list);
        ingredients.setOutput(VanillaTypes.ITEM,recipe.getOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, AncientRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup stackGroup = recipeLayout.getItemStacks();

        stackGroup.init(0,true,46,4);
        stackGroup.init(1,true,46,79);
        stackGroup.init(2,true,8,41);
        stackGroup.init(3,true,84,41);
        stackGroup.init(4,true,46,24);
        stackGroup.init(5,true,46,59);
        stackGroup.init(6,true,28,41);
        stackGroup.init(7,true,64,41);
        stackGroup.init(8,true,46,41);
        stackGroup.init(9,true,8,114);
        stackGroup.init(10,true,84,114);
        stackGroup.init(11,false,46,114);

        for (int i = 0; i < 9; i++) {
            stackGroup.set(i,recipe.getInputItem()[i]);
        }
        for (int i = 9; i < 11; i++) {
            stackGroup.set(i,stack.get(0));
        }
        stackGroup.set(11,recipe.getOutput());
    }
}
