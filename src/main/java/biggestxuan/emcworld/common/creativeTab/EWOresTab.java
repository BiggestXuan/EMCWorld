package biggestxuan.emcworld.common.creativeTab;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/28
 */

import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EWOresTab extends ItemGroup {
    public EWOresTab(){
        super("EMCWOresGroup");
    }
    @Override
    public ItemStack makeIcon(){
        return new ItemStack(EWItems.END_EMC_ORE.get());
    }
}
