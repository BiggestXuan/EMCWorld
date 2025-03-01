package biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer;

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
public class RainbowWarhammer extends WarHammerItem implements IRainbowEquipment {
    public RainbowWarhammer() {
        super(WarHammerTier.RAINBOW);
    }

    @Override
    public DamageUtils getAdditionsDamage(PlayerEntity player, ItemStack stack) {
        return DamageUtils.of(getRainbowDamage(stack,(float) super.getAdditionsDamage(player,stack).total()));
    }

    @Override
    public double getCriticalChance(ItemStack stack) {
        return super.getCriticalChance(stack) + getRainbowCriticalChance(stack);
    }

    @Override
    public double getCriticalRate(ItemStack stack) {
        return super.getCriticalRate(stack) + getRainbowCriticalRate(stack);
    }
}
