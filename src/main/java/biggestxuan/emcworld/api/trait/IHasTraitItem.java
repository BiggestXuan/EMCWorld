package biggestxuan.emcworld.api.trait;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.items.EMCWorldTraitCoreItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

@EMCWorldSince("1.1.0")
public interface IHasTraitItem{
    static boolean isInit(ItemStack stack){
        String name = "emcworld_trait_init";
        if(stack.hasTag()){
            CompoundNBT nbt = stack.getTag();
            return nbt.getBoolean(name);
        }
        return false;
    }

    static void init(ItemStack stack){
        String name = "emcworld_trait_init";
        CompoundNBT nbt = stack.getOrCreateTag();
        if(!isInit(stack)){
            nbt.putBoolean(name,true);
            CompoundNBT nbts = new CompoundNBT();
            for (int i = 1; i <= 5; i++) {
                nbts.put("trait_"+i,new CompoundNBT());
            }
            nbt.put("emcworld_trait",nbts);
            nbt.putInt("emcworld_max_traits",0);
            if(stack.getItem() instanceof EMCWorldTraitCoreItem){
                nbt.putInt("core_progress",0);
            }else{
                nbt.putInt("punch_progress",0);
            }
        }
    }

    default boolean isMaxTraits(ItemStack stack){
        return getMaxTraits(stack) >= limitMaxTraits(stack);
    }

    default int getMaxTraits(ItemStack stack){
        CompoundNBT nbt = stack.getOrCreateTag();
        return Math.min(nbt.getInt("emcworld_max_traits"),limitMaxTraits(stack));
    }

    default void setMaxTraits(ItemStack stack,int amt){
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.putInt("emcworld_max_traits",amt);
    }

    default int limitMaxTraits(ItemStack stack){
        return 5;
    }
}
