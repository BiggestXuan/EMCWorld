package biggestxuan.emcworld.common.blocks.InfuserBlock;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/14
 */

import biggestxuan.emcworld.api.block.BaseContainer;
import biggestxuan.emcworld.common.blocks.InfuserBlock.Slot.GemSlot;
import biggestxuan.emcworld.common.blocks.InfuserBlock.Slot.ResultSlot;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * References and citations from: https://blog.csdn.net/Jay_fearless/article/details/125549815
 * Author:Jay_fearless
 * LICENCE: CC 4.0 BY-SA
 */

public class InfuserContainer extends BaseContainer {
    private final BlockPos pos;
    private final World world;
    private final IIntArray data;

    public InfuserContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new IntArray(7));
    }

    public InfuserContainer(int pContainerId, PlayerInventory inv, TileEntity entity, IIntArray data) {
        super(EWContainerTypes.infuserContainer.get(),pContainerId);
        this.data = data;
        addDataSlots(this.data);
        InfuserBlockTileEntity tileEntity = (InfuserBlockTileEntity) entity;
        assert tileEntity != null;
        this.addSlot(new Slot(tileEntity.getInventory(),0,43,6));
        this.addSlot(new Slot(tileEntity.getInventory(),1,15,29));
        this.addSlot(new GemSlot(tileEntity.getInventory(),2,43,29));
        this.addSlot(new Slot(tileEntity.getInventory(),3,71,29));
        this.addSlot(new Slot(tileEntity.getInventory(),4,26,52));
        this.addSlot(new Slot(tileEntity.getInventory(),5,62,52));
        this.addSlot(new ResultSlot(tileEntity.getInventory(),6,133,29));
        addBar(inv);
        this.pos = tileEntity.getBlockPos();
        this.world = tileEntity.getLevel();
    }

    public int getProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 20;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getCraftLevel(){
        return this.data.get(4);
    }

    public int getRadiation(){
        return Math.min(57 * this.data.get(3) / this.data.get(6),57);
    }

    public int getEMC(){
        return (int) (70 - (70 * (1.0d * this.data.get(2) / this.data.get(5))));
    }

    public BlockPos getPos(){
        return pos;
    }

    public World getWorld() {
        return world;
    }
}
