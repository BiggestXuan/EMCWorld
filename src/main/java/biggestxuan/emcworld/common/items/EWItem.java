package biggestxuan.emcworld.common.items;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/24
 */

import biggestxuan.emcworld.api.item.EMCWorldBaseItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;

public class EWItem extends EMCWorldBaseItem {
    public EWItem() {
        super();
    }

    public EWItem(Properties properties){
        super(properties);
    }

    public EWItem(Integer maxSize){
        super(new Properties().tab(EWCreativeTabs.EW_CREATIVE_TAB).stacksTo(maxSize));
    }

    public EWItem(int rarity){
        super(rarity);
    }
}

