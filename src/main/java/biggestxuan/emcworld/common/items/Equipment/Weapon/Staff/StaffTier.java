package biggestxuan.emcworld.common.items.Equipment.Weapon.Staff;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/22
 */

import biggestxuan.emcworld.api.item.equipment.staff.IStaffTier;
import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public enum StaffTier implements IStaffTier {
    WOODEN(50,-3,6.5,0.05,1.2,0x796653,2, Items.OAK_PLANKS),
    STONE(120,-2.8,7.5,0.1,1.25,0x818181,2.5,Items.STONE),
    IRON(256,-2.4,8.5,0.15,1.3,0xc0c0c0,4,Items.IRON_INGOT),
    GOLDEN(64,-1.4,4,0.5,1.15,0xdbaf2d,8,Items.GOLD_INGOT),
    DIAMOND(2048,-2.1,11.25,0.2,1.45,0x74c2b2,5.5,Items.DIAMOND),
    NETHERITE(4096,-1.8,14.75,0.25,1.6,0x71615b,7,Items.NETHERITE_INGOT),
    GOD(10000,-2,8.5,0.2,1.3,0x0,6, EWItems.BIGGEST_EMC_GEM.get())
    ;

    private final int uses;
    private final double speed;
    private final double damage;
    private final double criticalChance;
    private final double criticalRate;
    private final int color;
    private final double burstSpeed;
    private final Item repair;

    StaffTier(int uses,double speed,double damage,double criticalChance,double criticalRate,int color,double burstSpeed,Item repair){
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.criticalChance = criticalChance;
        this.criticalRate = criticalRate;
        this.color = color;
        this.burstSpeed = burstSpeed;
        this.repair = repair;
    }

    @Override
    public double getCriticalRate() {
        return criticalRate;
    }

    @Override
    public double getCriticalChance() {
        return criticalChance;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public double getBurstSpeed() {
        return burstSpeed;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return (float) speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return (float) damage;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }

    @Nonnull
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(new ItemStack(repair,1));
    }
}
