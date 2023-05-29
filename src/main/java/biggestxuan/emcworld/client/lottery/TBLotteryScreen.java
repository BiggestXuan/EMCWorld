package biggestxuan.emcworld.client.lottery;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.items.LotteryItem;
import biggestxuan.emcworld.common.utils.Lottery.LotteryMode;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;

@OnlyIn(Dist.CLIENT)
public class TBLotteryScreen extends BuyLotteryScreen {
    public TBLotteryScreen() {
        super("tow_ball");
    }

    @Override
    protected LotteryMode getScreenLotteryMode() {
        return LotteryMode.TOW_BALL;
    }

    @Override
    protected boolean renderConfirmButton() {
        return true;
    }

    @Override
    protected void subRender(MatrixStack stack){
        drawCenteredString(stack,font, EMCWorld.tc("tooltip.emcworld.lottery.tb_tip"),(int)(width*0.3),(int)(height*0.07),0xffffff);
        //drawCenteredString(stack,font, EMCWorld.tc("tooltip.emcworld.lottery.tb_tip"),(int)(width*0.1),(int)(height*0.15),0xffffff);
        drawString(stack,font, EMCWorld.tc("tooltip.emcworld.lottery.tow", LotteryItem.getString(numList)),(int)(width*0.25),(int)(height*base),0xffffff);
        drawString(stack,font, EMCWorld.tc("tooltip.emcworld.lottery.ball",LotteryItem.getString(addList)),(int)(width*0.25),(int)(height*base*1.2),0xffffff);
    }
}
