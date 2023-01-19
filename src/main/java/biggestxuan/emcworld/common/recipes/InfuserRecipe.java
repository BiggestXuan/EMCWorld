package biggestxuan.emcworld.common.recipes;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/15
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWRecipeTypes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class InfuserRecipe implements IRecipe<Inventory> {
    public static final IRecipeType<InfuserRecipe> RECIPE = Type.INSTANCE;

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> inputs;
    private final int craftLevel;
    private final int costEMC;
    private final int costTime;

    public InfuserRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> inputs,int craftLevel,int costEMC,int costTime){
        this.id = id;
        this.output = output;
        this.inputs = inputs;
        this.craftLevel = craftLevel;
        this.costEMC = costEMC;
        this.costTime = costTime;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public boolean matches(Inventory p_77569_1_, World p_77569_2_) {
        return inputs.get(0).test(p_77569_1_.getItem(0)) && inputs.get(1).test(p_77569_1_.getItem(1)) && inputs.get(2).test(p_77569_1_.getItem(3))&& inputs.get(3).test(p_77569_1_.getItem(4))&& inputs.get(4).test(p_77569_1_.getItem(5));
    }

    @Override
    public ItemStack assemble(Inventory p_77572_1_) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return EWRecipeTypes.INFUSER_RECIPE.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public int getCostEMC() {
        return costEMC;
    }

    public int getCostEMCRate(){
        return costEMC / costTime;
    }

    public int getCostTime() {
        return costTime;
    }

    public int getCraftLevel() {
        return craftLevel;
    }

    public static class Type implements IRecipeType<InfuserRecipe>{
        private Type(){}
        public static final String ID = "infuser";
        public static final IRecipeType<InfuserRecipe> INSTANCE = EWRecipeTypes.register(ID);
    }

    public static class Serializer implements IRecipeSerializer<InfuserRecipe>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = EMCWorld.rl("infuser");

        @Override
        public InfuserRecipe fromJson(ResourceLocation p_199425_1_, JsonObject p_199425_2_) {
            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(p_199425_2_,"output"));
            JsonArray ingredients = JSONUtils.getAsJsonArray(p_199425_2_,"ingredients");
            int craftLevel = JSONUtils.getAsInt(p_199425_2_,"craftLevel");
            int costEMC = JSONUtils.getAsInt(p_199425_2_,"costEMC");
            int costTime = JSONUtils.getAsInt(p_199425_2_,"costTime");
            NonNullList<Ingredient> inputs = NonNullList.withSize(5,Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i,Ingredient.fromJson(ingredients.get(i)));
            }
            return new InfuserRecipe(p_199425_1_,output,inputs,craftLevel,costEMC,costTime);
        }

        @Nullable
        @Override
        public InfuserRecipe fromNetwork(ResourceLocation p_199426_1_, PacketBuffer p_199426_2_) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(p_199426_2_.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromNetwork(p_199426_2_));
            ItemStack output = p_199426_2_.readItem();
            int craftLevel = p_199426_2_.readInt();
            int costEMC = p_199426_2_.readInt();
            int costTime = p_199426_2_.readInt();
            return new InfuserRecipe(p_199426_1_,output,inputs,craftLevel,costEMC,costTime);
        }

        @Override
        public void toNetwork(PacketBuffer p_199427_1_, InfuserRecipe p_199427_2_) {
            p_199427_1_.writeInt(p_199427_2_.getIngredients().size());
            for (Ingredient ing : p_199427_2_.getIngredients()) {
                ing.toNetwork(p_199427_1_);
            }
            p_199427_1_.writeItemStack(p_199427_2_.getResultItem(), false);
            p_199427_1_.writeInt(p_199427_2_.getCraftLevel());
            p_199427_1_.writeInt(p_199427_2_.getCostEMC());
            p_199427_1_.writeInt(p_199427_2_.getCostTime());
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

        @Override
        public Class<IRecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass();
        }

        @SuppressWarnings("unchecked")
        private static <G> Class<G> castClass() {
            return (Class<G>) IRecipeSerializer.class;
        }

    }
}
