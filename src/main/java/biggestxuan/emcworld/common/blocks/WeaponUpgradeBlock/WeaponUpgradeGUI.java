package biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/14
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.network.PacketHandler;
import biggestxuan.emcworld.common.network.WeaponCoreButtonPacket;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class WeaponUpgradeGUI extends ContainerScreen<WeaponUpgradeContainer> {
    private final BlockPos pos;
    private final World world;
    private Button button;

    public WeaponUpgradeGUI(WeaponUpgradeContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        this.pos = p_i51105_1_.getPos();
        this.world = p_i51105_1_.getWorld();
    }

    @Override
    protected void init(){
        super.init();
        this.button = new Button(28*width/48, 8*height/24, 40, 20,EMCWorld.tc("gui.button.upgrade"),new ButtonPress(this.pos));
        //this.addButton(button);
    }

    @Override
    protected void renderBg(@Nonnull MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.renderBackground(p_230450_1_);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - imageWidth) / 2;  //88
        int y = (height - imageHeight) / 2; //83
        this.minecraft.getTextureManager().bind(EMCWorld.rl("textures/gui/weapon_upgrade_core.png"));
        blit(p_230450_1_,x,y,0,0,imageWidth,imageHeight);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
        renderTooltip(matrixStack,mouseX,mouseY);
        //this.button.renderButton(matrixStack,mouseX,mouseY,partialTicks);
    }

    @Override
    protected void renderLabels(@Nonnull MatrixStack matrixStack, int x, int y) {
        drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.weapon_upgrade_core"),100,5,0xffffff);
        int chance = this.menu.getChance();
        if(chance == 1919810){
            drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.weapon_upgrade_max"),100,30,0xffffff);
            return;
        }
        if(chance != 114514){
            drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.weapon_upgrade_chance",String.format("%.2f",chance/100f)+"%"),100,30,0xffffff);
        }
    }

    static class ButtonPress implements Button.IPressable{
        private final BlockPos pos;

        ButtonPress(BlockPos pos){
            this.pos = pos;
        }

        @Override
        public void onPress(Button button){
            PacketHandler.sendToServer(new WeaponCoreButtonPacket(this.pos));
        }
    }
}
