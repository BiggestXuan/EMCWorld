package biggestxuan.emcworld.client.screen;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/15
 */

import biggestxuan.emcworld.EMCWorld;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;

public class PCLWarningScreen extends Screen {
    private final Screen finalScreen;

    public PCLWarningScreen(Screen screen) {
        super(EMCWorld.tc("screen.pcl.title"));
        finalScreen = screen;
    }

    protected void init() {
        super.init();
        this.addButton(new Button(this.width / 2 - 75, this.height * 3 / 4, 150, 20,EMCWorld.tc("screen.pcl.ik"), (p_i232255_5) -> Minecraft.getInstance().setScreen(finalScreen)));
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        drawCenteredString(matrixStack, font, title, width / 2, 30, 16777215);
        drawString(matrixStack, font, EMCWorld.tc("screen.pcl.line1"), width / 12, 60, 16777215);
        drawString(matrixStack, font, EMCWorld.tc("screen.pcl.line2"), width / 12, 85, 16777215);
        drawString(matrixStack, font, EMCWorld.tc("screen.pcl.line3"), width / 12, 110, 16777215);
        drawString(matrixStack, font, EMCWorld.tc("screen.pcl.line4"), width / 12, 135, 16777215);
        drawString(matrixStack, font, EMCWorld.tc("screen.pcl.line5"), width / 12, 160, 16777215);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
