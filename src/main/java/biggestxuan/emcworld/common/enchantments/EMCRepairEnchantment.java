package biggestxuan.emcworld.common.enchantments;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.IEMCRepairableItem;
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
public class EMCRepairEnchantment extends BaseEnchantment{
    public EMCRepairEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentType.BREAKABLE,EquipmentSlotType.values());
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean canEnchant(@Nonnull ItemStack p_92089_1_) {
        return !(p_92089_1_.getItem() instanceof IEMCRepairableItem) && p_92089_1_.getItem().canBeDepleted();
    }
}
