package biggestxuan.emcworld.common.blocks.InfuserBlock;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/14
 */

import biggestxuan.emcworld.EMCWorld;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class InfuserGUI extends ContainerScreen<InfuserContainer> {
    private final BlockPos pos;
    private final World world;

    public InfuserGUI(InfuserContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        this.pos = p_i51105_1_.getPos();
        this.world = p_i51105_1_.getWorld();
    }

    @Override
    protected void renderBg(@Nonnull MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.renderBackground(p_230450_1_);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - imageWidth) / 2;  //88
        int y = (height - imageHeight) / 2; //83
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(EMCWorld.rl("textures/gui/infuser_core.png"));
        blit(p_230450_1_,x,y,0,0,imageWidth,imageHeight);
        blit(p_230450_1_,x+98,y+30,176,0, this.menu.getProgress(),14);
        blit(p_230450_1_,x+90,y+70,176,14,this.menu.getRadiation(),3);
        blit(p_230450_1_,x+163,y+6,176,17,5,this.menu.getEMC());
        switch (this.menu.getCraftLevel()){
            case 1:
                blit(p_230450_1_,x+6,y+6,176,87,8,8);
                break;
            case 2:
                blit(p_230450_1_,x+6,y+6,176,87+8,8,8);
                break;
            case 3:
                blit(p_230450_1_,x+6,y+6,176,87+8+8,8,8);
                break;
            case 4:
                blit(p_230450_1_,x+6,y+6,176,87+8+8+8,8,8);
                break;
        }
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
        renderTooltip(matrixStack,mouseX,mouseY);

    }

    @Override
    protected void renderLabels(@Nonnull MatrixStack matrixStack, int x, int y) {
        drawCenteredString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.infuser_core"),100,5,0xffffff);
    }
}
