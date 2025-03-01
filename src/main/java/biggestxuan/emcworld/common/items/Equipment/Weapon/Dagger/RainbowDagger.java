package biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger;

import biggestxuan.emcworld.api.item.equipment.dagger.IDaggerTier;
import biggestxuan.emcworld.common.items.Equipment.Weapon.IRainbowEquipment;
import biggestxuan.emcworld.common.utils.DamageUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/28
 */
public class RainbowDagger extends DaggerItem implements IRainbowEquipment {
    public RainbowDagger() {
        super(DaggerTier.RAINBOW);
    }

    @Override
    public DamageUtils getAdditionsDamage(PlayerEntity player, ItemStack stack) {
        return DamageUtils.of(getRainbowDamage(stack,(float) super.getAdditionsDamage(player,stack).total()));
    }

    @Override
    public double getSuckerRate(ItemStack stack) {
        return super.getSuckerRate(stack) + getRainbowCriticalChance(stack) / 2;
    }
}
