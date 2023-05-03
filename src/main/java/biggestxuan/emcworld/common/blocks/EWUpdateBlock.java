package biggestxuan.emcworld.common.blocks;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/22
 */

public class EWUpdateBlock extends EWBlock{
    private final int level;
    private final double addon;
    private final double time;
    private final double cost;

    public EWUpdateBlock(int level,double addon,double time,double cost) {
        super(5.0F);
        this.level = level;
        this.addon = addon;
        this.time = time;
        this.cost = cost;
    }

    public int getLevel(){
        return level;
    }

    public double getAddon(){
        return addon;
    }

    public double getTime(){
        return time;
    }

    public double getCost(){
        return cost;
    }

    @Override
    public float getExplosionResistance() {
        return 15 * level;
    }
}
