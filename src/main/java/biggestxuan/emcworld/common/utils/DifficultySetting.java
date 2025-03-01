package biggestxuan.emcworld.common.utils;

import biggestxuan.emcworld.common.config.ConfigManager;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/03
 */

public enum DifficultySetting{
    eight(8,"Eight",10000000.0,2000000.0,300000,700000.0,8000,1425000.0,500),
    seven(7,"Seven",700000.0,300000.0,80000.0,150000.0,3600,175000,300),
    six(6,"Six",70000.0,80000.0,16000.0,20000.0,2400.5,60000,175),
    five(5,"Five",18000.0,12000,2850.0,2085.0,265.5,22500.0,100),
    four(4,"Four",2000.0,1775,850.0,450.5,35.5,5000.0,85),
    three(3,"Three",300.0,285.5,125,85.5,4.85,1200.0,55),
    two(2,"Two",25.0,24.0,12.5,27.5,0.65,155,30),
    one(1,"One",5.0,2.0,2.5,4.5,0.1,17,20),
    start(0,"Start",0.0,0.5,0.3,0.5,0.0, 2.5,0);

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
        return deathBase * 1.25;
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