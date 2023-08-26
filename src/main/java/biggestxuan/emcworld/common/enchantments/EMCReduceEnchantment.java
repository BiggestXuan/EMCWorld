package biggestxuan.emcworld.common.enchantments;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.ICostEMCItem;
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
public class EMCReduceEnchantment extends BaseEnchantment{
    public EMCReduceEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentType.BREAKABLE,new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean canEnchant(@Nonnull ItemStack p_92089_1_) {
        return p_92089_1_.getItem() instanceof ICostEMCItem && p_92089_1_.getItem().canBeDepleted();
    }

    public boolean isTreasureOnly() {
        return true;
    }
}
