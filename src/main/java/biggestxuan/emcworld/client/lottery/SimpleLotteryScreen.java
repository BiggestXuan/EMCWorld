package biggestxuan.emcworld.client.lottery;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/07
 */

import biggestxuan.emcworld.common.utils.Lottery.LotteryMode;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SimpleLotteryScreen extends BuyLotteryScreen {
    public SimpleLotteryScreen() {
        super("simple");
    }

    @Override
    protected LotteryMode getScreenLotteryMode() {
        return LotteryMode.SIMPLEX;
    }

    @Override
    protected boolean renderConfirmButton() {
        return true;
    }

    @Override
    public boolean canSend(){
        var flag = super.canSend();
        if(numList.size() != 7){
            flag = false;
        }
        return flag;
    }
}
