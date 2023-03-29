package biggestxuan.emcworld.common.blocks.SuperEMCBlock;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.WeaponUpgradeGUI;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class SuperEMCGUI extends ContainerScreen<SuperEMCContainer> {
    private final BlockPos pos;
    public SuperEMCGUI(SuperEMCContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        pos = p_i51105_1_.getPos();
    }

    @Override
    protected void init() {
        super.init();
        int x = (width - getXSize()) / 2;
        int y = (height - getYSize()) / 2;
        this.addButton(new Button(x + 60, y + 60, 40, 20, EMCWorld.tc("gui.menu.start"), new WeaponUpgradeGUI.ButtonPress(pos, 0)));
    }

    @Override
    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.renderBackground(p_230450_1_);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.minecraft.getTextureManager().bind(EMCWorld.rl("textures/gui/super_emc.png"));
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
        drawString(matrixStack,this.font, EMCWorld.tc("block.emcworld.emc_super"),72,8,0xffffff);
    }
}
