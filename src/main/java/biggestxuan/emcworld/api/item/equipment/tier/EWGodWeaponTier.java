package biggestxuan.emcworld.api.item.equipment.tier;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/30
 */

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public class EWGodWeaponTier implements IItemTier {
    public static final EWGodWeaponTier INSTANCE = new EWGodWeaponTier();

    @Override
    public int getUses() {
        return 8888;
    }

    @Override
    public float getSpeed() {
        return 1.0f;
    }

    @Override
    public float getAttackDamageBonus() {
        return 1.0f;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Nonnull
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}
