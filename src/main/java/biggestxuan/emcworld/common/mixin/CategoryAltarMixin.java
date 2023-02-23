package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/10
 */

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import hellfirepvp.astralsorcery.client.util.RenderingConstellationUtils;
import hellfirepvp.astralsorcery.common.crafting.recipe.SimpleAltarRecipe;
import hellfirepvp.astralsorcery.common.integration.jei.CategoryAltar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CategoryAltar.class)
public abstract class CategoryAltarMixin {
    @Inject(
            method = "draw(Lhellfirepvp/astralsorcery/common/crafting/recipe/SimpleAltarRecipe;Lcom/mojang/blaze3d/matrix/MatrixStack;DD)V",
            at = @At(value = "INVOKE",target = "Lhellfirepvp/astralsorcery/client/util/RenderingConstellationUtils;renderConstellationIntoGUI(Ljava/awt/Color;Lhellfirepvp/astralsorcery/common/constellation/IConstellation;Lcom/mojang/blaze3d/matrix/MatrixStack;FFFFFDLjava/util/function/Supplier;ZZ)Ljava/util/Map;"),
            cancellable = true,remap = false
    )
    public void draw(SimpleAltarRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY, CallbackInfo ci){
        RenderingConstellationUtils.renderConstellationIntoGUI(recipe.getFocusConstellation().getConstellationColor(), recipe.getFocusConstellation(), matrixStack, 0.0F, 0.0F, 0.0F, 50.0F, 50.0F, 1.2000000476837158D, () -> 0.9F, true, false);
        RenderSystem.disableBlend();
        ci.cancel();
    }
}
