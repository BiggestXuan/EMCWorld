package biggestxuan.emcworld.common.items.Curios;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/31
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.armor.IEMCShieldArmor;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;

public class EMCShieldSupply extends EWItem implements IUpgradeableItem, IEMCShieldArmor, ICurioItem {
    public EMCShieldSupply(){
        super(new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB).stacksTo(1));
    }

    @Nonnull
    public ITextComponent getName(@Nonnull ItemStack p_200295_1_) {
        String star = " "+"\u2605".repeat(Math.max(0, getLevel(p_200295_1_)));
        return EMCWorld.tc("item.emcworld.emc_shield_supply").append(star);
    }

    @Override
    public int getMaxLevel() {
        return 8;
    }

    @Override
    public int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        switch (l){
            case 0:
                return 300;
            case 1:
                return 750;
            case 2:
                return 2000;
            case 3:
                return 4500;
            case 4:
                return 8500;
            case 5:
                return 12000;
            case 6:
                return 20000;
            case 7:
                return 30000;
        }
        return 114514;
    }

    @Override
    public double getEMCCostRate(){
        return 0;
    }

    @Override
    public long getTickCost(ItemStack stack) {
        return 0;
    }

    @Override
    public long EMCModifySecond(ItemStack stack) {
        return 0;
    }

    @Override
    public long getMaxInfuser(ItemStack stack) {
        return (long) (1000000 * Math.pow(3,getLevel(stack)));
    }

    @Override
    public float maxShield(ItemStack stack) {
        return 30f * (float) (Math.pow(1.5,getLevel(stack)));
    }

    @Override
    public float shieldSpeed(ItemStack stack) {
        return 4f * (float) (Math.pow(1.45,getLevel(stack)));
    }

    @Nonnull
    @Override
    public Rarity getRarity(@Nonnull ItemStack stack){
        int l = getLevel(stack);
        if(l <= 2) return Rarity.COMMON;
        if(l <= 4) return Rarity.UNCOMMON;
        if(l <= 6) return Rarity.RARE;
        return Rarity.EPIC;
    }
}
