package biggestxuan.emcworld.common.items.Equipment.Scroll;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/28
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IUpgradeableMaterial;
import biggestxuan.emcworld.api.item.base.BaseDifficultyItem;
import net.minecraft.item.ItemStack;

public class BiggestXuanScroll extends BaseDifficultyItem implements IUpgradeableMaterial {
    public static float chance = 0.15f;

    public BiggestXuanScroll() {
        super(3.0f, false, EMCWorld.tc("tooltip.emcworld.bx_scroll",(int) (chance*100)+"%"));
    }

    @Override
    public int getWeight(ItemStack stack) {
        return EMCWorld.HOMO;
    }
}
