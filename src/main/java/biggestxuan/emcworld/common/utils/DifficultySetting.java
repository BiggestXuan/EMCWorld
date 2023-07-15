package biggestxuan.emcworld.common.utils;

import biggestxuan.emcworld.common.config.ConfigManager;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/03
 */

public enum DifficultySetting{
    eight(8,"Eight",80000000.0,12500000.0,1000000.0,1200000.0,200000.0,250000.0,500),
    seven(7,"Seven",1000000.0,500000.0,125000.0,150000.0,100000.0,100000.0,375),
    six(6,"Six",100000.0,80000.0,20000.0,20000.0,2400.5,40000.0,225),
    five(5,"Five",18000.0,15000,4550.0,2985.0,265.5,12500.0,125),
    four(4,"Four",2000.0,1775,850.0,450.5,35.5,3500.0,85),
    three(3,"Three",300.0,285.5,125,75.5,4.85,1200.0,55),
    two(2,"Two",25.0,24.0,12.5,10.5,0.65,300.0,30),
    one(1,"One",5.0,2.0,2.5,1.5,0.1,60,20),
    start(0,"Start",0.0,0.5,0.3,0.15,0.0, 10.0,0);

    private final String gameStage;
    private final double deathBase;
    private final double hurtBase;
    private final double attackBase;
    private final double commonBase;
    private final double chestBase;
    private final double ftbBase;
    private final double difficulty;
    private final int index;

    DifficultySetting(int index,String gameStage, double deathBase, double hurtBase, double attackBase, double commonBase, double chestBase, double ftbBase, double difficulty){
        this.index = index;
        this.gameStage = gameStage;
        this.deathBase = deathBase;
        this.attackBase = attackBase;
        this.hurtBase = hurtBase;
        this.commonBase = commonBase;
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

    public double getCommonBase(){
        return commonBase;
    }

    public double getChestBase(){
        return chestBase;
    }

    public double getFtbBase(){
        return ftbBase * 1.5;
    }

    public double getDifficulty() {
        return difficulty * ConfigManager.DIFFICULTY.get() / 3.0d;
    }

    public int getIndex() {
        return index;
    }
}