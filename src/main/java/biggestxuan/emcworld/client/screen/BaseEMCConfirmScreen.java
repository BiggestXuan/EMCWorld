package biggestxuan.emcworld.client.screen;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/05
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class BaseEMCConfirmScreen extends Screen {
    protected int buttonLength = 75;
    protected int buttonHeight = 20;
    private final long emc;

    protected BaseEMCConfirmScreen(long emc) {
        super(EMCWorld.tc("screen.emc.title"));
        this.emc = emc;
    }

    @Override
    protected void init(){
        super.init();
        this.addButton(addConfirmButton());
        this.addButton(addCancelButton());
    }

    protected void renderBg(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_){
        this.renderBackground(p_230430_1_);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(EMCWorld.rl("textures/gui/transparent.png"));
        blit(p_230430_1_,width,height,0,0,width,height);
    }

    protected abstract Button addConfirmButton();

    protected Button addCancelButton(){
        return new Button((width / 2) + 50,height * 3 / 4,buttonLength,buttonHeight, EMCWorld.tc("screen.emc.cancel"),(a) -> Minecraft.getInstance().setScreen(null));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, font, title, width / 2, 30, 16777215);
        drawCenteredString(matrixStack, font, EMCWorld.tc("screen.emc.desc", MathUtils.format(emc)), width / 2, 75, 16777215);
    }
}
