package biggestxuan.emcworld.api.item.equipment.warhammer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/04
 */

import net.minecraft.item.IItemTier;

public interface IWarHammerTier extends IItemTier {
    double getCriticalRate();

    double getCriticalChance();

    double getAttackRange();

    double lossSpeed();
}
