package biggestxuan.emcworld.client.screen;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/23
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.MultiBlock;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class UpdateCoreGUI extends Screen {
    private final double cost;
    private final double addon;
    private final long time;
    private final int level;
    ResourceLocation bg = EMCWorld.rl("textures/gui/update_core.png");
    public UpdateCoreGUI(ITextComponent title, MultiBlock.UpdateMath info){
        super(title);
        this.cost = info.getCost();
        this.addon = info.getAddon();
        this.time = info.getTime();
        this.level = info.getLevel();
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(matrixStack);
        int base = this.width/2-50;
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(bg);
        blit(matrixStack, this.width / 2 - 110, 10, 0, 0, 200, 150, 200, 150);
        drawCenteredString(matrixStack, this.font, EMCWorld.tc("gui.emcworld.update_core"), this.width / 2 - 10, 20, 0xffffff);
        drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.update_core_level",level),base-50,40,0xffffff);
        drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.update_core_cost",String.format("%.2f",cost*100)+"%"),base-50,70,0xffffff);
        drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.update_core_addon",String.format("%.2f",addon*100)+"%"),base-50,100,0xffffff);
        drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.update_core_time",time/20),base-50,130,0xffffff);
    }
}
