package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/02
 */

import java.util.ArrayList;
import java.util.List;

public class DamageUtils {
    private double damage;
    private final List<Double> addDamage;

    public DamageUtils(double damage,List<Double> damages){
        this.damage = damage;
        this.addDamage = damages;
    }

    public DamageUtils(double damage){
        this(damage,new ArrayList<>());
    }

    public DamageUtils set(double value){
        this.damage = value;
        return this;
    }

    public DamageUtils append(List<Double> value){
        addDamage.addAll(value);
        return this;
    }

    public DamageUtils append(double value){
        addDamage.add(value);
        return this;
    }

    public static DamageUtils of(double d){
        return new DamageUtils(d);
    }

    public double getDamage() {
        return damage;
    }

    public List<Double> getAddDamage(){
        return addDamage;
    }

    public double total(){
        double t = damage;
        for(double v : addDamage){
            t += v;
        }
        return t;
    }
}
