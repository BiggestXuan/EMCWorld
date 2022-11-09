package biggestxuan.emcworld.client.hud;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/31
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;


public class EMCWorldHUD extends AbstractGui {
    private final Minecraft mc;
    private final MatrixStack matrixStack;

    public EMCWorldHUD(MatrixStack matrixStack){
        this.mc = Minecraft.getInstance();
        this.matrixStack = matrixStack;
    }

    public void render(PlayerEntity player){
        long costEMC = MathUtils.getPlayerDeathBaseCost(player);
        String emc = "EMC: "+ MathUtils.thousandSign(String.valueOf(EMCHelper.getPlayerEMC(player))) +" ("+MathUtils.thousandSign(String.valueOf(costEMC))+")";
        RenderSystem.color4f(1.0f,1.0f,1.0f,1.0f);
        int y = 1;
        int color = 0xFFFFFF;
        int line = 11;
        if(Minecraft.getInstance().options.renderDebug) return;
        if(EMCHelper.getPlayerEMC(player) >= costEMC){
            drawString(matrixStack,mc.font,emc,1,y,color);
        }
        else drawString(matrixStack,mc.font,emc,1,y,0xFF0000);
        IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
        String level = "LV:"+cap.getLevel()+" ("+cap.getXP()+"/"+MathUtils.getNeedXPToLevel(cap.getLevel())+")";
        y += line;
        drawString(matrixStack,mc.font,level,1,y,color);
        y += line;
        IUtilCapability util = player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new);
        if(util.isRaid()){
            drawString(matrixStack,mc.font, EMCWorld.tc("hud.emcworld.raid_info"),1,y,color);
            y += line;
            drawString(matrixStack,mc.font, EMCWorld.tc("hud.emcworld.rain_statue").append(getRaidStatue(util.getState())),1,y,color);
            y += line;
            drawString(matrixStack,mc.font, EMCWorld.tc("hud.emcworld.rain_wave",util.getWave(),util.getMaxWave()),1,y,color);
            y += line;
            drawString(matrixStack,mc.font, EMCWorld.tc("hud.emcworld.raid_amount",util.getPillager()),1,y,color);
            y += line;
            int amount = util.getVillager();
            drawString(matrixStack,mc.font, EMCWorld.tc("hud.emcworld.raid_damage",String.format("%.0f",util.getRaidRate()*100)+"%"),1,y,color);
            y += line;
            if(amount <= 5) color = 0xFF0000;
            drawString(matrixStack,mc.font, EMCWorld.tc("hud.emcworld.raid_villager",amount),1,y,color);
        }
    }
    private TranslationTextComponent getRaidStatue(int i){
        String s;
        switch (i){
            case 4:
                s = "hud.emcworld.raid_cd";
                break;
            case 3:
                s = "hud.emcworld.raid_loss";
                break;
            case 2:
                s = "hud.emcworld.raid_win";
                break;
            default:
                s = "hud.emcworld.raid_doing";
                break;
        }
        return EMCWorld.tc(s);
    }
}
