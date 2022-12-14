package biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/09
 */

import biggestxuan.emcworld.api.item.equipment.dagger.IDaggerTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public enum DaggerTier implements IDaggerTier {
    WOODEN(1.02,52,-2.4F,1.75f,0,1),
    STONE(1.05,104,-2F,2.75f,1,3),
    IRON(1.07,256,-1.8F,3.75f,2,8),
    GOLDEN(1.2,32,-0.8F,1.5f,0,20),
    DIAMOND(1.13,1541,-1.6F,4.25f,3,12),
    NETHERITE(1.15,2496,-1.4F,5.5f,4,16),
    GOD(1.05,2048,-2F,3.75f,4,15)
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
