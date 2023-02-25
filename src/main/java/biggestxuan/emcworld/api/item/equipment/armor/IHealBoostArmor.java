package biggestxuan.emcworld.api.item.equipment.armor;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/25
 */

import net.minecraft.item.ItemStack;

public interface IHealBoostArmor {
    double getHealBoostRate(ItemStack stack);
}
