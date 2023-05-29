package biggestxuan.emcworld.api.item;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/03
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;

public interface IEMCInfuserItem extends IForgeItem ,IEMCRepairableItem{

    default long getTickCost(ItemStack stack){
        return (long) (120 * ConfigManager.DIFFICULTY.get());
    }

    long getMaxInfuser(ItemStack stack);

    default double costRate(ItemStack stack){
        return 0.005;
    }

    default long getInfuser(ItemStack stack){
        return stack.getOrCreateTag().getLong("infuser");
    }

    default void setInfuser(ItemStack stack,long value){
        if(value > getMaxInfuser(stack)){
            value = getMaxInfuser(stack);
        }
        if(value < 0){
            value = 0;
        }
        stack.getOrCreateTag().putLong("infuser",value);
    }

    default long getUse(ItemStack stack){
        return getMaxInfuser(stack) - getInfuser(stack);
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

    @Override
    default boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    default double getDurabilityForDisplay(ItemStack stack) {
        return 1 - 1d * getInfuser(stack) / getMaxInfuser(stack);
    }

    @Override
    default int getRGBDurabilityForDisplay(ItemStack stack) {
        return 0xD7C8F3;
    }
}
