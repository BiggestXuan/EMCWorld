package biggestxuan.emcworld.api.recipe.ioRecipe;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/15
 */

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public abstract class BaseIOSerializer<T extends BaseIORecipe> implements IRecipeSerializer<T> {

    @Override
    public abstract T fromJson(ResourceLocation p_199425_1_, JsonObject p_199425_2_);

    @Nullable
    @Override
    public abstract T fromNetwork(ResourceLocation p_199426_1_, PacketBuffer p_199426_2_);

    @Override
    public abstract void toNetwork(PacketBuffer p_199427_1_, T p_199427_2_);

    @Override
    public abstract IRecipeSerializer<?> setRegistryName(ResourceLocation name);

    @Nullable
    @Override
    public abstract ResourceLocation getRegistryName();

    @Override
    public Class<IRecipeSerializer<?>> getRegistryType() {
        return castClass();
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> castClass() {
        return (Class<T>) IRecipeSerializer.class;
    }
}
