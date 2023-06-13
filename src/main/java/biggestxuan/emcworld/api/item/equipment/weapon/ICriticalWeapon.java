package biggestxuan.emcworld.api.item.equipment.weapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/17
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.ItemStack;

public interface ICriticalWeapon {
    default double getActCriticalChance(ItemStack stack){
        return Math.min(getCriticalChance(stack),1D);
    }

    default double getActCriticalRate(ItemStack stack){
        double a = 0;
        if(getCriticalChance(stack) > 1 && ConfigManager.UPGRADE_CRITICAL_OVERFLOW.get()){
            a = getCriticalChance(stack) -1;
        }
        return getCriticalRate(stack) + a;
    }

    double getCriticalChance(ItemStack stack);

    double getCriticalRate(ItemStack stack);
}
