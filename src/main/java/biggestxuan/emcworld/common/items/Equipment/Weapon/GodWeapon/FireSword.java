package biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/28
 */

import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodWeapon;
import net.minecraft.item.ItemStack;

public class FireSword extends BaseEMCGodWeapon {
    public FireSword() {
        super(8.75f,0xc90c0c);
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        int level = this.getLevel(stack);
        return (float) ((Math.pow(1.17f,level)*baseDamage)-baseDamage);
    }

    @Override
    public int getEnchantmentValue() {
        return 23;
    }

    @Override
    public double getBaseEMCWhenAttack(ItemStack stack) {
        int level = this.getLevel(stack);
        return Math.pow(1.065f,level);
    }

    @Override
    public long getBaseModifySecond(ItemStack stack) {
        int level = this.getLevel(stack);
        if(level <= 5) return 0;
        return Math.round(Math.pow(1.75,level-5)*8);
    }

    @Override
    public double getBaseCriticalChance(ItemStack stack) {
        return Math.pow(1.022,getLevel(stack)) - 1;
    }

    @Override
    public double getBaseCriticalRate(ItemStack stack) {
        return Math.pow(1.027,getLevel(stack));
    }

    @Override
    public double getBaseRange(ItemStack stack){
        int level = getLevel(stack);
        if(level <= 10) return 0d;
        if(level <= 15) return (level-10) * 0.3d;
        if(level <= 18) return (level-15) * 0.35d + 1.5d;
        if(level <= 20) return (level-18) * 0.4d + 2.55d;
        if(level <= 23) return (level-20) * 0.45d + 3.35d;
        else return 4.7d + 0.95d;
    }
}
