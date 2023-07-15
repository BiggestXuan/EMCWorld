package biggestxuan.emcworld.common.compact.JEI.Infuser;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/16
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.InfuserBlock.InfuserBlockTileEntity;
import biggestxuan.emcworld.common.recipes.InfuserRecipe;
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
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class InfuserCategory implements IRecipeCategory<InfuserRecipe> {
    public final IDrawable background;
    public final IDrawable icon;

    public InfuserCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(EMCWorld.rl("textures/gui/jei/infuser_jei.png"),0,0,142,78);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(EWItems.INFUSER_CORE.get()));
    }

    public static ResourceLocation getRL(){
        return EMCWorld.rl("infuser");
    }

    @Override
    public ResourceLocation getUid() {
        return getRL();
    }

    @Override
    public Class<? extends InfuserRecipe> getRecipeClass() {
        return InfuserRecipe.class;
    }

    @Override
    public String getTitle() {
        return I18n.get("gui.emcworld.infuser_core");
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
    public void setIngredients(InfuserRecipe infuserRecipe, IIngredients iIngredients) {
        iIngredients.setInputIngredients(infuserRecipe.getIngredients());
        iIngredients.setOutput(VanillaTypes.ITEM,infuserRecipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, InfuserRecipe infuserRecipe, IIngredients iIngredients) {
        IGuiItemStackGroup guiItemStacks = iRecipeLayout.getItemStacks();
        guiItemStacks.init(0,true,32-1,7-1);
        guiItemStacks.init(1,true,13-1,27-1);
        guiItemStacks.init(2,true,51-1,27-1);
        guiItemStacks.init(3,true,19-1,49-1);
        guiItemStacks.init(4,true,45-1,49-1);
        guiItemStacks.init(5,false,99-1,27-1);

        guiItemStacks.set(iIngredients);
    }

    @Override
    public void draw(InfuserRecipe recipe, @Nonnull MatrixStack matrix, double mouseX, double mouseY){
        FontRenderer fontRenderer = Minecraft.getInstance().font;
        String time = I18n.get("jei.emcworld.infuser_time",recipe.getCostTime()/20);
        fontRenderer.draw(matrix,time,((background.getWidth() - fontRenderer.width(time))/2F)+25, 5,0x808080);
    }

    @Override
    public List<ITextComponent> getTooltipStrings(InfuserRecipe recipe, double mouseX, double mouseY) {
        List<ITextComponent> info = new ArrayList<>();
        if(mouseX >=5 && mouseX <=13 && mouseY >=4 && mouseY <=12){
            info.add(EMCWorld.tc("jei.emcworld.infuser_level",recipe.getCraftLevel()));
        }
        if(mouseX >=125 && mouseX <=132 && mouseY >=2 && mouseY <74){
            info.add(EMCWorld.tc("jei.emcworld.infuser_emc",getInfo(recipe)));
        }
        return info;
    }

    private String getInfo(InfuserRecipe recipe){
        double rate = 1d * recipe.getCostEMC() / InfuserBlockTileEntity.maxAmount[recipe.getCraftLevel()-1];
        String info;
        if(rate <= 0.1d){
            info = "jei.emcworld.infuser_emc_amount1";
        }
        else if(rate <= 0.25d){
            info = "jei.emcworld.infuser_emc_amount2";
        }
        else if(rate <= 0.5d){
            info = "jei.emcworld.infuser_emc_amount3";
        }
        else if(rate <= 0.8d){
            info = "jei.emcworld.infuser_emc_amount4";
        }
        else if(rate < 1d){
            info = "jei.emcworld.infuser_emc_amount5";
        }
        else{
            info = "jei.emcworld.infuser_emc_amount6";
        }
        info = EMCWorld.tc(info).getString();
        if(Screen.hasShiftDown()){
            info += " (";
            info += MathUtils.format(recipe.getCostEMC())+" EMC)";
        }
        return info;
    }
}
