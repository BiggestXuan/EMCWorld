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
public class CompoundLotteryScreen extends BuyLotteryScreen {
    public CompoundLotteryScreen() {
        super("compound");
    }

    @Override
    protected LotteryMode getScreenLotteryMode() {
        return LotteryMode.COMPOUND;
    }

    @Override
    protected boolean renderConfirmButton() {
        return true;
    }
}
