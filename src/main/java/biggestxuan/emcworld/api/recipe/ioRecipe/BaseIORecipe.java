package biggestxuan.emcworld.api.recipe.ioRecipe;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/15
 */

import biggestxuan.emcworld.api.recipe.helper.NoInventoryRecipe;
import com.blamejared.crafttweaker.api.item.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class BaseIORecipe implements IRecipe<NoInventoryRecipe> {
    protected final ResourceLocation id;
    protected final NonNullList<Ingredient> inputs;
    private final ItemStack output;
    protected final int ticks;

    public BaseIORecipe(ResourceLocation id,NonNullList<Ingredient> inputs,ItemStack output,int ticks){
        this.id = id;
        this.inputs = inputs;
        this.output = output;
        this.ticks = ticks;
    }

    @Override
    public boolean matches(NoInventoryRecipe p_77569_1_, World p_77569_2_) {
        return true;
    }

    @Override
    public ItemStack assemble(NoInventoryRecipe p_77572_1_) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public abstract IRecipeSerializer<?> getSerializer();

    @Override
    public abstract IRecipeType<?> getType();

    @Override
    public NonNullList<Ingredient> getIngredients(){
        return this.inputs;
    }

    public int getTicks(){
        return this.ticks;
    }

    public static NonNullList<Ingredient> getList(IIngredient[] inputs){
        NonNullList<Ingredient> output = NonNullList.create();
        for(IIngredient i : inputs){
            output.add(i.asVanillaIngredient());
        }
        return output;
    }
}
