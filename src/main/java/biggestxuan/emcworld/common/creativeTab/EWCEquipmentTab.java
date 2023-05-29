package biggestxuan.emcworld.common.creativeTab;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/27
 */

import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EWCEquipmentTab extends ItemGroup {
    public EWCEquipmentTab(){
        super("EMCWEquipment");
    }
    @Override
    public ItemStack makeIcon(){
        return new ItemStack(EWItems.ULTIMATE_EMC_STORED_TOTEM.get());
    }

}
