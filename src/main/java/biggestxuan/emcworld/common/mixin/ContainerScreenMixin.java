package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/11
 */

@EMCWorldSince("1.0.5")
@Mixin(ContainerScreen.class)
public abstract class ContainerScreenMixin extends Screen {
    @Shadow @Final protected PlayerInventory inventory;

    @Shadow protected int titleLabelX;

    @Shadow protected int titleLabelY;

    @Shadow protected int inventoryLabelX;

    @Shadow protected int inventoryLabelY;

    protected ContainerScreenMixin(ITextComponent p_i51108_1_) {
        super(p_i51108_1_);
    }

    /**
     * @author Biggest_Xuan
     * @reason color.
     */
    @Overwrite
    protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
        font.draw(p_230451_1_, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 0xffffff);
        font.draw(p_230451_1_, this.inventory.getDisplayName(), (float)this.inventoryLabelX, (float)this.inventoryLabelY, 0xffffff);
    }
}
