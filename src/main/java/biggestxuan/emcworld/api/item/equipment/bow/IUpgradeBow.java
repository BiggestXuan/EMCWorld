package biggestxuan.emcworld.api.item.equipment.bow;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/08
 */

import biggestxuan.emcworld.api.item.IUpgradeableItem;
import net.minecraft.item.ItemStack;

public interface IUpgradeBow extends IUpgradeableItem {
    float getAdditionDamage(ItemStack stack);

    float lossBowTime(ItemStack stack);
}
