package biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/04
 */

import biggestxuan.emcworld.api.item.equipment.warhammer.IWarHammerTier;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public enum WarHammerTier implements IWarHammerTier {
    WOODEN(6.15,120,0,1,1.2,1.1,1.03,0.8),
    STONE(7,256,1,4,1.5,1.2,1.05,0.7),
    IRON(8.25,512,2,8,1.9,1.3,1.1,0.63),
    GOLDEN(3.75,50,0,30,0.4,1.05,1.3,1),
    DIAMOND(11.45,1024,3,13,2.7,1.4,1.15,0.5),
    NETHERITE(22,2048,4,18,6,1.6,1.2,0.4),
    GOD(10.75,4096,4,20,2.25,1.45,1.125,0.5),
    GAIA(16.5,1600,4,15,3.5,1.5,1.18,0.45)
    ;

    private final double damage;
    private final int maxUse;
    private final int level;
    private final int enchant;
    private final double range;
    private final double cr;
    private final double cc;
    private final double ls;

    WarHammerTier(double damage,int maxUse,int level,int enchant,double range,double cr,double cc,double ls){
        this.damage = damage;
        this.level = level;
        this.enchant = enchant;
        this.maxUse = maxUse;
        this.range = range;
        this.cr = cr;
        this.cc = cc;
        this.ls = ls;
    }

    @Override
    public double getCriticalRate() {
        return cr;
    }

    @Override
    public double getCriticalChance() {
        return cc-1;
    }

    @Override
    public double getAttackRange() {
        return range;
    }

    @Override
    public double lossSpeed() {
        return ls;
    }

    @Override
    public int getUses() {
        return maxUse;
    }

    @Override
    public float getSpeed() {
        return 0.1f;
    }

    @Override
    public float getAttackDamageBonus() {
        return (float) damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchant;
    }

    @Nonnull
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}
