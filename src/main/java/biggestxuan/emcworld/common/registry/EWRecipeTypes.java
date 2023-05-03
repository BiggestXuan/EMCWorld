package biggestxuan.emcworld.common.registry;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/15
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.recipes.EMCStageLimit;
import biggestxuan.emcworld.common.recipes.InfuserRecipe;
import biggestxuan.emcworld.common.recipes.SteelFurnaceRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EWRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EMCWorld.MODID);

    public static final RegistryObject<IRecipeSerializer<?>> INFUSER_RECIPE = RECIPES.register("infuser",() -> InfuserRecipe.Serializer.INSTANCE);
    public static final RegistryObject<IRecipeSerializer<?>> STEEL_FURNACE_RECIPE = RECIPES.register("steel_furnace",() -> SteelFurnaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<IRecipeSerializer<?>> EMC_STAGE_LIMIT_RECIPE = RECIPES.register("emc_stage_limit",() -> EMCStageLimit.EMCStageLimitSerializer.serializer);

    public static <T extends IRecipe<?>> IRecipeType<T> register(final String p_222147_0_) {
        return Registry.register(Registry.RECIPE_TYPE, EMCWorld.rl(p_222147_0_), new IRecipeType<T>() {
            public String toString() {
                return p_222147_0_;
            }
        });
    }
}
