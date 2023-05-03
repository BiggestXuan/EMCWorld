package biggestxuan.emcworld.common.items.Equipment.Weapon.Gun;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/21
 */

import biggestxuan.emcworld.api.item.equipment.gun.IGunTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public enum GunTier implements IGunTier {
    WOODEN(64,5.2,0,35,0.1,3.6,0.02,"wooden"),
    STONE(128,6.6,1,28,0.2,3.3,0.04,"stone"),
    IRON(256,8.1,2,22,0.4,2.9,0.08,"iron"),
    GOLDEN(48,3.7,0,4,0.7,0.4,0.14,"golden"),
    DIAMOND(2048,9.3,3,16,0.6,2.5,0.18,"diamond"),
    GAIA(3012,11.4,3,14,0.7,2.3,0.26,"gaia"),
    RAINBOW(3547,12.8,3,13,0.75,2.2,0.29,"rainbow"),
    NETHERITE(4096,16.2,4,9,0.8,1.7,0.4,"netherite")
    ;

    private final int use;
    private final float damage;
    private final int level;
    private final int cd;
    private final double accuracy;
    private final double backlash;
    private final double penetrate;
    private final String name;

    GunTier(int use,double damage,int level,int cd,double accuracy,double backlash,double penetrate,String name){
        this.use = use;
        this.damage = (float) damage;
        this.level = level;
        this.cd = cd;
        this.accuracy = accuracy;
        this.backlash = backlash;
        this.name = name;
        this.penetrate = penetrate;
    }

    @Override
    public double getPenetrate(){
        return penetrate;
    }

    @Override
    public String getName(){
        return name;
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
    public double backlash(ItemStack stack) {
        return backlash;
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
