package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/24
 */

import biggestxuan.emcworld.common.creativeTab.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EWCreativeTabs {
    public static final ItemGroup EW_CREATIVE_TAB = new EWCreativeTab();
    public static final ItemGroup EW_BLOCKS_TAB = new EWOresTab();
    public static final ItemGroup EW_ORE_ITEMS = new EWOreItemsTab();
    public static final ItemGroup EW_EQUIPMENT_TAB = new EWCEquipmentTab();
    public static final ItemGroup EW_FTBQ_TAB = new EWQuestsTab();
}
