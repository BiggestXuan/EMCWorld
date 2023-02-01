package biggestxuan.emcworld.common.blocks.PrefixBlock;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/01
 */

import biggestxuan.emcworld.api.block.BaseContainer;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class PrefixContainer extends BaseContainer {
    private final BlockPos pos;
    public PrefixContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public PrefixContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity){
        super(EWContainerTypes.prefixContainer.get(),pContainerId);
        PrefixTileEntity tile = (PrefixTileEntity) blockEntity;
        this.addSlot(new PrefixSlot.ScrollSlot(tile.getInventory(),0,45,31));
        this.addSlot(new PrefixSlot.WeaponSlot(tile.getInventory(),1,114,31));
        pos = tile.getBlockPos();
        addBar(inv);
    }

    public BlockPos getPos(){
        return pos;
    }
}
