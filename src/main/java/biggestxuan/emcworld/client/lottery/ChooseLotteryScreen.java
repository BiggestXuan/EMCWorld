package biggestxuan.emcworld.client.lottery;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/08
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.Lottery.LotteryMode;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;

public class ChooseLotteryScreen extends Screen {
    public ChooseLotteryScreen() {
        super(EMCWorld.tc("choose_lottery"));
    }

    @Override
    public void init(){
        super.init();
        this.addButton(new ChooseLotteryModeButton((int)(width * 0.22),(int)(height*0.5),"simple",LotteryMode.SIMPLEX));
        this.addButton(new ChooseLotteryModeButton((int)(width * 0.44),(int)(height*0.5),"double",LotteryMode.COMPOUND));
        this.addButton(new ChooseLotteryModeButton((int)(width * 0.66),(int)(height*0.5),"tb",LotteryMode.TOW_BALL));
    }

    @Override
    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(p_230430_1_);
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
        drawString(p_230430_1_,this.font,EMCWorld.tc("tooltip.emcworld.lottery.warn"),(int)(width*0.1),(int)(height*0.8),0xffffff);
    }

    private static class ChooseLotteryModeButton extends Button{
        private final LotteryMode mode;

        public ChooseLotteryModeButton(int p_i232255_1_, int p_i232255_2_, String name, LotteryMode mode) {
            super(p_i232255_1_, p_i232255_2_,50,20,EMCWorld.tc("tooltip.emcworld.lottery."+name),Button::onPress);
            this.mode = mode;
        }

        @Override
        public void onPress(){
            Minecraft mc = Minecraft.getInstance();
            switch (mode){
                case SIMPLEX:
                    mc.setScreen(new SimpleLotteryScreen());
                    break;
                case COMPOUND:
                    mc.setScreen(new CompoundLotteryScreen());
                    break;
                case TOW_BALL:
                    mc.setScreen(new TBLotteryScreen());
                    break;
                default:
                    mc.setScreen(null);
                    break;
            }
        }
    }
}
