package biggestxuan.emcworld.common.blocks.tile;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/16
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.api.item.equipment.IGemInlaidItem;
import biggestxuan.emcworld.common.blocks.container.GemstoneContainer;
import biggestxuan.emcworld.common.items.Equipment.BaseWeaponGemItem;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GemstoneTileEntity extends BaseContainerTileEntity implements ITickableTileEntity, INamedContainerProvider {
    private final Inventory inventory = new Inventory(2);
    private MODE mode = MODE.NONE;
    private STATE state = STATE.STOP;

    public GemstoneTileEntity() {
        super(EWTileEntityTypes.GemstoneTileEntity.get());
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

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public void tick() {
        mode = MODE.NONE;
        if(inventory.getItem(0).getItem() instanceof IGemInlaidItem && inventory.getItem(1).getItem() instanceof BaseWeaponGemItem){
            mode = MODE.FUSION;
        }
        if(inventory.getItem(0).getItem() instanceof IGemInlaidItem && inventory.getItem(1).getItem().equals(Items.AIR)){
            mode = MODE.SPLIT;
        }
        if(state == STATE.START){
            if(mode == MODE.FUSION){
                addTag();
                costGem();
            }
            if(mode == MODE.SPLIT){
                clearTag();
            }
            state = STATE.STOP;
        }
    }

    public void setState(STATE state){
        this.state = state;
    }

    private void costGem(){
        inventory.getItem(1).shrink(1);
    }

    private void addTag(){
        BaseWeaponGemItem gem = (BaseWeaponGemItem) inventory.getItem(1).getItem();
        IGemInlaidItem weapon = (IGemInlaidItem) inventory.getItem(0).getItem();
        weapon.setGemIndex(inventory.getItem(0),gem.getIndex());
    }

    private void clearTag(){
        IGemInlaidItem weapon = (IGemInlaidItem) inventory.getItem(0).getItem();
        weapon.setGemIndex(inventory.getItem(0),0);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("gui.emcworld.gemstone_core");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new GemstoneContainer(p_createMenu_1_,p_createMenu_2_,this);
    }

    public enum MODE{
        FUSION,SPLIT,NONE;
    }

    public enum STATE{
        START,STOP;
    }
}
