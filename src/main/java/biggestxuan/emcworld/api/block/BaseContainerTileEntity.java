package biggestxuan.emcworld.api.block;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/13
 */

import net.minecraft.inventory.Inventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public abstract class BaseContainerTileEntity extends TileEntity {
    public BaseContainerTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Nullable
    public abstract Inventory getInventory();
}
