package biggestxuan.emcworld.common.items.Equipment.Weapon.Sword;


import biggestxuan.emcworld.api.item.equipment.weapon.BaseWeaponItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.IRainbowEquipment;
import biggestxuan.emcworld.common.items.Equipment.Weapon.Tier.EWSwordTier;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/28
 */
public class RainbowSword extends BaseWeaponItem implements IRainbowEquipment {
    public RainbowSword() {
        super(EWSwordTier.RAINBOW,0,-2.2F);
    }
}
