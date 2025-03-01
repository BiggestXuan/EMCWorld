package biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/09
 */

import biggestxuan.emcworld.api.item.equipment.dagger.IDaggerTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public enum DaggerTier implements IDaggerTier {
    WOODEN(1.02,52,-2.8F,2.5f,0,1),
    STONE(1.05,104,-2.6F,3.25f,1,3),
    IRON(1.07,256,-2.3F,5f,2,8),
    GOLDEN(1.2,32,-1.5F,2f,0,20),
    DIAMOND(1.13,1541,-2.1F,6.75f,3,12),
    NETHERITE(1.25,2496,-1.6F,20.5f,4,16),
    GOD(1.05,2048,-2F,6f,4,15),
    GAIA(1.16,2200,-1.8F,10.5f,4,13),
    RAINBOW(1.2,2659,-1.6F,12.5F,4,15);
    ;

    private final double speed;
    private final int uses;
    private final float attackSpeed;
    private final float damage;
    private final int level;
    private final int ench;

    DaggerTier(double speed,int uses,float attackSpeed,float damage,int level,int ench){
        this.speed = speed;
        this.uses = uses;
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.level = level;
        this.ench = ench;
    }

    @Override
    public double getAttackSpeed(ItemStack stack) {
        return speed;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return attackSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return ench;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}
