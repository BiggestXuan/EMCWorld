package biggestxuan.emcworld.api.item.equipment;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/10
 */

import biggestxuan.emcworld.api.item.INeedLevelItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import net.minecraft.item.ItemStack;

public interface IEMCGodWeaponLevel extends INeedLevelItem,IStarItem {
    @Override
    default int getUseLevel(ItemStack stack){
        if(stack.getItem() instanceof IUpgradeableItem){
            IUpgradeableItem item = (IUpgradeableItem) stack.getItem();
            int weaponLevel = item.getLevel(stack);
            if(weaponLevel >= 30) return 100;
            if(weaponLevel >= 28) return 90;
            if(weaponLevel >= 27) return 80;
            if(weaponLevel >= 26) return 70;
            if(weaponLevel >= 24) return 60;
            if(weaponLevel >= 22) return 50;
            if(weaponLevel >= 20) return 40;
            return 20;
        }
        return 0;
    }
}
