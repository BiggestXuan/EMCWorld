package biggestxuan.emcworld.api.item;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/24
 */

import net.minecraft.item.ItemStack;

public interface IUpgradeableItem extends ICostEMCItem, ISecondEMCItem, IEMCRepairableItem {
    int getMaxLevel();

    int getLevel(ItemStack stack);

    void setLevel(ItemStack stack, int level);

    void addLevel(ItemStack stack, int level);

    void lossLevel(ItemStack stack, int level);

    default int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        int weight = 10;
        for (int i = 0; i < l; i++) {
            weight = (int) (1.42f * weight);
        }
        return weight;
    }

    @Override
    default double getEMCCostRate() {
        return 0d;
    }
}
