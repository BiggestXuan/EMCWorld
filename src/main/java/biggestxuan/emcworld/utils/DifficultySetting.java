package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/03
 */

public enum DifficultySetting{
    eight("Eight",3000000.0,7000000.0,600000.0,800000.0,50000.0, 25000.0,1000),
    seven("Seven",250000.0,1000000.0,75000.0,100000.0,13000.0, 7500.0,675),
    six("Six",32500.0,150000.0,14500.0,17000.0,2750.0, 1800.0,425),
    five("Five",3000.0,10000,1350.0,1985.0,168.5, 485.0,225),
    four("Four",420.0,775,255.5,345.5,26.5, 185.0,125),
    three("Three",45.0,85.5,25,62.5,3.5, 120.0,75),
    two("Two",6.0,12.0,4.5,8.0,0.65, 35.0,40),
    one("One",1.0,2.0,0.8,2.0,0.15, 10.0,20),
    start("Start",0.0,0.5,0.2,0.3,0.0, 3.0,0);

    private final String gameStage;
    private final double deathBase;
    private final double hurtBase;
    private final double attackBase;
    private final double blockBase;
    private final double chestBase;
    private final double ftbBase;
    private final double difficulty;

    DifficultySetting(String gameStage, double deathBase, double hurtBase, double attackBase, double blockBase, double chestBase, double ftbBase, double difficulty){
        this.gameStage = gameStage;
        this.deathBase = deathBase;
        this.attackBase = attackBase;
        this.hurtBase = hurtBase;
        this.blockBase = blockBase;
        this.chestBase = chestBase;
        this.ftbBase = ftbBase;
        this.difficulty = difficulty;
    }

    public String getGameStage(){
        return gameStage;
    }

    public double getDeathBase(){
        return deathBase;
    }
    public double getAttackBase(){
        return attackBase;
    }
    public double getHurtBase(){
        return hurtBase;
    }
    public double getBlockBase(){
        return blockBase;
    }
    public double getChestBase(){
        return chestBase;
    }
    public double getFtbBase(){
        return ftbBase;
    }
    public double getDifficulty() {
        return difficulty;
    }
}