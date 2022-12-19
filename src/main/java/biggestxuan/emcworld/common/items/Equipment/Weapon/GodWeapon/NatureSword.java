package biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/28
 */

import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodWeapon;
import net.minecraft.item.ItemStack;

public class NatureSword extends BaseEMCGodWeapon {
    public NatureSword() {
        super(5f,0x2a8000);
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        int level = this.getLevel(stack);
        return (float) ((Math.pow(1.13f,level)*baseDamage)-baseDamage);
    }

    @Override
    public double getBaseCriticalChance(ItemStack stack) {
        return Math.pow(1.01,getLevel(stack)) - 1;
    }

    @Override
    public double getBaseCriticalRate(ItemStack stack) {
        return Math.pow(1.014,getLevel(stack));
    }

    @Override
    public int getEnchantmentValue() {
        return 17;
    }

    @Override
    public double getBaseEMCWhenAttack(ItemStack stack) {
        int level = this.getLevel(stack);
        return Math.pow(0.9f,level);
    }

    @Override
    public long getBaseModifySecond(ItemStack stack) {
        int level = this.getLevel(stack);
        if(level <= 5) return 0;
        return Math.round(Math.pow(1.65,level-5)*8);
    }

    @Override
    public double getBaseRange(ItemStack stack){
        int level = getLevel(stack);
        if(level <= 12) return 0d;
        if(level <= 15) return (level-10) * 0.2d;
        if(level <= 18) return (level-15) * 0.25d + 0.6d;
        if(level <= 20) return (level-18) * 0.3d + 1.35d;
        if(level <= 23) return (level-20) * 0.35d + 1.95d;
        else return 1.95d + 0.5d;
    }
}
