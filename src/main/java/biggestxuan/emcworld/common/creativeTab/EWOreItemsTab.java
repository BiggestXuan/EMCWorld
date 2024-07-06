package biggestxuan.emcworld.common.creativeTab;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/28
 */

import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EWOreItemsTab extends ItemGroup {
    public EWOreItemsTab(){
        super("EMCWOreItemsGroup");
    }
    @Override
    public ItemStack makeIcon(){
        return new ItemStack(EWItems.DIRTY_DUST_RAINBOW.get());
    }
}
