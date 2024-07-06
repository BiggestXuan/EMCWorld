package biggestxuan.emcworld.common.compact.MysticalAgriculture;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/12/14
 */

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.EMCWorldBaseItem;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.ICropTierProvider;

@EMCWorldSince("1.1.0")
public class EMCWorldEssenceItem extends EMCWorldBaseItem implements ICropTierProvider {
    public final CropTier tier;

    public EMCWorldEssenceItem(CropTier tier){
        this.tier = tier;
    }

    @Override
    public CropTier getTier() {
        return tier;
    }
}
