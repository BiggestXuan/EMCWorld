package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.enchantments.EMCGiantKillerEnchantment;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/01/17
 */

@EMCWorldSince("1.1.0")
@Mixin(DamageEnchantment.class)
public abstract class DamageEnchantmentMixin extends Enchantment {
    protected DamageEnchantmentMixin(Rarity p_i46731_1_, EnchantmentType p_i46731_2_, EquipmentSlotType[] p_i46731_3_) {
        super(p_i46731_1_, p_i46731_2_, p_i46731_3_);
    }

    /**
     * @author Biggest_Xuan
     * @reason No
     */
    @Override
    @Overwrite
    public boolean checkCompatibility(Enchantment p_77326_1_) {
        return !(p_77326_1_ instanceof DamageEnchantment) && !(p_77326_1_ instanceof EMCGiantKillerEnchantment);
    }
}
