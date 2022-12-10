package biggestxuan.emcworld.api.item.equipment;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/10
 */

import biggestxuan.emcworld.api.item.INeedLevelItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import net.minecraft.item.ItemStack;

public interface IEMCGodWeaponLevel extends INeedLevelItem {
    @Override
    default int getUseLevel(ItemStack stack){
        if(stack.getItem() instanceof IUpgradeableItem){
            IUpgradeableItem item = (IUpgradeableItem) stack.getItem();
            int weaponLevel = item.getLevel(stack);
            if(weaponLevel >= 22) return 100;
            if(weaponLevel >= 20) return 91;
            if(weaponLevel >= 18) return 81;
            if(weaponLevel >= 16) return 71;
            if(weaponLevel >= 14) return 61;
            if(weaponLevel >= 12) return 51;
            if(weaponLevel >= 10) return 41;
            return 31;
        }
        return 0;
    }
}
