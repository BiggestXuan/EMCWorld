package biggestxuan.emcworld.common.blocks.PrefixBlock;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/01
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
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
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PrefixTileEntity extends BaseContainerTileEntity implements ITickableTileEntity, INamedContainerProvider {
    private Inventory inventory = new Inventory(2);
    public State state = State.STOP;
    public PrefixTileEntity() {
        super(EWTileEntityTypes.PrefixCoreTileEntity.get());
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("");
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_){
        super.load(p_230337_1_,p_230337_2_);
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item1")));
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        p_189515_1_.put("item",this.inventory.getItem(0).serializeNBT());
        p_189515_1_.put("item1",this.inventory.getItem(1).serializeNBT());
        super.save(p_189515_1_);
        return p_189515_1_;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new PrefixContainer(p_createMenu_1_,p_createMenu_2_,this);
    }

    @Override
    public void tick() {
        state = State.STOP;
    }

    public enum State{
        STOP,START
    }
}
