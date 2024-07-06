package biggestxuan.emcworld.common.items;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/24
 */

import biggestxuan.emcworld.api.item.EMCWorldBaseItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.item.Rarity;

public class EWItem extends EMCWorldBaseItem {
    public static final Properties EWProperties = new Properties().tab(EWCreativeTabs.EW_CREATIVE_TAB);

    public EWItem(Properties properties){
        super(properties);
    }

    public EWItem(int maxSize){
        super(new Properties().tab(EWCreativeTabs.EW_CREATIVE_TAB).stacksTo(maxSize));
    }

    public EWItem(Rarity rarity){
        super(rarity);
    }

    public EWItem() {
        super();
    }
}

