package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/22
 */

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import noobanidus.mods.lootr.client.item.SpecialLootChestItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SpecialLootChestItemRenderer.class)
public abstract class SpecialLootChestItemRendererMixin extends ItemStackTileEntityRenderer {
    @Override
    public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay){

    }
}
