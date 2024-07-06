package biggestxuan.emcworld.common.utils.Lottery;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/07
 */

public enum LotteryMode {
    SIMPLEX(1),COMPOUND(2),TOW_BALL(3);

    private final int index;

    LotteryMode(int index){
        this.index = index;
    }

    public static LotteryMode getMode(int index){
        for(LotteryMode mode : LotteryMode.values()){
            if(mode.index == index){
                return mode;
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }
}
