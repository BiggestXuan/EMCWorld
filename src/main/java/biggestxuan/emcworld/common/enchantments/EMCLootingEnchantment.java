package biggestxuan.emcworld.common.enchantments;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.ICostEMCItem;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/08/01
 */

@EMCWorldSince("1.0.3")
public class EMCLootingEnchantment extends BaseEnchantment{
    public EMCLootingEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentType.BREAKABLE,new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}
