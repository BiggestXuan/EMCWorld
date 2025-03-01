package biggestxuan.emcworld.common.items.Equipment.Weapon;

import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.api.item.IEMCRepairableItem;
import biggestxuan.emcworld.api.item.INeedLevelItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import net.minecraft.item.ItemStack;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/28
 */
public interface IRainbowEquipment extends IEMCInfuserItem, INeedLevelItem , IEMCRepairableItem, IUpgradeableItem {
    @Override
    default int getMaxLevel(){
        return 12;
    }

    @Override
    default int getUseLevel(ItemStack stack) {
        return 20;
    }

    @Override
    default long getMaxInfuser(ItemStack stack) {
        return 3000000;
    }

    default long getTickCost(ItemStack stack){
        return 420;
    };

    default double getRainbowCriticalRate(ItemStack stack) {
        return (getInfuser(stack) >= Math.E ? Math.log(getInfuser(stack))/40d : 0);
    }

    default double getRainbowCriticalChance(ItemStack stack) {
        return (getInfuser(stack) >= Math.E ? Math.log(getInfuser(stack))/45d : 0);
    }

    default float getRainbowDamage(ItemStack stack,float base){
        return getInfuser(stack) >= Math.E ? base + (float) (Math.log(getInfuser(stack)*0.65) / 1.5) : base;
    }

}
