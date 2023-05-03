package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/23
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.blay09.mods.waystones.api.IWaystone;
import net.blay09.mods.waystones.client.gui.widget.WaystoneButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Mixin(WaystoneButton.class)
public abstract class WayStoneButtonMixin extends Button {

    private static final ResourceLocation ICON = EMCWorld.rl("textures/item/small_emc_gem.png");

    public WayStoneButtonMixin(int p_i232255_1_, int p_i232255_2_, int p_i232255_3_, int p_i232255_4_, ITextComponent p_i232255_5_, IPressable p_i232255_6_) {
        super(p_i232255_1_, p_i232255_2_, p_i232255_3_, p_i232255_4_, p_i232255_5_, p_i232255_6_);
    }

    @Shadow(remap = false)
    @Final
    private int xpLevelCost;

    @Inject(method = "<init>",at = @At("RETURN"),remap = false)
    public void button(int x, int y, IWaystone waystone, int xpLevelCost, IPressable pressable, CallbackInfo ci){
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(player != null){
            active = EMCHelper.getPlayerEMC(player) >= xpLevelCost;
        }
    }

    /***
     * @author Biggest_Xuan
     * @reason Overwrite button render.
     */
    @Override
    @Overwrite(remap = false)
    @OnlyIn(Dist.CLIENT)
    public void renderButton(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.renderButton(matrixStack, mouseX, mouseY, partialTicks);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft mc = Minecraft.getInstance();
        if (this.xpLevelCost > 0 && mc.player != null) {
            boolean canTP = EMCHelper.getPlayerEMC(mc.player) >= this.xpLevelCost || mc.player.abilities.instabuild;
            mc.getTextureManager().bind(ICON);
            blit(matrixStack, x+2, y+2, 0,0, 16, 16);
            mc.font.draw(matrixStack, MathUtils.format(this.xpLevelCost),x+6,y+6,0xC8FF8F);
            if (this.isHovered && mouseX <= x + 24) {
                List<ITextComponent> tooltip = new ArrayList<>();
                TranslationTextComponent text = Screen.hasShiftDown() ? EMCWorld.tc("gui.waystone.emc",MathUtils.thousandSign(xpLevelCost)) : EMCWorld.tc("gui.waystone.emc",MathUtils.format(xpLevelCost));
                text.withStyle(canTP ? TextFormatting.GREEN : TextFormatting.RED);
                tooltip.add(text);
                Screen screen = mc.screen;
                if(screen != null){
                    screen.renderComponentTooltip(matrixStack, tooltip, mouseX, mouseY + 9);
                }
            }
            RenderSystem.disableLighting();
        }
    }
}
