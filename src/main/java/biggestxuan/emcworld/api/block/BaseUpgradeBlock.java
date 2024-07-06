package biggestxuan.emcworld.api.block;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.blocks.EWBlock;
import net.minecraft.nbt.CompoundNBT;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/28
 */

@EMCWorldSince("1.0.6")
public abstract class BaseUpgradeBlock extends EWBlock {
    public BaseUpgradeBlock(Properties properties) {
        super(properties);
    }

    public CompoundNBT getRemoveNBT(BaseUpgradeTileEntity tile){
        var nbt = new CompoundNBT();
        nbt.putInt("level",tile.upgradeLevel);
        nbt.putInt("prefix",tile.prefix);
        nbt.putInt("star",tile.star);
        nbt.putInt("max_star", tile.maxStar);
        nbt.putBoolean("star_init", true);
        return nbt;
    }

    public void setPlaceNBT(BaseUpgradeTileEntity tile,CompoundNBT nbt){
        tile.upgradeLevel = nbt.getInt("level");
        tile.prefix = nbt.getInt("prefix");
        tile.star = nbt.getInt("star");
        tile.maxStar = nbt.getInt("max_star");
    }
}
