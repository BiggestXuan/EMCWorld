package biggestxuan.emcworld.common.traits;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.trait.ITrait;

import java.util.HashMap;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

@EMCWorldSince("1.1.0")
public class TraitColor {
    public static HashMap<String,Integer> TraitColorMap;

    public static void init(){
        TraitColorMap = new HashMap<>();
    }

    public static int getTraitColor(ITrait trait){
        return getTraitColor(trait.getRL().toString());
    }

    public static int getTraitColor(String name){
        var color = TraitColorMap.get(name);
        return color == null ? 0 : color;
    }
}
