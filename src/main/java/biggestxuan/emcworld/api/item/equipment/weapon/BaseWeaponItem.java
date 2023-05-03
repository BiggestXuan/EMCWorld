package biggestxuan.emcworld.api.item.equipment.weapon;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/26
 */

import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;

import javax.annotation.Nonnull;

public abstract class BaseWeaponItem extends SwordItem{
    public BaseWeaponItem(IItemTier tier,int damage,float damageSpeed){
        super(tier,damage,damageSpeed,new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB));
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

    @Nonnull
    @Override
    public Rarity getRarity(ItemStack stack){
        return Rarity.UNCOMMON;
    }
}
