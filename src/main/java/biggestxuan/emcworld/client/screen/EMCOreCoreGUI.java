package biggestxuan.emcworld.client.screen;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.blocks.container.EMCOreCoreContainer;
import biggestxuan.emcworld.common.blocks.tile.EMCOreCoreTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/09
 */

@EMCWorldSince("1.0.5")
public class EMCOreCoreGUI extends EMCWorldContainerHelperGUI<EMCOreCoreContainer> {
    private final World world;
    private final BlockPos pos;

    public EMCOreCoreGUI(EMCOreCoreContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        this.world = p_i51105_1_.world;
        this.pos = p_i51105_1_.pos;
    }

    @Override
    public String getHelperLink() {
        return "https://www.kancloud.cn/biggest_xuan/emcworld/3203694";
    }

    @Override
    protected void renderBg(@Nonnull MatrixStack stack, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.renderBackground(stack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.minecraft.getTextureManager().bind(EMCWorld.rl("textures/gui/emc_ore.png"));
        blit(stack,x,y,0,0,imageWidth,imageHeight);

        EMCOreCoreTileEntity tile = (EMCOreCoreTileEntity) world.getBlockEntity(pos);
        assert tile != null;
        blit(stack,x+35,y+33,176,0,17,getRate(tile.progress,tile.maxProgress,17));
        blit(stack,x+161,y+7,176,17,5,getRate(tile.emc,tile.maxEMC,70));
    }

    private static int getRate(long value,long max,int length){
        double rate = max == 0 ? 1 : 1D - 1D * value / max;
        return (int) (length * rate);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
        renderTooltip(matrixStack,mouseX,mouseY);
    }
}
