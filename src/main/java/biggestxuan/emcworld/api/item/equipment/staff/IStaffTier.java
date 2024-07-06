package biggestxuan.emcworld.api.item.equipment.staff;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/21
 */

import net.minecraft.item.IItemTier;

public interface IStaffTier extends IItemTier {

    double getCriticalRate();

    double getCriticalChance();

    int getColor();

    double getBurstSpeed();

    default int getUseLevel(){
        return 0;
    }
}
