package biggestxuan.emcworld.common.blocks.tile;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/20
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.common.blocks.container.AdvancedUpdateContainer;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class AdvancedUpdateTileEntity extends BaseContainerTileEntity implements ITickableTileEntity, INamedContainerProvider{
    private Inventory inventory = new Inventory(1);
    private AdvancedUpdateNumber number = new AdvancedUpdateNumber();
    public AdvancedUpdateTileEntity(){
        super(EWTileEntityTypes.AdvancedUpdateTileEntity.get());
    }

    @Override
    public void tick(){
        if(!Objects.requireNonNull(this.getLevel()).isClientSide){
            this.number.set(0,this.inventory.getItem(0).getCount());
        }
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("gui.emcworld.update_core");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, @Nonnull PlayerInventory p_createMenu_2_, @Nonnull PlayerEntity p_createMenu_3_) {
        assert level != null;
        return new AdvancedUpdateContainer(p_createMenu_1_,p_createMenu_2_,this.getBlockPos(),level,number);
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_) {
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item")));
        super.load(p_230337_1_,p_230337_2_);
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        ItemStack itemStack = this.inventory.getItem(0).copy();
        p_189515_1_.put("item",itemStack.serializeNBT());
        return super.save(p_189515_1_);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public BlockPos getPos() {
        return this.getBlockPos();
    }

    @Override
    public World getLevel(){
        return this.level;
    }
}
