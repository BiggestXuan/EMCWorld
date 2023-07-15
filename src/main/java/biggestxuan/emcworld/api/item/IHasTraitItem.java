package biggestxuan.emcworld.api.item;

import biggestxuan.emcworld.api.EMCWorldSince;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/13
 */

@EMCWorldSince("1.1.0")
public interface IHasTraitItem extends IContainerTraitItem{
    default void init(ItemStack stack){
        String name = "emcworld_trait_init";
        CompoundNBT nbt = stack.getOrCreateTag();
        if(!nbt.getBoolean(name)){
            nbt.putBoolean(name,true);
            CompoundNBT nbts = new CompoundNBT();
            for (int i = 1; i <= 5; i++) {
                nbts.put("trait_"+i,new CompoundNBT());
            }
            nbt.put("emcworld_trait",nbts);
        }
    }
}
