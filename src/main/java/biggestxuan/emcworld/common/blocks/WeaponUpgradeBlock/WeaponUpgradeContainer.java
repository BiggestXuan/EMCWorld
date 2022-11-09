package biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/27
 */

import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.Slot.LuckySlot;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.Slot.MaterialSlot;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.Slot.WeaponSlot;
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


public class WeaponUpgradeContainer extends Container {
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
        addPlayerHotbar(inv);
        addPlayerInventory(inv);
        this.pos = entity.getBlockPos();
        this.world = entity.getLevel();
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_) {
        return true;
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
