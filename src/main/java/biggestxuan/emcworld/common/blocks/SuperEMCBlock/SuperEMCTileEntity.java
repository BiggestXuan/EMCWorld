package biggestxuan.emcworld.common.blocks.SuperEMCBlock;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/26
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.api.item.IEMCInfuserItem;
import biggestxuan.emcworld.common.compact.Projecte.EMCGemsMapping;
import biggestxuan.emcworld.common.items.EMCGemItem;
import biggestxuan.emcworld.common.recipes.SuperEMCRecipe;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SuperEMCTileEntity extends BaseContainerTileEntity implements ITickableTileEntity, INamedContainerProvider {
    private final Inventory inventory = new Inventory(3);
    private MODE mode = MODE.STOP;
    public SuperEMCTileEntity() {
        super(EWTileEntityTypes.superEMCTileEntity.get());
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void tick() {
        if(mode == MODE.START){
            ItemStack recipeResult = SuperEMCRecipe.match(inventory);
            if(!inventory.getItem(1).equals(recipeResult)){
                cost();
                inventory.setItem(2,recipeResult);
                stop();
            } else if(inventory.getItem(0).getItem() instanceof EMCGemItem && inventory.getItem(1).getItem() instanceof IEMCInfuserItem){
                ItemStack out = inventory.getItem(1).copy();
                IEMCInfuserItem item = (IEMCInfuserItem) out.getItem();
                Item gem = inventory.getItem(0).getItem();
                for(EMCGemsMapping mapping : EMCGemsMapping.values()){
                    if(gem.equals(mapping.getItem())){
                        if(item.getInfuser(out) == item.getMaxInfuser(out)){
                            break;
                        }
                        item.addInfuser(out,mapping.getBaseEMC());
                        cost();
                        inventory.setItem(2,out);
                    }
                }
            }
        }
        stop();
    }

    private void cost(){
        for (int i = 0; i <= 1; i++) {
            ItemStack stack = inventory.getItem(i);
            stack.shrink(1);
            inventory.setItem(i,stack);
        }
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_){
        super.load(p_230337_1_,p_230337_2_);
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item1")));
        this.inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item2")));
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        p_189515_1_.put("item",this.inventory.getItem(0).serializeNBT());
        p_189515_1_.put("item1",this.inventory.getItem(1).serializeNBT());
        p_189515_1_.put("item2",this.inventory.getItem(2).serializeNBT());
        super.save(p_189515_1_);
        return p_189515_1_;
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("block.emcworld.emc_super");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new SuperEMCContainer(p_createMenu_1_,p_createMenu_2_,this);
    }

    public void stop(){
        mode = MODE.STOP;
    }

    public void start(){
        mode = MODE.START;
    }

    enum MODE{
        STOP,START
    }
}
