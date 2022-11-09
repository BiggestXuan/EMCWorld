package biggestxuan.emcworld.api.item.base;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/25
 */

import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.item.Item;

public abstract class BaseCuriosItem extends EWItem {
    public BaseCuriosItem(int durability){
        super(new Item.Properties().durability(durability).tab(EWCreativeTabs.EW_EQUIPMENT_TAB));
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

    @Override
    public double getEMCCostRate(){
        return 0d;
    }
}
