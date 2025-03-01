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
import net.minecraft.tileentity.TileEntity;

public class ScrollItem extends BaseDifficultyItem implements IUpgradeableMaterial {
    protected final int weight;

    public ScrollItem(float difficulty,int weight) {
        super(difficulty,false,weight == 0 ? EMCWorld.tc("") : EMCWorld.tc("tooltip.emcworld.scroll_weight",weight));
        this.weight = weight;
    }

    public ScrollItem(float difficulty) {
        this(difficulty,0);
    }

    @Override
    public int getActWeight(ItemStack stack, ItemStack target, TileEntity tileEntity) {
        return weight;
    }

    @Override
    public double getEMCCostRate(){
        return 0d;
    }

    @Override
    public double breakWeaponRate(){
        return IUpgradeableMaterial.super.breakWeaponRate();
    }
}

