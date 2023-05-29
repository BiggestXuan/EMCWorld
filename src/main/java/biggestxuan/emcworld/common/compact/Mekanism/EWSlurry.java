package biggestxuan.emcworld.common.compact.Mekanism;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/26
 */

public class EWSlurry {
    private final String name;
    private final int tint;

    public EWSlurry(String name, int tint) {
        this.name = name;
        this.tint = tint;
    }
    public String getName(){
        return name;
    }
    public int getTint(){
        return tint;
    }
}
