package biggestxuan.emcworld.common.blocks.SteelFurnace;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/16
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.block.BaseContainerTileEntity;
import biggestxuan.emcworld.common.blocks.MultiBlock;
import biggestxuan.emcworld.common.recipes.SteelFurnaceRecipe;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SteelFurnaceTileEntity extends BaseContainerTileEntity implements ITickableTileEntity, INamedContainerProvider {
    protected Inventory inventory = new Inventory(3);
    private int progress;
    private int maxProgress;
    private boolean canCraft;
    private int speed;
    private SteelFurnaceRecipe recipe = null;
    public IIntArray data;

    public SteelFurnaceTileEntity() {
        super(EWTileEntityTypes.SteelFurnaceTileEntity.get());
        this.data = new IIntArray() {
            @Override
            public int get(int p_221476_1_) {
                if(p_221476_1_ == 0){
                    return getProgress();
                }
                if(p_221476_1_ == 1){
                    return speed;
                }
                return 0;
            }

            @Override
            public void set(int p_221477_1_, int p_221477_2_) {
                if(p_221477_1_ == 1){
                    speed = p_221477_2_;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public void tick() {
        if(level == null || level.isClientSide) return;
        if(!MultiBlock.SteelFurnaceCanCraft(level,this.getBlockPos())){
            progress = 0;
            maxProgress = 0;
            return;
        }
        recipe = getRecipe();
        if(!isCanCraft()) return;
        maxProgress = recipe.getTicks();
        progress++;
        if(progress >= maxProgress){
            craftItem();
            progress = 0;
            maxProgress = 0;
        }
        recipe = null;
    }

    private void craftItem(){
        inventory.getItem(0).shrink(1);
        inventory.getItem(1).shrink(1);
        if(inventory.getItem(2).equals(ItemStack.EMPTY)){
            inventory.getItem(2).grow(recipe.getResultItem().getCount());
        }
        inventory.setItem(2,recipe.getResultItem());
    }

    private boolean isCanCraft(){
        if(recipe == null) return false;
        return (inventory.getItem(2).equals(ItemStack.EMPTY) || inventory.getItem(2).equals(recipe.getResultItem())) && inventory.getItem(2).getCount()+recipe.getResultItem().getCount() <= inventory.getItem(2).getMaxStackSize();
    }

    @Nullable
    private SteelFurnaceRecipe getRecipe(){
        ItemStack s1 = inventory.getItem(0);
        ItemStack s2 = inventory.getItem(1);
        List<SteelFurnaceRecipe> recipeList = level.getRecipeManager().getAllRecipesFor(SteelFurnaceRecipe.Type.INSTANCE);
        //System.out.println(recipeList.get(0).toString());
        for(SteelFurnaceRecipe recipe2 : recipeList){
            NonNullList<Ingredient> i = recipe2.getIngredients();
            if(i.get(0).test(s1) && i.get(1).test(s2)){
                return recipe2;
            }
        }
        return null;
    }

    @Override
    public void load(@Nonnull BlockState p_230337_1_, @Nonnull CompoundNBT p_230337_2_) {
        super.load(p_230337_1_,p_230337_2_);
        progress = p_230337_2_.getInt("progress");
        speed = p_230337_2_.getInt("speed");
        inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item")));
        inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item1")));
        inventory.addItem(ItemStack.of(p_230337_2_.getCompound("item2")));
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT p_189515_1_){
        p_189515_1_.putInt("progress",progress);
        p_189515_1_.putInt("speed",speed);
        p_189515_1_.put("item",inventory.getItem(0).copy().serializeNBT());
        p_189515_1_.put("item1",inventory.getItem(1).copy().serializeNBT());
        p_189515_1_.put("item2",inventory.getItem(2).copy().serializeNBT());
        super.save(p_189515_1_);
        return p_189515_1_;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return EMCWorld.tc("block.emcworld.steel_furnace_core");
    }

    private int getProgress(){
        return 100 * progress / maxProgress;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, @Nonnull PlayerInventory p_createMenu_2_, @Nonnull PlayerEntity p_createMenu_3_) {
        return new SteelFurnaceCoreContainer(p_createMenu_1_,p_createMenu_2_,this,data);
    }
}
