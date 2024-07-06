package biggestxuan.emcworld.api.item.equipment.armor;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/24
 */

import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

import javax.annotation.Nonnull;

public abstract class BaseArmorItem extends ArmorItem {
    public BaseArmorItem(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }
    public BaseArmorItem(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_) {
        super(p_i48534_1_, p_i48534_2_, new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB));
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

    @Nonnull
    @Override
    public Rarity getRarity(@Nonnull ItemStack stack){
        return Rarity.UNCOMMON;
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_82789_1_, ItemStack p_82789_2_) {
        return false;
    }
}
