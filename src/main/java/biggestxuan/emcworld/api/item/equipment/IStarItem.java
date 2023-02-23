package biggestxuan.emcworld.api.item.equipment;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/05
 */

import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.item.ItemStack;

public interface IStarItem {
    static int getDifficultyMaxStar(){
        double diff = ConfigManager.DIFFICULTY.get();
        if(diff == 0.5) return 1;
        if(diff <= 1)return 2;
        if(diff <= 1.5) return 3;
        if(diff <= 2) return 4;
        if(diff <= 2.5) return 5;
        if(diff <3) return 6;
        else return 8;
    }
    default void initStar(ItemStack stack){
        stack.getOrCreateTag().putBoolean("star_init",true);
        stack.getOrCreateTag().putInt("max_star",0);
        stack.getOrCreateTag().putInt("star",0);
    }

    default int getMaxStar(ItemStack stack){
        return stack.getOrCreateTag().getInt("max_star");
    }

    default int getStar(ItemStack stack){
        return stack.getOrCreateTag().getInt("star");
    }

    default void addMaxStar(ItemStack stack){
        stack.getOrCreateTag().putInt("max_star",Math.min(getDifficultyMaxStar(),getMaxStar(stack)+1));
    }

    default void addStar(ItemStack stack){
        stack.getOrCreateTag().putInt("star",Math.min(getDifficultyMaxStar(),getStar(stack)+1));
    }

    default double getBuffer(ItemStack stack){
        double buffer = 1;
        if(stack.getItem() instanceof IStarItem){
            IStarItem item = (IStarItem) stack.getItem();
            double base = 0.01d;
            for (int i = 0; i < item.getStar(stack); i++) {
                buffer += base;
                base += 0.01d;
            }
        }
        return buffer;
    }
}
