package biggestxuan.emcworld.common.items.Equipment.Scroll;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/28
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.base.BaseDifficultyItem;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.item.ItemStack;

public class ScrollItem extends BaseDifficultyItem implements IUpgradeableMaterial {
    protected final int weight;

    public ScrollItem(float difficulty,int weight) {
        super(difficulty,false,EMCWorld.tc("tooltip.emcworld.scroll_weight",weight));
        this.weight = weight;
    }

    @Override
    public int getWeight(ItemStack stack){
        return this.weight;
    }

    @Override
    public double getEMCCostRate(){
        return 0d;
    }
}

