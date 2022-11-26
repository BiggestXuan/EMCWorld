package biggestxuan.emcworld.common.recipes;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/15
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.recipe.ioRecipe.BaseIORecipe;
import biggestxuan.emcworld.api.recipe.ioRecipe.BaseIOSerializer;
import biggestxuan.emcworld.api.recipe.ioRecipe.BaseIOType;
import biggestxuan.emcworld.common.registry.EWRecipeTypes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class SteelFurnaceRecipe extends BaseIORecipe {

    public SteelFurnaceRecipe(ResourceLocation id,NonNullList<Ingredient> inputs,ItemStack output,int ticks){
        super(id,inputs,output,ticks);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return EWRecipeTypes.STEEL_FURNACE_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type extends BaseIOType<SteelFurnaceRecipe> {
        public static final String ID = "steel_furnace";
        public static final IRecipeType<SteelFurnaceRecipe> INSTANCE = EWRecipeTypes.register(ID);
    }

    public static class Serializer extends BaseIOSerializer<SteelFurnaceRecipe> {
        public static final IRecipeSerializer<SteelFurnaceRecipe> INSTANCE = new Serializer();
        public static final ResourceLocation ID = EMCWorld.rl(Type.ID);

        @Override
        public SteelFurnaceRecipe fromJson(ResourceLocation p_199425_1_, JsonObject p_199425_2_) {
            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(p_199425_2_,"output"));
            JsonArray inputs = JSONUtils.getAsJsonArray(p_199425_2_,"ingredients");
            int ticks = JSONUtils.getAsInt(p_199425_2_,"time");
            NonNullList<Ingredient> ingredients = NonNullList.withSize(inputs.size(),Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                ingredients.set(i,Ingredient.fromJson(inputs.get(i)));
            }
            return new SteelFurnaceRecipe(p_199425_1_,ingredients,output,ticks);
        }

        @Nullable
        @Override
        public SteelFurnaceRecipe fromNetwork(ResourceLocation p_199426_1_, PacketBuffer p_199426_2_) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(p_199426_2_.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(p_199426_2_));
            }
            ItemStack output = p_199426_2_.readItem();
            int ticks = p_199426_2_.readInt();
            return new SteelFurnaceRecipe(p_199426_1_,inputs,output,ticks);
        }

        @Override
        public void toNetwork(PacketBuffer p_199427_1_, SteelFurnaceRecipe p_199427_2_) {
            p_199427_1_.writeInt(p_199427_2_.getIngredients().size());
            for (Ingredient ing : p_199427_2_.getIngredients()) {
                ing.toNetwork(p_199427_1_);
            }
            p_199427_1_.writeItemStack(p_199427_2_.getResultItem(), false);
            p_199427_1_.writeInt(p_199427_2_.ticks);
        }

        @Override
        public IRecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }
    }
}
