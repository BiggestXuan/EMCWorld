package biggestxuan.emcworld.common.items.Curios;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/31
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IDisableAnvilItem;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.IStarItem;
import biggestxuan.emcworld.api.item.equipment.armor.IEMCShieldArmor;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.EWItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;

public class EMCShieldSupply extends EWItem implements IUpgradeableItem, IEMCShieldArmor, ICurioItem, IPrefixItem, IDisableAnvilItem, IStarItem {
    public EMCShieldSupply(){
        super(new Properties().tab(EWCreativeTabs.EW_EQUIPMENT_TAB).stacksTo(1));
    }

    @Override
    public int getMaxLevel() {
        return (int) (8 * ConfigManager.DIFFICULTY.get() / 3);
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
                return 10000;
            case 5:
                return 25000;
            case 6:
                return 40000;
            case 7:
                return 60000;
        }
        return EMCWorld.HOMO;
    }

    @Override
    public double getEMCCostRate(){
        return 0;
    }

    @Override
    public long getMaxInfuser(ItemStack stack) {
        return (long) (1000000 * Math.pow(3,getLevel(stack)) * getPrefixCommonRate(stack) * getBuffer(stack));
    }

    @Override
    public float maxShield(ItemStack stack) {
        return 30f * (float) (Math.pow(1.5,getLevel(stack)) * getPrefixCommonRate(stack) * getBuffer(stack));
    }

    @Override
    public float shieldSpeed(ItemStack stack) {
        return 4f * (float) (Math.pow(1.45,getLevel(stack)) * getPrefixCommonRate(stack) * getBuffer(stack));
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

    private double getPrefixCommonRate(ItemStack stack){
        double b;
        Prefix prefix = getPrefix(stack);
        if(prefix == null) return 1;
        if(prefix.getLevel() <= 3){
            b = 1 - (4 - prefix.getLevel()) * 0.1;
        }else{
            b = (prefix.getLevel()-4) * 0.05 + 1;
        }
        return b;
    }
}
