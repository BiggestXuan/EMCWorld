package biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/28
 */

import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodWeapon;
import net.minecraft.item.ItemStack;

public class IceSword extends BaseEMCGodWeapon {
    public IceSword() {
        super(7.5f,"ice_sword",0x244bb9);
    }

    @Override
    public float getAdditionsDamage(ItemStack stack) {
        int level = this.getLevel(stack);
        return (float) ((Math.pow(1.15f,level)*baseDamage)-baseDamage);
    }

    @Override
    public int getEnchantmentValue() {
        return 20;
    }

    @Override
    public double costEMCWhenAttack(ItemStack stack) {
        int level = this.getLevel(stack);
        return Math.pow(0.97f,level);
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        int level = this.getLevel(stack);
        if(level <= 5) return 0;
        return Math.round(Math.pow(1.7,level-5)*8);
    }

    @Override
    public double getAttackRange(ItemStack stack){
        int level = getLevel(stack);
        if(level <= 11) return 0d;
        if(level <= 15) return (level-10) * 0.25d;
        if(level <= 18) return (level-15) * 0.3d + 1.0d;
        if(level <= 20) return (level-18) * 0.35d + 1.6d;
        if(level <= 23) return (level-20) * 0.4d + 2.8d;
        else return 4.0d + 0.5d;
    }
}
