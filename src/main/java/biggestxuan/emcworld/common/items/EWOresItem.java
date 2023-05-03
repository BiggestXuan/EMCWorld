package biggestxuan.emcworld.common.items;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/23
 */

import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.item.Item;

public class EWOresItem extends Item {
    public EWOresItem() {
        super(new Item.Properties().tab(EWCreativeTabs.EW_ORE_ITEMS));
    }
}
