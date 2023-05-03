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
    WOODEN(7.25,120,0,1,1.2,1.25,1.1,0.8),
    STONE(8.5,256,1,4,1.5,1.35,1.2,0.7),
    IRON(10.5,512,2,8,1.9,1.45,1.3,0.63),
    GOLDEN(5,50,0,30,0.4,1.05,1.9,1),
    DIAMOND(15.5,1024,3,13,2.7,1.5,1.35,0.5),
    NETHERITE(28,2048,4,18,6,1.75,1.5,0.4),
    GOD(11.25,4096,4,20,2.25,1.5,1.25,0.5),
    GAIA(19.5,1600,4,15,4,1.6,1.4,0.45)
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
