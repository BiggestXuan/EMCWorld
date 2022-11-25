package biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.api.block.BaseContainer;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.Slot.LuckySlot;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.Slot.MaterialSlot;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.Slot.WeaponSlot;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class WeaponUpgradeContainer extends BaseContainer {
    private final BlockPos pos;
    private final World world;
    private final IIntArray data;

    public WeaponUpgradeContainer(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new IntArray(7));
    }

    public WeaponUpgradeContainer(int pContainerId, PlayerInventory inv, TileEntity blockEntity, IIntArray intArray) {
        super(EWContainerTypes.weaponUpgradeContainer.get(),pContainerId);
        this.data = intArray;
        addDataSlots(this.data);
        WeaponUpgradeBlockTileEntity entity = (WeaponUpgradeBlockTileEntity) blockEntity;
        this.addSlot(new WeaponSlot(entity.getInventory(),0,53,29));
        this.addSlot(new MaterialSlot(entity.getInventory(),1,26,7));
        this.addSlot(new MaterialSlot(entity.getInventory(),2,80,7));
        this.addSlot(new MaterialSlot(entity.getInventory(),3,26,53));
        this.addSlot(new MaterialSlot(entity.getInventory(),4,80,53));
        this.addSlot(new LuckySlot(entity.getInventory(),5,116,53));
        addBar(inv);
        this.pos = entity.getBlockPos();
        this.world = entity.getLevel();
    }

    public BlockPos getPos(){
        return this.pos;
    }

    public World getWorld(){
        return this.world;
    }

    public int getChance(){
        return this.data.get(0);
    }
}
