package biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/01
 */

import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import net.minecraft.item.ItemStack;

public class NullSword extends BaseEMCGodSword {
    public NullSword() {
        super(5f,0x323531);
    }

    @Override
    public long getBaseModifySecond(ItemStack stack) {
        int level = getLevel(stack);
        if(level <5){
            return (int) Math.pow(1.125,level);
        }
        return (int) (Math.pow(2,level)*Math.pow(0.75,level-5));
    }

    @Override
    public double getBaseCriticalChance(ItemStack stack) {
        return Math.pow(1.013,getLevel(stack)) - 1;
    }

    @Override
    public double getBaseCriticalRate(ItemStack stack) {
        return Math.pow(1.013,getLevel(stack));
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        int level = getLevel(stack);
        double d = (level*Math.PI/180);
        return (float) ((Math.pow(1.13,level)-Math.tan(d))*baseDamage);
    }

    @Override
    public double getBaseRange(ItemStack stack) {
        return 1.5D;
    }

    @Override
    public int getEnchantmentValue() {
        return 20;
    }

    @Override
    public double getBaseEMCWhenAttack(ItemStack stack) {
        int level = getLevel(stack);
        if(level >5){
            return Math.pow(1.03,level);
        }
        return 0.3 * level;
    }

    @Override
    public long getTickCost(ItemStack stack){
        int level = getLevel(stack);
        if(level <10){
            return Math.round(Math.pow(1.5,level));
        }
        return (long) (baseDamage * level);
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        int weight = 15;
        for (int i = 0; i < l; i++) {
            weight = (int) (1.35f * weight);
        }
        return weight;
    }
}
