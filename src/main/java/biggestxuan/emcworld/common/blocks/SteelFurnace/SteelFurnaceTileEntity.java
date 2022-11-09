package biggestxuan.emcworld.common.blocks.SteelFurnace;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/16
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.MultiBlock;
import biggestxuan.emcworld.common.recipes.SteelFurnaceRecipe;
import biggestxuan.emcworld.common.registry.EWTileEntityTypes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SteelFurnaceTileEntity extends TileEntity implements ITickableTileEntity {
    private int progress;
    private int maxProgress;
    private boolean canCraft;
    private List<ItemStack> inventory;

    public SteelFurnaceTileEntity() {
        super(EWTileEntityTypes.SteelFurnaceTileEntity.get());
    }

    @Override
    public void tick() {
        if(this.level == null) return;
        if(this.level.isClientSide) return;
        canCraft = MultiBlock.SteelFurnaceCanCraft(this.level,this.getBlockPos());
        if(!canCraft){
            resetCraft();
            return;
        }
        inventory = new ArrayList<>();
        if(getInputs(this).size() !=0 ) {
            inventory.addAll(getInputs(this));
        }
        SteelFurnaceRecipe recipe = getRecipe(this);
        if(recipe == null) return;
        maxProgress = recipe.getTicks();
        progress ++;
        if(progress >= maxProgress && maxProgress != 0){
            craftItem(recipe);
            resetCraft();
        }
    }

    private void craftItem(SteelFurnaceRecipe recipe){
        World level = this.level;
        if(level == null) return;
        for(ItemEntity entity:getNearItem(this)){
            if(isInRecipe(entity,recipe)){
                ItemStack stack = entity.getItem();
                stack.shrink(1);
                entity.setItem(stack);
            }
        }
        level.addFreshEntity(new ItemEntity(level,this.getBlockPos().getX(),this.getBlockPos().getY()+1,this.getBlockPos().getZ(),recipe.getResultItem()));
    }

    private void resetCraft(){
        progress = 0;
        maxProgress = 0;
    }

    private boolean isInRecipe(ItemEntity entity,SteelFurnaceRecipe recipe){
        Item entityItem = entity.getItem().getItem();
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            for(ItemStack s:recipe.getIngredients().get(i).getItems()){
                if(entityItem.equals(s.getItem())){
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    private SteelFurnaceRecipe getRecipe(SteelFurnaceTileEntity entity){
        if(entity.level == null || entity.level.getServer() == null ) return null;
        List<SteelFurnaceRecipe> allRecipe = entity.level.getServer().getRecipeManager().getAllRecipesFor(SteelFurnaceRecipe.Type.INSTANCE);
        for(SteelFurnaceRecipe recipe:allRecipe){
            if(matchRecipe(recipe)){
                return recipe;
            }
        }
        return null;
    }

    private boolean matchRecipe(SteelFurnaceRecipe recipe){
        NonNullList<Ingredient> requireInputs = recipe.getIngredients();
        List<Item> allItem = new ArrayList<>();
        List<Item> inv = new ArrayList<>();
        if(inventory.size() == 0) return false;
        for(ItemStack s:inventory){
            inv.add(s.getItem());
        }
        for(Ingredient ingredient:requireInputs){
            for(ItemStack stack:ingredient.getItems()){
                allItem.add(stack.getItem());
            }
        }
        for(Item i :allItem){
            if(!inv.contains(i)){
                return false;
            }
        }
        return true;
    }

    private List<ItemStack> getInputs(SteelFurnaceTileEntity entity){
        List<ItemStack> o = new ArrayList<>();
        for(ItemEntity i:getNearItem(entity)){
            o.add(i.getItem());
        }
        return o;
    }

    private List<ItemEntity> getNearItem(SteelFurnaceTileEntity entity){
        BlockPos pos = entity.getBlockPos();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(x-1,y,z-1),new BlockPos(x+1,y+3,z+1));
        if(entity.level == null){
            return new ArrayList<>();
        }
        return entity.level.getLoadedEntitiesOfClass(ItemEntity.class,aabb);
    }
}
