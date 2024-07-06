package biggestxuan.emcworld.common.items;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.base.BaseUpgradeBlockItem;
import biggestxuan.emcworld.common.registry.EWCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/09
 */

@EMCWorldSince("1.0.5")
public class EMCCoreBlockItem extends BaseUpgradeBlockItem {
    public EMCCoreBlockItem(Block p_i48527_1_) {
        super(p_i48527_1_,new Properties().tab(EWCreativeTabs.EW_BLOCKS_TAB).stacksTo(1));
    }

    @Override
    public int getMaxLevel() {
        return 8;
    }

    @Override
    public int getWeightRequired(ItemStack stack) {
        int level = getLevel(stack);
        switch (level){
            case 0:
                return 100;
            case 1:
                return 350;
            case 2:
                return 1000;
            case 3:
                return 3500;
            case 4:
                return 8000;
            case 5:
                return 17500;
            case 6:
                return 27500;
            case 7:
                return 60000;
        }
        return EMCWorld.HOMO;
    }
}
