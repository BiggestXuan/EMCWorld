package biggestxuan.emcworld.client;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.trait.ITrait;
import biggestxuan.emcworld.common.items.EMCWorldTraitCoreItem;
import biggestxuan.emcworld.common.traits.TraitUtils;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/11/23
 */

@EMCWorldSince("1.1.0")
public class EMCCoreItemColor implements IItemColor {
    @Override
    public int getColor(ItemStack stack, int p_getColor_2_) {
        if(stack.getItem() instanceof EMCWorldTraitCoreItem){
            ITrait trait = TraitUtils.getTrait(stack,1);
            if(trait.isFake()){
                return 0xb2b5b8;
            }
            return trait.getColor();
        }
        return 0;
    }
}
