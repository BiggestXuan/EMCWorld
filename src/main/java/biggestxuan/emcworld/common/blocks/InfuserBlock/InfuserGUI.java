package biggestxuan.emcworld.common.blocks.InfuserBlock;

/**
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
        InfuserBlockTileEntity tile = (InfuserBlockTileEntity) world.getBlockEntity(pos);
        assert tile != null;
        int progress = tile.getProgress();
        int maxProgress = tile.getMaxProgress();
        int emc = tile.getEmc();
        int maxEMC = tile.getMaxEMC();
        int radiation = tile.getRadiation();
        int maxRadiation = tile.getMaxRadiation();
        int craftLevel = tile.getCraftLevel();
        blit(p_230450_1_,x,y,0,0,imageWidth,imageHeight);
        blit(p_230450_1_,x+98,y+30,176,0, getProgress(progress,maxProgress),14);
        blit(p_230450_1_,x+90,y+70,176,14,getRadiation(radiation,maxRadiation),3);
        blit(p_230450_1_,x+163,y+6,176,17,5,getEMC(emc,maxEMC));
        blit(p_230450_1_,x+6,y+6,176,87+(craftLevel-1)*8,8,8);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
        renderTooltip(matrixStack,mouseX,mouseY);
    }

    private int getProgress(int progress,int maxProgress){
        int progressArrowSize = 20;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    private int getRadiation(int radiation,int maxRadiation){
        return Math.min(57 * radiation / maxRadiation,57);
    }

    private int getEMC(int emc,int maxEMC){
        double rate = 1d * emc / maxEMC;
        int length = (int) Math.round(70 * rate);
        return Math.min(70,70 - length);
    }

    @Override
    protected void renderLabels(@Nonnull MatrixStack matrixStack, int x, int y) {
        drawCenteredString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.infuser_core"),100,5,0xffffff);
    }
}
