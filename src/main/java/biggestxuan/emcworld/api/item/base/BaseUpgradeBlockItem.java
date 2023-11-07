package biggestxuan.emcworld.api.item.base;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.item.IPrefixItem;
import biggestxuan.emcworld.api.item.IUpgradeableItem;
import biggestxuan.emcworld.api.item.equipment.IStarItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/31
 */

@EMCWorldSince("1.0.6")
public abstract class BaseUpgradeBlockItem extends BlockItem implements IUpgradeableItem, IStarItem, IPrefixItem {
    public BaseUpgradeBlockItem(Block p_i48527_1_, Properties p_i48527_2_) {
        super(p_i48527_1_, p_i48527_2_);
    }
}
