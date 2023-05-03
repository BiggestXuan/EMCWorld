package biggestxuan.emcworld.common.compact.Champions.Rank;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/15
 */

import top.theillusivec4.champions.common.rank.Rank;

import java.util.ArrayList;

public enum RankEnum {
    Level5(5,12,0.15),
    Level6(6,20,0.1),
    Level7(7,36,0.05),
    Level8(8,64,0.025),
    ;
    private final Rank rank;

    RankEnum(int tier,int growthFactor, double chance){
        this.rank = new Rank(tier,tier,growthFactor,(float) chance,0,new ArrayList<>(),new ArrayList<>());
    }

    public Rank getRank() {
        return rank;
    }
}
