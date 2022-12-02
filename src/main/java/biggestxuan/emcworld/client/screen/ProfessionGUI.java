package biggestxuan.emcworld.client.screen;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/27
 */


import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.PlayerLevel.PlayerLevelCapability;
import biggestxuan.emcworld.common.utils.MathUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ProfessionGUI extends Screen {
    private final PlayerLevelCapability cap;
    ResourceLocation bg = EMCWorld.rl("textures/gui/level_core.png");
    public ProfessionGUI(ITextComponent title, PlayerLevelCapability cap){
        super(title);
        this.cap = cap;
    }

    public void renderString(MatrixStack m,TranslationTextComponent t,int x, int y){
        drawString(m,this.font,t,x,y,0xffffff);
    }

    public void renderString(MatrixStack m,TranslationTextComponent t,int x, int y,int color){
        drawString(m,this.font,t,x,y,color);
    }

    public static String getProfessionName(int p){
        switch (p){
            case 1:
                return I18n.get("profession.emcworld.sword");
            case 2:
                return I18n.get("profession.emcworld.tank");
            case 3:
                return I18n.get("profession.emcworld.wizard");
        }
        return I18n.get("profession.emcworld.null");
    }

    private int getProfession(){
        return cap.getProfession();
    }

    private boolean isRenderSkill(int level){
        if(level == 100){
            return cap.getSkills()[40] !=0 && cap.getSkills()[41] !=0;
        }
        return cap.getMaxLevel() > level && cap.getLevel() >= level;
    }

    private static String format(int e){
        if(e >10000){
            return String.valueOf(Math.round(e/10000f));
        }
        return String.format("%.2f",e/100f)+"%";
    }

    private void renderSkills(MatrixStack matrixStack,TranslationTextComponent text,int yPos){
        renderString(matrixStack,text,this.width/16+20,yPos);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(matrixStack);
        int b = this.width/16;
        int xPos = b+20;
        int line = 16;
        int w = Math.min((int) Math.round(this.width * 0.8),550);
        int h = Math.min((int) Math.round(this.height * 0.8),275);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(bg);
        blit(matrixStack, this.width/12, 5, 0, 0, w, h, w, h);
        drawCenteredString(matrixStack,this.font, EMCWorld.tc("gui.emcworld.profession_title"),this.width/2-20,15,0xffffff);
        renderString(matrixStack, EMCWorld.tc("gui.emcworld.profession_info", getProfessionName(cap.getProfession())),b+20,30);
        renderString(matrixStack, EMCWorld.tc("gui.emcworld.profession_info1",cap.getLevel(),cap.getXP(),MathUtils.getNeedXPToLevel(cap.getLevel())),this.width/2-45,30);
        renderString(matrixStack, EMCWorld.tc("gui.emcworld.profession_info2",cap.getMaxLevel()), (int) (this.width*0.75),30);
        String base = "skills.emcworld.";
        int[] skillData = cap.getSkills();
        int yPos = 50;
        List<String> skill = new ArrayList<>();
        for(int i :skillData){
            skill.add(format(i));
        }
        String[] skillInfo = skill.toArray(new String[44]);
        if(getProfession() ==1) {
            renderString(matrixStack, EMCWorld.tc(base + "p1_default", skillInfo[0]), xPos, yPos);
        } else if(getProfession() == 2) {
            renderString(matrixStack, EMCWorld.tc(base + "p2_default", skillInfo[0]), xPos, yPos);
        } else if(getProfession() == 3) {
            renderString(matrixStack, EMCWorld.tc(base + "p3_default", skillInfo[0]), xPos, yPos);
        }
        yPos += line;
        int level = 10;
        String text = base+"p"+cap.getProfession()+"_skill";
        if(isRenderSkill(level)){
            renderSkills(matrixStack, EMCWorld.tc(text+level,skillInfo[4],skillInfo[5]),yPos);
            yPos += line;
            level += 10;
        }
        if(isRenderSkill(level)){
            renderSkills(matrixStack, EMCWorld.tc(text+level,skillInfo[8]),yPos);
            yPos += line;
            level += 10;
        }
        if(isRenderSkill(level)){
            renderSkills(matrixStack, EMCWorld.tc(text+level,skillInfo[12]),yPos);
            yPos += line;
            level += 10;
        }
        if(isRenderSkill(level)){
            renderSkills(matrixStack, EMCWorld.tc(text+level,skillInfo[16],skillInfo[17],skillInfo[18]),yPos);
            yPos += line;
            level += 10;
        }
        if(isRenderSkill(level)){
            renderSkills(matrixStack, EMCWorld.tc(text+level,skillInfo[20]),yPos);
            yPos += line;
            level += 10;
        }
        if(isRenderSkill(level)){
            renderSkills(matrixStack, EMCWorld.tc(text+level,skillInfo[24]),yPos);
            yPos += line;
            level += 10;
        }
        if(isRenderSkill(level)){
            renderSkills(matrixStack, EMCWorld.tc(text+level,skillInfo[28]),yPos);
            yPos += line;
            level += 10;
        }

        if(cap.getLevel() >= 80){
            if(cap.getModify() == 1 && cap.getProfession() == 1){
                renderString(matrixStack, EMCWorld.tc("profession.emcworld.modify_kill"),xPos,yPos,0xff0000);
            }
            if(cap.getModify() == 2 && cap.getProfession() == 1){
                renderString(matrixStack, EMCWorld.tc("profession.emcworld.modify_blood"),xPos,yPos,0xff0000);
            }
            if(cap.getModify() == 1 && cap.getProfession() == 2){
                renderString(matrixStack, EMCWorld.tc("profession.emcworld.modify_shied"),xPos,yPos,0xff0000);
            }
            if(cap.getModify() == 2 && cap.getProfession() == 2){
                renderString(matrixStack, EMCWorld.tc("profession.emcworld.modify_addon"),xPos,yPos,0xff0000);
            }
            if(cap.getModify() == 1 && cap.getProfession() == 3){
                renderString(matrixStack, EMCWorld.tc("profession.emcworld.modify_big_wizard"),xPos,yPos,0xff0000);
            }
            if(cap.getModify() == 2 && cap.getProfession() == 3){
                renderString(matrixStack, EMCWorld.tc("profession.emcworld.modify_protect_wizard"),xPos,yPos,0xff0000);
            }
        }
        yPos += line;
        if(isRenderSkill(level)){
            renderSkills(matrixStack, EMCWorld.tc(text+level+"_addon"+cap.getModify(),skillInfo[32],skillInfo[33],skillInfo[34],skillInfo[35]),yPos);
            yPos += line;
            level += 10;
        }
        if(isRenderSkill(level)){
            renderSkills(matrixStack, EMCWorld.tc(text+level+"_addon"+cap.getModify(),skillInfo[36],skillInfo[37]),yPos);
            yPos += line;
            level += 10;
        }
        if(isRenderSkill(level)){
            renderSkills(matrixStack, EMCWorld.tc(text+level+"_addon"+cap.getModify(),skillInfo[40],skillInfo[41]),yPos);
        }
    }
    public static class open{
        public open(PlayerLevelCapability info){
            Minecraft.getInstance().setScreen(new ProfessionGUI(EMCWorld.tc("gui.emcworld.profession"),info));
        }
    }
}
