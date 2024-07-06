package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.EMCWorld;
import com.mojang.blaze3d.matrix.MatrixStack;
import hellfirepvp.astralsorcery.client.screen.journal.ScreenJournalPerkTree;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/13
 */

@Mixin(ScreenJournalPerkTree.class)
public abstract class ScreenJournalPerkTreeMixin extends Screen {
    protected ScreenJournalPerkTreeMixin(ITextComponent p_i51108_1_) {
        super(p_i51108_1_);
    }

    @Inject(method = "render",at = @At("RETURN"))
    public void _inject(MatrixStack renderStack, int mouseX, int mouseY, float pTicks, CallbackInfo ci){
        drawCenteredString(renderStack,this.minecraft.font,EMCWorld.tc("message.emcworld.disable"),width/2,height/2,0xff0000);
    }
}
