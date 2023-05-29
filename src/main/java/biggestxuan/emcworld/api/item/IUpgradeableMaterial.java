package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.api.item.IUpgradeableItem;
import net.minecraft.item.ItemStack;

public interface IUpgradeableMaterial {
    default int getWeight(ItemStack stack){
        if(stack.getItem() instanceof IUpgradeableItem) {
            IUpgradeableItem item = (IUpgradeableItem) stack.getItem();
            int l = item.getLevel(stack);
            int weight = 10;
            for (int i = 0; i < l; i++) {
                weight = (int) (1.3f * weight);
            }
            return weight;
        }
        return 0;
    }

    default double breakWeaponRate(){
        return 0;
    };
}
