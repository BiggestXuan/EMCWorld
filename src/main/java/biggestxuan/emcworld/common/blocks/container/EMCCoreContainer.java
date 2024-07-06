package biggestxuan.emcworld.common.blocks.container;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.api.block.EMCWorldBaseContainer;
import biggestxuan.emcworld.api.trait.IHasTraitItem;
import biggestxuan.emcworld.common.blocks.tile.EMCCoreTileEntity;
import biggestxuan.emcworld.common.items.EMCGemItem;
import biggestxuan.emcworld.common.items.EMCWorldTraitCoreItem;
import biggestxuan.emcworld.common.registry.EWContainerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/11/19
 */

@EMCWorldSince("1.1.0")
public abstract class EMCCoreContainer extends EMCWorldBaseContainer {
    public static class Assembler extends EMCCoreContainer{
        public Assembler(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
            this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
        }

        public Assembler(int pContainerId, PlayerInventory inv, TileEntity tile) {
            super(EWContainerTypes.emcCoreAssemblerContainer.get(), pContainerId, inv, tile);
            addSlot(new EMCCoreWeaponSlot(this.tile.inventory,0,80,9));
            int x = 12;
            for (int i = 1; i < 6; i++) {
                addSlot(new EMCCoreSlot(this.tile.inventory,i,x,48));
                x += 34;
            }
        }
    }

    public static class Puller extends EMCCoreContainer{
        public Puller(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
            this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
        }

        public Puller(int pContainerId, PlayerInventory inv, TileEntity tile) {
            super(EWContainerTypes.emcCorePullerContainer.get(), pContainerId, inv, tile);
            addSlot(new EMCCoreWeaponSlot(this.tile.inventory,0,50,15));
            addSlot(new Slot(this.tile.inventory,1,110,15));
            int x = 20;
            for (int i = 2; i < 7; i++) {
                addSlot(new EMCCoreOutputSlot(this.tile.inventory,i,x,48));
                x += 30;
            }
        }
    }

    public static class Generator extends EMCCoreContainer{
        public Generator(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
            this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
        }

        public Generator(int pContainerId, PlayerInventory inv, TileEntity tile) {
            super(EWContainerTypes.emcCoreGeneratorContainer.get(), pContainerId, inv, tile);
            int y = 35;
            addSlot(new EMCCoreSlot(this.tile.inventory,0,29,y));
            addSlot(new EMCCoreGemSlot(this.tile.inventory,1,80,y));
            addSlot(new Slot(this.tile.inventory,2,131,y));
        }

    }

    public static class Puncher extends EMCCoreContainer{
        public Puncher(int pContainerId, PlayerInventory inv, PacketBuffer extraData) {
            this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
        }

        public Puncher(int pContainerId, PlayerInventory inv, TileEntity tile) {
            super(EWContainerTypes.emcCorePuncherContainer.get(), pContainerId, inv, tile);
            int y = 35;
            addSlot(new EMCCoreWeaponSlot(this.tile.inventory,0,29,y));
            addSlot(new EMCCoreGemSlot(this.tile.inventory,1,80,y));
            addSlot(new Slot(this.tile.inventory,2,131,y));
        }
    }

    public final World world;
    public final BlockPos pos;
    public EMCCoreTileEntity tile;

    public EMCCoreContainer(ContainerType<?> type,int pContainerId, PlayerInventory inv, TileEntity tile) {
        super(type,pContainerId);
        this.world = tile.getLevel();
        this.pos = tile.getBlockPos();
        addBar(inv);
        if(tile instanceof EMCCoreTileEntity){
            this.tile = (EMCCoreTileEntity) tile;
        }
    }

    public static class EMCCoreWeaponSlot extends Slot {

        public EMCCoreWeaponSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
            super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        }

        @Override
        public boolean mayPlace(ItemStack p_75214_1_) {
            return p_75214_1_.getItem() instanceof IHasTraitItem && !(p_75214_1_.getItem() instanceof EMCWorldTraitCoreItem);
        }
    }

    public static class EMCCoreSlot extends Slot{

        public EMCCoreSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
            super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        }

        @Override
        public boolean mayPlace(ItemStack p_75214_1_) {
            return p_75214_1_.getItem() instanceof EMCWorldTraitCoreItem;
        }
    }

    public static class EMCCoreOutputSlot extends Slot{

        public EMCCoreOutputSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
            super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        }

        @Override
        public boolean mayPlace(ItemStack p_75214_1_) {
            return false;
        }
    }

    public static class EMCCoreGemSlot extends Slot{

        public EMCCoreGemSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
            super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        }

        @Override
        public boolean mayPlace(ItemStack p_75214_1_) {
            return p_75214_1_.getItem() instanceof EMCGemItem;
        }
    }
}
