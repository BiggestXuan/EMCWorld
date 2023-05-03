package biggestxuan.emcworld.common.enchantments;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/24
 */

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public abstract class BaseEnchantment extends Enchantment {
    protected BaseEnchantment(Rarity p_i46731_1_, EnchantmentType p_i46731_2_, EquipmentSlotType[] p_i46731_3_) {
        super(p_i46731_1_, p_i46731_2_, p_i46731_3_);
    }
}
