package biggestxuan.emcworld.common.blocks.container;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/06
 */

import biggestxuan.emcworld.api.block.EMCWorldBaseContainer;
import biggestxuan.emcworld.common.blocks.tile.SteelFurnaceTileEntity;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class SteelFurnaceCoreContainer extends EMCWorldBaseContainer {
    private final IIntArray data;
    public SteelFurnaceCoreContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()),new IntArray(3));
    }

    public SteelFurnaceCoreContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity, IIntArray data){
        super(EWContainerTypes.steelFurnaceContainer.get(),pContainerId);
        SteelFurnaceTileEntity tile = (SteelFurnaceTileEntity) blockEntity;
        this.addSlot(new Slot(tile.getInventory(),0,45,11));
        this.addSlot(new Slot(tile.getInventory(),1,45,59));
        this.addSlot(new Slot(tile.getInventory(),2,121,35));
        addBar(inv);
        this.data = data;
    }

    public int getProgress(){
        return 29 * data.get(0) / 100;
    }

    public int getSpeed(){
        return data.get(1);
    }
}
