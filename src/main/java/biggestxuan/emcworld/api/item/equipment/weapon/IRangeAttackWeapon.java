package biggestxuan.emcworld.api.item.equipment.weapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/30
 */

import net.minecraft.item.ItemStack;

public interface IRangeAttackWeapon {
    double getAttackRange(ItemStack stack);
}
