package biggestxuan.emcworld.common.blocks.SteelFurnace;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/06
 */

import biggestxuan.emcworld.EMCWorld;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class SteelFurnaceGUI extends ContainerScreen<SteelFurnaceCoreContainer> {
    public SteelFurnaceGUI(SteelFurnaceCoreContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.renderBackground(p_230450_1_);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.minecraft.getTextureManager().bind(EMCWorld.rl("textures/gui/steel_furnace_gui.png"));
        blit(p_230450_1_,x,y,0,0,imageWidth,imageHeight);
        blit(p_230450_1_,x+77,y+33,176,0,this.menu.getProgress(),21);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
        renderTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void renderLabels(@Nonnull MatrixStack matrixStack, int x, int y) {
        drawCenteredString(matrixStack,this.font, EMCWorld.tc("block.emcworld.steel_furnace_core"),100,5,0xffffff);
    }
}
