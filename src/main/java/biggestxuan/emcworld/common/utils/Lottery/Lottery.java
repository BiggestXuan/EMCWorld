package biggestxuan.emcworld.common.utils.Lottery;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/07
 */

import biggestxuan.emcworld.common.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public final class Lottery {
    private final List<Integer> num;
    private final List<Integer> add;
    private final int rate;
    private final LotteryMode mode;

    public Lottery(List<Integer> num,List<Integer> add,int rate,LotteryMode mode){
        this.num = num;
        this.add = add;
        this.rate = rate;
        this.mode = mode;
    }

    public List<List<Integer>> getAll(){
        List<List<Integer>> list = new ArrayList<>();
        if(mode != LotteryMode.TOW_BALL){
            num.addAll(add);
            if(mode == LotteryMode.SIMPLEX){
                list.add(num);
                return list;
            }
            list = MathUtils.permute(num,7);
        }else{
            int a = num.size();
            List<List<Integer>> temp = MathUtils.permute(add,7-a);
            for(List<Integer> i : temp){
                List<Integer> t = MathUtils.copyList(num);
                t.addAll(i);
                list.add(t);
            }
        }
        return list;
    }

    public int getRate() {
        return rate;
    }

    public List<Integer> getAdd() {
        return add;
    }

    public List<Integer> getNum() {
        return num;
    }

    public LotteryMode getMode() {
        return mode;
    }
}
