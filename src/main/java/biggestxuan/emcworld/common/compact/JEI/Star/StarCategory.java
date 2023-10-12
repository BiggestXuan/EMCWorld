package biggestxuan.emcworld.common.compact.JEI.Star;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.recipes.StarPedestalRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import hellfirepvp.astralsorcery.client.util.Blending;
import hellfirepvp.astralsorcery.client.util.RenderingConstellationUtils;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class StarCategory implements IRecipeCategory<StarPedestalRecipe> {
    @Nonnull
    private final IDrawable background;
    private final IDrawable icon;

    public StarCategory(IGuiHelper helper){
        this.background = helper.createDrawable(EMCWorld.rl("textures/gui/jei/star_jei.png"),0,0,163,185);
        this.icon = helper.createDrawableIngredient(EMCWorld.getItem("emcworld:star_pedestal"));
    }

    public static ResourceLocation rl(){
        return EMCWorld.rl("star_pedestal");
    }

    @Override
    public ResourceLocation getUid() {
        return rl();
    }

    @Override
    public Class<? extends StarPedestalRecipe> getRecipeClass() {
        return StarPedestalRecipe.class;
    }

    @Override
    public String getTitle() {
        return I18n.get("jei.emcworld.star");
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
    public void setIngredients(StarPedestalRecipe recipe, IIngredients ingredients) {
        List<Ingredient> list = new ArrayList<>();
        recipe.getInputs().forEach(e->e.forEach(c->list.add(Ingredient.of(c))));
        ingredients.setInputIngredients(list);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, StarPedestalRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup sg = recipeLayout.getItemStacks();
        final int s = 18;
        final int c = 72;
        sg.init(0,true,c,c);
        sg.init(1,true,c,c-s);
        sg.init(2,true,c,c+s);
        sg.init(3,true,c-s,c);
        sg.init(4,true,c+s,c);
        sg.init(5,true,c,c-2*s);
        sg.init(6,true,c,c+2*s);
        sg.init(7,true,c-2*s,c);
        sg.init(8,true,c+2*s,c);
        sg.init(9,true,c-s,c-s);
        sg.init(10,true,c+s,c-s);
        sg.init(11,true,c-s,c+s);
        sg.init(12,true,c+s,c+s);
        sg.init(13,true,c-s,c-2*s);
        sg.init(14,true,c+s,c-2*s);
        sg.init(15,true,c-s,c+2*s);
        sg.init(16,true,c+s,c+2*s);
        sg.init(17,true,c-2*s,c-s);
        sg.init(18,true,c+2*s,c-s);
        sg.init(19,true,c-2*s,c+s);
        sg.init(20,true,c+2*s,c+s);
        sg.init(21,true,c,c-3*s);
        sg.init(22,true,c,c+3*s);
        sg.init(23,true,c-3*s,c);
        sg.init(24,true,c+3*s,c);

        List<List<ItemStack>> inputs = recipe.getInputs();
        for (int i = 1; i < 5; i++) {
            sg.set(i,trans(inputs.get(0)).get(i-1));
        }

        for (int i = 5; i < 13; i++) {
            if(recipe.getLevel() >= 2){
                sg.set(i,trans(inputs.get(1)).get(i-5));
            }
        }

        for (int i = 13; i < 21; i++) {
            if(recipe.getLevel() >= 3){
                sg.set(i,trans(inputs.get(2)).get(i-13));
            }
        }

        for (int i = 21; i < 25; i++) {
            if(recipe.getLevel() >= 4){
                sg.set(i,trans(inputs.get(3)).get(i-21));
            }
        }
    }

    @Override
    public void draw(StarPedestalRecipe recipe, @Nonnull MatrixStack matrix, double mouseX, double mouseY){
        FontRenderer fontRenderer = Minecraft.getInstance().font;
        if(recipe.getMode() == StarPedestalRecipe.MODE.MAX_STAR){
            fontRenderer.draw(matrix,I18n.get("jei.emcworld.star_mode",I18n.get("jei.emcworld.star_mode_max")),4,150,0x808080);
        }else{
            fontRenderer.draw(matrix,I18n.get("jei.emcworld.star_mode",I18n.get("jei.emcworld.star_mode_star")),4,150,0x808080);
        }
        fontRenderer.draw(matrix,I18n.get("jei.emcworld.star_level",recipe.getLevel()),4,161,0x808080);
        fontRenderer.draw(matrix,I18n.get("jei.emcworld.star_star",recipe.getRequireStar(),recipe.getRequireStar()+1),4,171,0x808080);
        if (recipe.getStar() != null) {
            RenderSystem.enableBlend();
            Blending.DEFAULT.apply();
            RenderingConstellationUtils.renderConstellationIntoGUI(recipe.getStar().getConstellationColor(), recipe.getStar(), matrix, 0, 0, 0, 50, 50, 1.2, () -> 0.9f, true, false);
            RenderSystem.disableBlend();
        }
    }

    @Deprecated//HashSet<ItemStack> -> List<ItemStack>
    private static List<ItemStack> trans(List<ItemStack> set){
        return set;
    }

    private static <T extends Item> Ingredient a(RegistryObject<T> c){
        return Ingredient.of(c.get());
    }
}
