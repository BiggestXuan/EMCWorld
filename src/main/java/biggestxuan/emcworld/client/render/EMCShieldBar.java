package biggestxuan.emcworld.client.render;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/28
 */

import biggestxuan.emcworld.EMCWorld;
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

public class EMCShieldBar implements BarOverlay {
    private static final ResourceLocation ICON = EMCWorld.rl("textures/gui/icon.png");
    private boolean side;

    @Override
    public boolean shouldRender(PlayerEntity playerEntity) {
        return playerEntity != null && !playerEntity.isDeadOrDying() && EMCWorldAPI.getInstance().getUtilCapability(playerEntity).getMaxShield() > 0;
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
    public void renderBar(MatrixStack matrixStack, PlayerEntity playerEntity, int i, int i1) {
        if(playerEntity == null || playerEntity.isDeadOrDying()) return;
        int a = i - 90;
        int b = i1 - this.getSidedOffset() + 28;
        matrixStack.pushPose();
        GlStateManager._enableBlend();
        Color.reset();
        ModUtils.drawTexturedModalRect(matrixStack,a,b, 0, 0, 81, 9);
        ColorUtils.hex2Color("#C1EEFF").color2Gl();
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
            int a = i - 112;
            int b = i1 - this.getSidedOffset() + 28;
            String value = ar.getShield() == ar.getMaxShield() ? String.valueOf((int)ar.getMaxShield()) : String.format("%.1f",ar.getShield());
            ModUtils.drawStringOnHUD(matrixStack, value,a,b-1,0xc1eeff);
        });
    }

    @Override
    public void renderIcon(MatrixStack matrixStack, PlayerEntity playerEntity, int i, int i1) {
        int a = i - 119;
        int b = i1 - this.getSidedOffset() + 29;
        ModUtils.mc.getTextureManager().bind(ICON);
        GlStateManager._enableBlend();
        ModUtils.drawTexturedModalRect(matrixStack, a,b, 0, 0, 8, 8);
    }

    @Override
    public String name() {
        return "emc_shield";
    }

    private int getPercents() {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if(player == null || player.isDeadOrDying()){
            return 0;
        }
        float a = EMCWorldAPI.getInstance().getUtilCapability(player).getShield();
        float m = EMCWorldAPI.getInstance().getUtilCapability(player).getMaxShield();
        return (int) (a / m * 79F);
    }
}
