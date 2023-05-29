package biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/28
 */

import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import net.minecraft.item.ItemStack;

public class NatureSword extends BaseEMCGodSword {
    public NatureSword() {
        super(6.25f,0x2a8000);
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        int level = this.getLevel(stack);
        return (float) ((Math.pow(1.135f,level)*baseDamage)-baseDamage);
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
        return Math.pow(0.91f,level);
    }

    @Override
    public long getBaseModifySecond(ItemStack stack) {
        int level = this.getLevel(stack);
        if(level <= 5) return 0;
        return Math.round(Math.pow(1.2,level-5)*8);
    }

    @Override
    public double getBaseRange(ItemStack stack){
        int level = getLevel(stack);
        return Math.pow(1.07,level);
    }
}
