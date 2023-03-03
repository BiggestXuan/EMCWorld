package biggestxuan.emcworld.common.items.Equipment.Weapon.Sword;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/01/18
 */

import biggestxuan.emcworld.api.item.equipment.weapon.BaseWeaponItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Tier.EWSwordTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

import javax.annotation.Nonnull;

public class GaiaSword extends BaseWeaponItem {
    public GaiaSword() {
        super(EWSwordTier.GAIA,0,-2.4F);
    }

    @Nonnull
    @Override
    public Rarity getRarity(ItemStack stack){
        return Rarity.COMMON;
    }
}
