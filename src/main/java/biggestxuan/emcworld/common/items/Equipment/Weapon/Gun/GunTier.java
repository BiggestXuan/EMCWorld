package biggestxuan.emcworld.common.items.Equipment.Weapon.Gun;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/21
 */

import biggestxuan.emcworld.api.item.equipment.gun.IGunTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public enum GunTier implements IGunTier {
    WOODEN(64,5.2,0,35,0.1),
    STONE(128,6.6,1,28,0.2),
    IRON(256,8.1,2,22,0.4),
    GOLDEN(48,3.7,0,4,0.7),
    DIAMOND(2048,9.3,3,16,0.6),
    GAIA(3012,11.4,3,14,0.7),
    RAINBOW(3547,12.8,3,13,0.75),
    NETHERITE(4096,16.2,4,9,0.8)
    ;

    private final int use;
    private final float damage;
    private final int level;
    private final int cd;
    private final double accuracy;

    GunTier(int use,double damage,int level,int cd,double accuracy){
        this.use = use;
        this.damage = (float) damage;
        this.level = level;
        this.cd = cd;
        this.accuracy = accuracy;
    }

    @Override
    public int cd(ItemStack stack) {
        return cd;
    }

    @Override
    public double accuracy(ItemStack stack) {
        return accuracy;
    }

    @Override
    public int getUses() {
        return use;
    }

    @Override
    public float getSpeed() {
        return 1.25F;
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
        return 18;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}
