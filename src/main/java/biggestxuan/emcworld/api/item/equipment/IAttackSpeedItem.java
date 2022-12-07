package biggestxuan.emcworld.api.item.equipment;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/05
 */

import net.minecraft.item.ItemStack;

public interface IAttackSpeedItem {
    double getAttackSpeed(ItemStack stack);
}
