package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/28
 */

import biggestxuan.emcworld.api.item.equipment.IStarItem;
import net.minecraft.item.ItemStack;

public interface ISecondEMCItem {
    long EMCModifySecond(ItemStack stack);

    default long getActEMC(ItemStack stack){
        double emc = EMCModifySecond(stack);
        if(stack.getItem() instanceof IUpgradeableItem){
            IUpgradeableItem item = (IUpgradeableItem) stack.getItem();
            emc *= 1D + (item.getLevel(stack) * 0.02);
        }
        if(stack.getItem() instanceof IStarItem){
            IStarItem item = (IStarItem) stack.getItem();
            emc *= Math.pow(1.75,item.getStar(stack));
        }
        return (long) emc;
    }
}
