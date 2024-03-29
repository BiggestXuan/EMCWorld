package biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/28
 */

import biggestxuan.emcworld.api.item.ISponsorItem;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import biggestxuan.emcworld.common.utils.Sponsors.Sponsors;
import net.minecraft.item.ItemStack;

public class CharaSword extends BaseEMCGodSword implements ISponsorItem {
    public CharaSword() {
        super(8,0x44ccaa);
    }

    @Override
    public long getBaseModifySecond(ItemStack stack) {
        return (long) (555.5 * Math.pow(1.14514 * Math.min(22,getLevel(stack)), Math.pow(1.14514, 1.14514)));
    }

    @Override
    public double getBaseCriticalChance(ItemStack stack) {
        return 0.03 * getLevel(stack);
    }

    @Override
    public double getBaseCriticalRate(ItemStack stack) {
        return 0.07 * getLevel(stack) + 1;
    }

    @Override
    public float getBaseDamage(ItemStack stack) {
        return (float) (Math.sqrt(getLevel(stack))*11.4514);
    }

    @Override
    public double getBaseRange(ItemStack stack) {
        return 0.06*getLevel(stack);
    }

    @Override
    public int getEnchantmentValue() {
        return 25;
    }

    @Override
    public double getBaseEMCWhenAttack(ItemStack stack) {
        return Math.sqrt(getLevel(stack));
    }

    @Override
    public Sponsors getSponsor(){
        return Sponsors.all.Chara_SS.getSponsors();
    }
}
