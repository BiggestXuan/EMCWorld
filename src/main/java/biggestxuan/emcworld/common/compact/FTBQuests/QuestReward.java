package biggestxuan.emcworld.common.compact.FTBQuests;

import biggestxuan.emcworld.common.config.ConfigManager;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/09
 */

public enum QuestReward {
    EASY("easy", 750.0),
    NORMAL("normal", 3750.0),
    HARD("hard", 25500.0),
    EPIC("epic", 225000.0);

    private final String tag;
    private final double baseEMC;
    QuestReward(String tag, double baseEMC){
        this.tag = tag;
        this.baseEMC = baseEMC;
    }
    public String getTag(){
        return tag;
    }
    public double getBaseEMC(){
            return baseEMC * ConfigManager.DIFFICULTY.get() / 3d;
        }
}

