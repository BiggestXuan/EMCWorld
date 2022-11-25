package biggestxuan.emcworld.common.blocks.GemstoneBlock;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/16
 */

import biggestxuan.emcworld.api.block.BaseContainer;
import biggestxuan.emcworld.common.blocks.GemstoneBlock.Slot.GemstoneSlot;
import biggestxuan.emcworld.common.blocks.GemstoneBlock.Slot.WeaponSlot;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class GemstoneContainer extends BaseContainer {
    public GemstoneContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public GemstoneContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity){
        super(EWContainerTypes.gemstoneContainer.get(),pContainerId);
        GemstoneTileEntity tile = (GemstoneTileEntity) blockEntity;
        this.addSlot(new WeaponSlot(tile.getInventory(),0,45,31));
        this.addSlot(new GemstoneSlot(tile.getInventory(),1,114,31));
        addBar(inv);
    }
}
