package biggestxuan.emcworld.client.render;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/20
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import tfar.classicbar.Color;
import tfar.classicbar.ColorUtils;
import tfar.classicbar.ModUtils;
import tfar.classicbar.overlays.BarOverlay;

public class ArcanaBar implements BarOverlay {
    private static final ResourceLocation ICON = new ResourceLocation("divinerpg", "textures/gui/arcana_bar.png");
    private boolean side;
    @Override
    public boolean shouldRender(PlayerEntity playerEntity) {
        if(playerEntity == null || playerEntity.isDeadOrDying()){
            return false;
        }
        return EMCWorldAPI.getInstance().getUtilCapability(playerEntity).showArcana();
    }

    @Override
    public boolean rightHandSide() {
        return side;
    }

    @Override
    public BarOverlay setSide(boolean b) {
        this.side = b;
        return this;
    }

    @Override
    public void renderBar(MatrixStack matrixStack, PlayerEntity playerEntity, int i, int i1){
        if(playerEntity == null || playerEntity.isDeadOrDying()) return;
        int a = i - 90;
        int b = i1 - this.getSidedOffset() + 35;
        matrixStack.pushPose();
        GlStateManager._enableBlend();
        Color.reset();
        ModUtils.drawTexturedModalRect(matrixStack,a,b, 0, 0, 81, 9);
        ColorUtils.hex2Color("#248BFF").color2Gl();
        ModUtils.drawTexturedModalRect(matrixStack,a+1, b+1, 1, 10, getPercents(), 7);
        matrixStack.popPose();
    }

    @Override
    public boolean shouldRenderText() {
        return true;
    }

    @Override
    public void renderText(MatrixStack matrixStack, PlayerEntity playerEntity, int i, int i1) {
        if(playerEntity == null || playerEntity.isDeadOrDying()) return;
        PlayerEntity player = Minecraft.getInstance().player;
        if(player == null) return;
        player.getCapability(EMCWorldCapability.UTIL).ifPresent((ar)->{
            int a = i - 106;
            int b = i1 - this.getSidedOffset() + 35;
            ModUtils.drawStringOnHUD(matrixStack, String.valueOf((int)ar.getArcana()),a,b-1,0x248BFF);
        });

    }

    @Override
    public void renderIcon(MatrixStack matrixStack, PlayerEntity playerEntity, int i, int i1) {
        int a = i - 114;
        int b = i1 - this.getSidedOffset() + 36;
        ModUtils.mc.getTextureManager().bind(ICON);
        GlStateManager._enableBlend();
        ModUtils.drawTexturedModalRect(matrixStack, a,b, 3, 10, 8, 7);
    }

    @Override
    public String name() {
        return "arcana";
    }

    private int getPercents() {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(player == null || player.isDeadOrDying()){
            return 0;
        }
        float a = EMCWorldAPI.getInstance().getUtilCapability(player).getArcana();
        float m = EMCWorldAPI.getInstance().getUtilCapability(player).getMaxArcana();
        return (int) (a / m * 79F);
    }
}
