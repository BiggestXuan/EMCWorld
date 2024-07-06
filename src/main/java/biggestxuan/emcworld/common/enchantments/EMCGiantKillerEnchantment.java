package biggestxuan.emcworld.common.enchantments;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/01/17
 */

@EMCWorldSince("1.1.0")
public class EMCGiantKillerEnchantment extends BaseEnchantment{
    public EMCGiantKillerEnchantment() {
        super(Rarity.VERY_RARE,EnchantmentType.WEAPON,new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean checkCompatibility(Enchantment p_77326_1_) {
        return !(p_77326_1_ instanceof DamageEnchantment);
    }
}
