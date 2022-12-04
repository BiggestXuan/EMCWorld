package biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/04
 */

import biggestxuan.emcworld.api.item.equipment.warhammer.IWarHammerTier;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public enum WarHammerTier implements IWarHammerTier {
    WOODEN(6,120,0,1,0.6,1.25,1.05,0.8),
    STONE(7.5,256,1,4,0.9,1.35,1.1,0.7),
    IRON(8.5,512,2,8,1.3,1.45,1.2,0.63),
    GOLDEN(4,50,0,30,0.2,1.05,1.7,1),
    DIAMOND(10,1024,3,13,1.8,1.5,1.25,0.5),
    NETHERITE(12,2048,4,18,2.5,1.65,1.35,0.4),
    GOD(9.5,4096,4,20,1,2,1.2,0.5)
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
        return cc;
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
