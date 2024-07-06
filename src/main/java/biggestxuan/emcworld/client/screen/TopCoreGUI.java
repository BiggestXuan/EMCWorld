package biggestxuan.emcworld.client.screen;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/14
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.client.screen.EMCWorldContainerHelperGUI;
import biggestxuan.emcworld.common.blocks.container.TopCoreContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TopCoreGUI extends EMCWorldContainerHelperGUI<TopCoreContainer> {
    private final BlockPos pos;
    private final World world;

    public TopCoreGUI(TopCoreContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        this.pos = p_i51105_1_.getPos();
        this.world = p_i51105_1_.getWorld();
    }

    @Override
    protected void init(){
        super.init();
    }

    @Override
    protected void renderBg(@Nonnull MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.renderBackground(p_230450_1_);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - imageWidth) / 2;  //88
        int y = (height - imageHeight) / 2; //83
        this.minecraft.getTextureManager().bind(EMCWorld.rl("textures/gui/top_core.png"));
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
        drawString(matrixStack,this.font, EMCWorld.tc("block.emcworld.top_core"),100,5,0xffffff);
    }

    @Override
    public String getHelperLink() {
        return "https://www.kancloud.cn/biggest_xuan/emcworld/3192292";
    }

}
