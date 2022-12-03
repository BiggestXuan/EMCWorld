package biggestxuan.emcworld.api.item;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/03
 */

import net.minecraft.item.ItemStack;

public interface IEMCInfuserItem {
    long getMaxInfuser(ItemStack stack);

    default double costRate(ItemStack stack){
        return 0.005;
    }

    default long getInfuser(ItemStack stack){
        return stack.getOrCreateTag().getLong("infuser");
    }

    default void setInfuser(ItemStack stack,long value){
        stack.getOrCreateTag().putLong("infuser",Math.min(getMaxInfuser(stack),value));
    }

    default void addInfuser(ItemStack stack,long value){
        setInfuser(stack,getInfuser(stack)+value);
    }

    default double getInfuserRate(ItemStack stack){
        return 1.0d * getInfuser(stack) / getMaxInfuser(stack);
    }

    default long getCostEMC(ItemStack stack){
        return (long) (getInfuser(stack) * costRate(stack));
    }

    default void cost(ItemStack stack){
        addInfuser(stack,Math.negateExact(getCostEMC(stack)));
    }
}
