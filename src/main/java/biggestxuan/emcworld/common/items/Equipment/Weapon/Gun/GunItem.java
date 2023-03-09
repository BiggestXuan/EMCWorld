package biggestxuan.emcworld.common.items.Equipment.Weapon.Gun;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/04
 */

import biggestxuan.emcworld.api.item.equipment.gun.IGunTier;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.item.TieredItem;

public class GunItem extends TieredItem {
    public GunItem(IGunTier tier) {
        super(tier,new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB));
    }
}
