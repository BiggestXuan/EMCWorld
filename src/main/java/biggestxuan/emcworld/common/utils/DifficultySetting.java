package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/03
 */

public enum DifficultySetting{
    eight("Eight",10000000.0,7000000.0,600000.0,800000.0,50000.0, 30000.0,500),
    seven("Seven",700000.0,1000000.0,75000.0,100000.0,13000.0, 12500.0,375),
    six("Six",75000.0,150000.0,14500.0,17000.0,1200.5, 6000.0,225),
    five("Five",8000.0,10000,1350.0,1985.0,175.5, 3500.0,125),
    four("Four",850.0,775,255.5,345.5,23.5, 1200.0,85),
    three("Three",90.0,85.5,25,45.5,2.95, 350.0,55),
    two("Two",9.5,12.0,4.5,6.5,0.35, 100.0,30),
    one("One",1.0,2.0,1.2,1.5,0.1, 45.0,20),
    start("Start",0.0,0.5,0.3,0.15,0.0, 10.0,0);

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