package biggestxuan.emcworld.client.screen;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.blocks.container.EMCCoreContainer;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.toServer.WeaponCoreButtonPacket;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/11/19
 */

@EMCWorldSince("1.1.0")
public abstract class EMCCoreScreen<T extends EMCCoreContainer> extends EMCWorldContainerHelperGUI<T> {
    public static class Assembler extends EMCCoreScreen<EMCCoreContainer.Assembler>{

        public Assembler(EMCCoreContainer.Assembler p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
            super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        }

        @Override
        protected String getTexture() {
            return "emc_core_assembler";
        }

        @Override
        protected int[] getXYPos(){
            return new int[]{120,20};
        }
    }

    public static class Puller extends EMCCoreScreen<EMCCoreContainer.Puller>{

        public Puller(EMCCoreContainer.Puller p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
            super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        }

        @Override
        protected String getTexture() {
            return "emc_core_puller";
        }

        @Override
        protected int[] getXYPos(){
            return new int[]{130,20};
        }
    }

    public static class Generator extends EMCCoreScreen<EMCCoreContainer.Generator>{

        public Generator(EMCCoreContainer.Generator p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
            super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        }

        @Override
        protected String getTexture() {
            return "emc_core_generator";
        }
    }

    public static class Puncher extends EMCCoreScreen<EMCCoreContainer.Puncher>{

        public Puncher(EMCCoreContainer.Puncher p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
            super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        }

        @Override
        protected String getTexture() {
            return "emc_core_generator";
        }
    }

    protected final World world;
    protected final BlockPos pos;
    protected T tile;

    public EMCCoreScreen(T p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        tile = p_i51105_1_;
        this.world = tile.world;
        this.pos = tile.pos;
    }

    @Override
    protected void init(){
        super.init();
        int x = (width - getXSize()) / 2;
        int y = (height - getYSize()) / 2;
        this.addButton(new Button(x + getXYPos()[0], y + getXYPos()[1], 40, 20, EMCWorld.tc("screen.emc.confirm"), p_onPress_1_ -> PacketHandler.sendToServer(new WeaponCoreButtonPacket(pos,0))));
    }

    protected int[] getXYPos(){
        return new int[]{120,60};
    }

    protected abstract String getTexture();

    @Override
    protected void renderBg(@Nonnull MatrixStack stack, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.renderBackground(stack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(EMCWorld.rl("textures/gui/"+getTexture()+".png"));
        blit(stack,(this.width-imageWidth)/2,(this.height-imageHeight)/2,0,0,getXSize(),getYSize(),176,166);
    }

    @Override
    public String getHelperLink() {
        return "https://www.kancloud.cn/biggest_xuan/emcworld/3203694";
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
        renderTooltip(matrixStack,mouseX,mouseY);
    }
}
