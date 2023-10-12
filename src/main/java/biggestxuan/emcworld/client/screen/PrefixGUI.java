package biggestxuan.emcworld.client.screen;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.screen.EMCWorldContainerHelperGUI;
import biggestxuan.emcworld.client.screen.WeaponUpgradeGUI;
import biggestxuan.emcworld.common.blocks.container.PrefixContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class PrefixGUI extends EMCWorldContainerHelperGUI<PrefixContainer> {
    private final BlockPos pos;
    public PrefixGUI(PrefixContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        pos = p_i51105_1_.getPos();
    }

    @Override
    public String getHelperLink() {
        return "https://www.kancloud.cn/biggest_xuan/emcworld/3148138";
    }

    @Override
    protected void init(){
        super.init();
        int x = (width - getXSize())/2;
        int y = (height - getYSize()) /2;
        this.addButton(new Button(x+35, y+60, 40, 20, EMCWorld.tc("gui.prefix.button"), new WeaponUpgradeGUI.ButtonPress(pos,0)));
        this.addButton(new Button(x+105, y+60, 40, 20, EMCWorld.tc("gui.prefix.button_all"), new WeaponUpgradeGUI.ButtonPress(pos,1)));
    }

    @Override
    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.renderBackground(p_230450_1_);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.minecraft.getTextureManager().bind(EMCWorld.rl("textures/gui/prefix_gui.png"));
        blit(p_230450_1_,x,y,0,0,imageWidth,imageHeight);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
        renderTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void renderLabels(@Nonnull MatrixStack matrixStack, int x, int y) {
        super.renderLabels(matrixStack, x, y);
        drawString(matrixStack,this.font, EMCWorld.tc("gui.prefix.title"),72,8,0xffffff);
    }
}
