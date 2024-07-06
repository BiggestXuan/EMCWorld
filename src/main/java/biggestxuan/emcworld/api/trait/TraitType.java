package biggestxuan.emcworld.api.trait;

import biggestxuan.emcworld.api.EMCWorldSince;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

@EMCWorldSince("1.1.0")
public enum TraitType {
    TOOL("tool"),ARMOR("armor"),OTHER("other");

    private final String name;

    TraitType(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}