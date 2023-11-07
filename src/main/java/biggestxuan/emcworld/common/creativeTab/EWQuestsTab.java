package biggestxuan.emcworld.common.creativeTab;

import biggestxuan.emcworld.common.registry.EWItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/14
 */

public class EWQuestsTab extends ItemGroup {
    public EWQuestsTab() {
        super("emcworld_quests");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(EWItems.EMCWORLD_QUEST_ITEM.get());
    }
}
