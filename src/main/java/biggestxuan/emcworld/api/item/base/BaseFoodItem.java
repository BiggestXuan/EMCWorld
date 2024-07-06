package biggestxuan.emcworld.api.item.base;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/24
 */

import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.item.Food;

public abstract class BaseFoodItem extends EWItem {
    public BaseFoodItem(int hunger, int saturation){
        super(new Properties().food(new Food.Builder().nutrition(hunger).saturationMod(saturation).build()).tab(EWCreativeTabs.EW_CREATIVE_TAB));
    }

    public BaseFoodItem(int hunger, float saturation,boolean hasCreativeTab){
        super(new Properties().food(new Food.Builder().nutrition(hunger).saturationMod(saturation).build()));
    }
}
