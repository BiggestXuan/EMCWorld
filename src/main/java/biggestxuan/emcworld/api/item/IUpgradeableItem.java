package biggestxuan.emcworld.api.item;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/24
 */

import net.minecraft.item.ItemStack;

public interface IUpgradeableItem {
    int getMaxLevel();

    default int getLevel(ItemStack stack){
        return stack.getOrCreateTag().getInt("level");
    };

    default void setLevel(ItemStack stack, int level){
        stack.getOrCreateTag().putInt("level",level);
    };

    default void addLevel(ItemStack stack, int level){
        setLevel(stack,Math.min(getMaxLevel(),getLevel(stack)+level));
    };

    default void lossLevel(ItemStack stack, int level){
        setLevel(stack,Math.max(0,getLevel(stack)-level));
    }

    default int getWeightRequired(ItemStack stack){
        int l = getLevel(stack);
        int weight = 10;
        for (int i = 0; i < l; i++) {
            weight = (int) (1.42f * weight);
        }
        return weight;
    }
}
