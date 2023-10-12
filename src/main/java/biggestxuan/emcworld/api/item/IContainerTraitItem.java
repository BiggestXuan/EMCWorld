package biggestxuan.emcworld.api.item;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.traits.ITrait;
import biggestxuan.emcworld.common.traits.TraitUtils;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/14
 */

@EMCWorldSince("1.1.0")
public interface IContainerTraitItem {
    default List<ITrait> getTraits(ItemStack stack){
        return TraitUtils.getStackTraits(stack);
    }

    default void setTraits(ItemStack stack,ITrait trait){
        TraitUtils.addTrait(stack,trait);
    }
}
