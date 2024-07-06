package biggestxuan.emcworld.common.traits;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.trait.ITrait;
import biggestxuan.emcworld.common.traits.traits.GoldTrait;
import biggestxuan.emcworld.common.traits.traits.IronTrait;
import biggestxuan.emcworld.common.traits.traits.StoneTrait;

import java.util.ArrayList;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

@EMCWorldSince("1.1.0")
public class TraitManager {
    public static ArrayList<ITrait> list = new ArrayList<>();

    public static void init(){
        list = new ArrayList<>();
        TraitColor.init();
    }

    public static void register(){
        register(new StoneTrait(),new IronTrait(),new GoldTrait());
    }

    public static void register(ITrait trait){
        list.add(trait);
        TraitColor.TraitColorMap.put(trait.getRL().toString(), trait.getColor());
    }

    public static void register(ITrait ... traits){
        for(ITrait t : traits){
            register(t);
        }
    }

    public static void disableTrait(ITrait trait){
        if(list.remove(trait)){
            EMCWorld.LOGGER.info("Remove trait " + trait.getRL().toString() + " successfully!");
            return;
        }
        EMCWorld.LOGGER.error("Can not remove trait!");
    }
}
