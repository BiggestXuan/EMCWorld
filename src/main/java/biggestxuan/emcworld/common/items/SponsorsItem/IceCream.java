package biggestxuan.emcworld.common.items.SponsorsItem;

import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.item.Food;

import javax.annotation.Nullable;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/14
 */

public class IceCream extends EWItem implements ISponsorItem {
    public static final Food ICE_FOOD = new Food.Builder().saturationMod(20f).nutrition(2).build();

    public IceCream(){
        super(new Properties().stacksTo(1).tab(EWCreativeTabs.EW_CREATIVE_TAB).food(ICE_FOOD));
    }

    @Nullable
    @Override
    public Sponsors getSponsor() {
        return Sponsors.all.SKY_LIN.getSponsors();
    }


}
