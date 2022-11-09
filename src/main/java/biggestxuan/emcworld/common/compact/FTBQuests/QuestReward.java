package biggestxuan.emcworld.common.compact.FTBQuests;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/09
 */

public enum QuestReward {
    EASY("easy", 500.0),
    NORMAL("normal", 3000.0),
    HARD("hard", 15000.0),
    EPIC("epic", 100000.0);

    private final String tag;
    private final double baseEMC;
    private QuestReward(String tag, double baseEMC){
        this.tag = tag;
        this.baseEMC = baseEMC;
    }
    public String getTag(){
        return tag;
    }
    public double getBaseEMC(){
            return baseEMC;
        }
}

