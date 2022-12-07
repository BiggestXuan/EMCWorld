package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/03
 */

public enum DifficultySetting{
    eight("Eight",4000000.0,7000000.0,600000.0,800000.0,50000.0, 24000.0,500),
    seven("Seven",350000.0,1000000.0,75000.0,100000.0,13000.0, 7500.0,375),
    six("Six",42500.0,150000.0,14500.0,17000.0,2750.0, 1800.0,225),
    five("Five",5000.0,10000,1350.0,1985.0,168.5, 480.0,125),
    four("Four",600.0,775,255.5,345.5,26.5, 180.0,85),
    three("Three",65.0,85.5,25,45.5,3.5, 120.0,55),
    two("Two",8.5,12.0,4.5,6.5,0.65, 36.0,30),
    one("One",1.0,2.0,1.2,1.5,0.15, 12.0,20),
    start("Start",0.0,0.5,0.3,0.15,0.0, 3.0,0);

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