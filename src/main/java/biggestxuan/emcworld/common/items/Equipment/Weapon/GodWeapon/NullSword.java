package biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/01
 */

import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodWeapon;
import net.minecraft.item.ItemStack;

public class NullSword extends BaseEMCGodWeapon {
    public NullSword() {
        super(5f,"null_sword",0x323531);
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        int level = getLevel(stack);
        if(level <5){
            return (int) Math.pow(1.125,level);
        }
        return (int) (Math.pow(2,level)*Math.pow(0.75,level-5));
    }

    @Override
    public float getAdditionsDamage(ItemStack stack) {
        int level = getLevel(stack);
        double d = (level*Math.PI/180);
        return (float) ((Math.pow(1.2,level)-Math.tan(d))*baseDamage);
    }

    @Override
    public double getAttackRange(ItemStack stack) {
        return 1.5D;
    }

    @Override
    public int getEnchantmentValue() {
        return 20;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
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
        return (long) (baseDamage + getAdditionsDamage(stack));
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
