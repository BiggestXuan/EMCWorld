package biggestxuan.emcworld.common.utils.Lottery;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/07
 */

import biggestxuan.emcworld.common.data.LotteryData;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotteryUtils {
    public static long getBuyPrice(Lottery lottery){
        return getBuyPrice(lottery.getRate(),lottery.getNum().size(),lottery.getAdd().size(),lottery.getMode());
    }

    public static long getBuyPrice(int rate,int base,int add,LotteryMode mode){
        if(base + add < 7){
            return 0L;
        }
        long emc = 1000L * rate;
        if(mode == LotteryMode.SIMPLEX){
            return emc;
        }
        if(mode == LotteryMode.COMPOUND){
            return emc * MathUtils.C(base+add,7);
        }
        if(mode == LotteryMode.TOW_BALL){
            return emc * MathUtils.C(add,7-base);
        }
        return 0L;
    }

    public static List<Integer> getRandCode(){
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (i < 7){
            int num = MathUtils.getRangeRandom(1,30);
            if(!list.contains(num)){
                list.add(num);
                i++;
            }
        }
        Collections.sort(list);
        return list;
    }

    public static long getLotteryPrice(Lottery lottery,List<Integer> win,MinecraftServer server){
        long emc = 0;
        List<List<Integer>> list = lottery.getAll();
        for(List<Integer> i : list){
            emc += getLotteryEMC(getLotteryNum(win,i),server);
        }
        return emc * lottery.getRate();
    }

    public static int getLotteryNum(List<Integer> win,List<Integer> your){
        int count = 0;
        for(int i : win){
            if(your.contains(i)){
                count ++;
            }
        }
        return count;
    }

    public static long getLotteryEMC(int index, MinecraftServer server){
        switch (index){
            case 7:
                long base = (long) (LotteryData.getInstance(server).getStoredEMC() * 0.1);
                return Math.max(Math.min(base,200000000L),30000000);
            case 6:
                return 1500000L;
            case 5:
                return 50000L;
            case 4:
                return 3000L;
            default:
                return 0;
        }
    }
}
