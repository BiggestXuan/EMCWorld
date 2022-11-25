package biggestxuan.emcworld.common.blocks.AdvancedUpdateBlock;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/30
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.MultiBlock;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.recipes.AdvancedUpdateRecipe;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class AdvancedUpdateGUI extends ContainerScreen<AdvancedUpdateContainer> {
    private final BlockPos pos;
    private final World world;
    public AdvancedUpdateGUI(AdvancedUpdateContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.pos = p_i51105_1_.getPos();
        this.world = p_i51105_1_.getWorld();
    }

    @Override
    protected void renderBg(@Nonnull MatrixStack matrixStack, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.renderBackground(matrixStack);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(EMCWorld.rl("textures/gui/advanced_update_container.png"));
        blit(matrixStack,(this.width-imageWidth)/2,(this.height-imageHeight)/2,0,0,getXSize(),getYSize(),176,166);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        renderBackground(matrixStack);
        super.render(matrixStack,mouseX,mouseY,partialTicks);
        renderTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void renderLabels(@Nonnull MatrixStack matrixStack, int x, int y) {
        MultiBlock.UpdateMath info = MultiBlock.getUpdateInfo(world,pos);
        if(info.getLevel() == 0){
            info = new MultiBlock.UpdateMath();
        }
        drawCenteredString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.update_core"),85,5,0xffffff);
        drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.update_core_level",info.getLevel()),5,20,0xffffff);
        drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.update_core_cost",String.format("%.2f",info.getCost()*100)+"%"),5,35,0xffffff);
        drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.update_core_addon",String.format("%.2f",info.getAddon()*100)+"%"),5,50,0xffffff);
        drawString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.update_core_time",MathUtils.format(info.getTime()/20)),5,65,0xffffff);
        long reallyCost;
        ItemStack itemStack = this.getMenu().getItems().get(0);
        for(AdvancedUpdateRecipe recipes:AdvancedUpdateRecipe.values()){
            if(itemStack.getItem().equals(recipes.getInput().getItem())){
                reallyCost = (Math.round(recipes.costEMC() * MathUtils.difficultyLoss() * itemStack.getCount() * info.getCost()));
                String delay = MathUtils.format(reallyCost)+" EMC";
                if(EMCHelper.getPlayerEMC(Minecraft.getInstance().player) >= reallyCost && recipes.recipeLevel() <= info.getLevel()){
                    drawCenteredString(matrixStack, this.font, delay, 138, 20, 0xffffff);
                }
                else drawCenteredString(matrixStack, this.font, delay, 138, 20, 0xff0000);
                break;
            }
        }
        if(itemStack.isEmpty()){
            drawCenteredString(matrixStack, this.font, "0 EMC", 138, 20, 0xffffff);
        }
    }
}
