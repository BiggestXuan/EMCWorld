package biggestxuan.emcworld.common.compact.JEI.FTBQ;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.items.EMCWorldQuestsItem;
import biggestxuan.emcworld.common.utils.FTBQJeiUtils;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import dev.ftb.mods.ftbquests.item.FTBQuestsItems;
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
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/13
 */

public class FTBQCategory implements IRecipeCategory<FTBQJeiUtils> {
    @Nonnull
    private final IDrawable background;
    private final IDrawable icon;

    public FTBQCategory(IGuiHelper guiHelper){
        icon = guiHelper.createDrawableIngredient(new ItemStack(FTBQuestsItems.BOOK.get()));
        background = guiHelper.createDrawable(EMCWorld.rl("textures/gui/jei/ftbq_jei.png"),0,0,221,186);
    }

    public static ResourceLocation rl() {
        return EMCWorld.rl("ftbq_jei");
    }

    @Override
    public ResourceLocation getUid() {
        return rl();
    }

    @Override
    public Class<? extends FTBQJeiUtils> getRecipeClass() {
        return FTBQJeiUtils.class;
    }

    @Override
    public String getTitle() {
        return EMCWorld.tc("jei.emcworld.ftbq.title").getString();
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
    public void setIngredients(FTBQJeiUtils recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.inputs);
        List<ItemStack> list = recipe.outputs;
        list.add(EMCWorldQuestsItem.getQuestItemByName(recipe.questName));
        ingredients.setOutputs(VanillaTypes.ITEM,list);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FTBQJeiUtils recipe, IIngredients ingredients) {
        IGuiItemStackGroup sg = recipeLayout.getItemStacks();

        initJEIFTBQSlots(sg,12,20,0,true,recipe);
        initJEIFTBQSlots(sg,147,20,9,false,recipe);
    }

    @Override
    public void draw(FTBQJeiUtils recipe, @Nonnull MatrixStack matrix, double mouseX, double mouseY){
        FontRenderer fontRenderer = Minecraft.getInstance().font;
        String questName = recipe.questName;
        fontRenderer.draw(matrix,questName,110-fontRenderer.width(questName)/2,8,0xffffff);
        fontRenderer.draw(matrix, EMCWorld.tc("jei.emcworld.ftbq.emc_reward","+"+MathUtils.format(recipe.emc)),147,80,0xffffff);
        if(recipe.hasDependence()){
            int y = 100;
            fontRenderer.draw(matrix, EMCWorld.tc("jei.emcworld.ftbq.dependencies"),147,y,0xffffff);
            for(var d : recipe.dependencies){
                y += 10;
                fontRenderer.draw(matrix,d,147,y,0xffffff);
            }
        }
        if(recipe.hasDependant()){
            int y = 80;
            fontRenderer.draw(matrix, EMCWorld.tc("jei.emcworld.ftbq.dependants"),10,y,0xffffff);
            for(var d : recipe.dependants){
                y += 10;
                fontRenderer.draw(matrix,d,10,y,0xffffff);
            }
        }
    }

    private static void initJEIFTBQSlots(IGuiItemStackGroup sg, int xStart, int yStart, int index, boolean isInput, FTBQJeiUtils recipe){
        int ix = index;
        int x;
        int y = yStart;
        for (int i = 0; i < 3; i++) {
            x = xStart;
            for (int j = 0; j < 3; j++) {
                sg.init(ix,isInput,x,y);
                try{
                    if(isInput){
                        sg.set(ix,EMCWorld.ingredient2itemstackList(recipe.inputs.get(ix)));
                    }else{
                        ItemStack s = recipe.outputs.get(ix-9);
                        if(!(s.getItem() instanceof EMCWorldQuestsItem)){
                            sg.set(ix,s);
                        }
                    }
                }catch (Exception ignored){}
                x += 18;
                ix++;
            }
            y += 18;
        }
    }
}
