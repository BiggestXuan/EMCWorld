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

    public int getSize(){
        return addDamage.size() + 1;
    }

    public double getBaseDamage(){
        return get(0);
    }

    public double get(int index){
        if(index == 0){
            return damage;
        }
        if(index > addDamage.size()){
            return 0;
        }
        return addDamage.get(index-1);
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

    public DamageUtils append(DamageUtils utils){
        this.addDamage.addAll(utils.addDamage);
        return this;
    }

    public DamageUtils append(double value){
        addDamage.add(value);
        return this;
    }

    public DamageUtils copy(){
        DamageUtils d = new DamageUtils(damage);
        d.append(addDamage);
        return d;
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

    @Override
    public String toString(){
        return "["+damage+"],("+new ArrayList<>(addDamage).toString()+")";
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof DamageUtils)){
            return false;
        }
        DamageUtils u = (DamageUtils) obj;
        if(this.getSize() != u.getSize() || this.getBaseDamage() != u.getBaseDamage()){
            return false;
        }
        for (int i = 0; i < this.getSize(); i++) {
            if(this.get(i) != u.get(i)){
                return false;
            }
        }
        return true;
    }
}
