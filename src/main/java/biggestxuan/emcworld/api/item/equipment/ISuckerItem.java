package biggestxuan.emcworld.api.item.equipment;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.item.ItemStack;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/27
 */
@EMCWorldSince("1.2.0-Fix1")
public interface ISuckerItem {
    double getSuckerRate(ItemStack stack);

    default int getCoolDown(ItemStack stack){
        return 120;
    }
}
