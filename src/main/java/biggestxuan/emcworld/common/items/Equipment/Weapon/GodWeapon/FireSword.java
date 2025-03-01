package biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/28
 */

import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.item.ItemStack;

public class FireSword extends BaseEMCGodSword {
    public FireSword() {
        super(8.75f,0xc90c0c);
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        float base = (float) ((Math.pow(1.156f,Math.min(22,getLevel(stack)))*baseDamage));
        return getLevel(stack) >= 22 ? (float) MathUtils.getGodWeaponAddition(stack, base) : base;
    }

    @Override
    public int getEnchantmentValue() {
        return 23;
    }

    @Override
    public double getBaseEMCWhenAttack(ItemStack stack) {
        int level = this.getLevel(stack);
        return Math.pow(1.04f,level);
    }

    @Override
    public long getBaseModifySecond(ItemStack stack) {
        int level = this.getLevel(stack);
        if(level <= 5) return 0;
        return Math.round(Math.pow(1.4,level-5)*8);
    }

    @Override
    public double getBaseCriticalChance(ItemStack stack) {
        return Math.pow(1.011,getLevel(stack)) - 1;
    }

    @Override
    public double getBaseCriticalRate(ItemStack stack) {
        return Math.pow(1.027,getLevel(stack));
    }

    @Override
    public double getBaseRange(ItemStack stack){
        int level = getLevel(stack);
        return Math.pow(1.09,level);
    }
}
