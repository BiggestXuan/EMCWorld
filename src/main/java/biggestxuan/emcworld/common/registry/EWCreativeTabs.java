package biggestxuan.emcworld.common.registry;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/24
 */

import biggestxuan.emcworld.common.creativeTab.EWCEquipmentTab;
import biggestxuan.emcworld.common.creativeTab.EWCreativeTab;
import biggestxuan.emcworld.common.creativeTab.EWOreItemsTab;
import biggestxuan.emcworld.common.creativeTab.EWOresTab;
import net.minecraft.item.ItemGroup;

public class EWCreativeTabs {
    public static final ItemGroup EW_CREATIVE_TAB = new EWCreativeTab();
    public static final ItemGroup EW_ORES_TAB = new EWOresTab();
    public static final ItemGroup EW_ORE_ITEMS = new EWOreItemsTab();
    public static final ItemGroup EW_EQUIPMENT_TAB = new EWCEquipmentTab();
}
