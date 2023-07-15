package biggestxuan.emcworld.common.traits;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

@EMCWorldSince("1.1.0")
public class TraitManager {
    public static List<ITrait> list;

    public static void init(){
        list = new ArrayList<>();
        TraitColor.init();
    }

    public static void register(ITrait trait){
        list.add(trait);
        TraitColor.TraitColorMap.put(trait.getName().toString(), trait.getColor());
    }

    public static void register(ITrait ... traits){
        for(ITrait t : traits){
            register(t);
        }
    }

    public static void disableTrait(ITrait trait){
        if(list.remove(trait)){
            EMCWorld.LOGGER.info("Remove trait " + trait.getName().toString() + " successfully!");
            return;
        }
        EMCWorld.LOGGER.error("Can not remove trait!");
    }
}
