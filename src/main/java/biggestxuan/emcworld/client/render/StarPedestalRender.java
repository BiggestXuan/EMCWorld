package biggestxuan.emcworld.client.render;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/04
 */

import biggestxuan.emcworld.common.blocks.tile.StarPedestalTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import hellfirepvp.astralsorcery.client.util.RenderingDrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

public class StarPedestalRender extends TileEntityRenderer<StarPedestalTileEntity> {
    public StarPedestalRender(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void render(StarPedestalTileEntity p_225616_1_, float p_225616_2_, MatrixStack matrix, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
        Minecraft minecraft = Minecraft.getInstance();
        ItemStack stack = p_225616_1_.getInventory().getItem(0);
        if (!stack.isEmpty()) {
            matrix.pushPose();
            matrix.translate(0.5D, 0.7D, 0.5D);
            float scale = stack.getItem() instanceof BlockItem ? 0.9F : 0.65F;
            matrix.scale(scale, scale, scale);
            double tick = System.currentTimeMillis() / 800.0D;
            matrix.translate(0.0D, Math.sin(tick % (2 * Math.PI)) * 0.065D, 0.0D);
            matrix.mulPose(Vector3f.YP.rotationDegrees((float) ((tick * 40.0D) % 360)));
            minecraft.getItemRenderer().renderStatic(stack, ItemCameraTransforms.TransformType.GROUND, p_225616_5_, p_225616_6_, matrix, p_225616_4_);
            matrix.popPose();
        }
        long id = p_225616_1_.getBlockPos().asLong();
        matrix.pushPose();
        matrix.translate(0.5F, 2.5F, 0.5F);
        RenderingDrawUtils.renderLightRayFan(matrix, p_225616_4_, getColor(p_225616_1_), id * 31L, 15, 1.5F, 35);
        //RenderingDrawUtils.renderLightRayFan(matrix, p_225616_4_, ColorsAS.CELESTIAL_CRYSTAL, id * 16L, 10, 1F, 25);
        matrix.popPose();
    }

    private Color getColor(StarPedestalTileEntity tile){
        int level = tile.getCraftLevel();
        switch (level){
            case 1:
                return Color.GREEN;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.MAGENTA;
            case 4:
                return Color.ORANGE;
        }
        return Color.WHITE;
    }
}
