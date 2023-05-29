package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/28
 */

import biggestxuan.emcworld.EMCWorld;
import mekanism.common.Mekanism;
import mekanism.common.registration.impl.FluidDeferredRegister;
import mekanism.common.registration.impl.FluidRegistryObject;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BucketItem;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class EWFluids {
    private EWFluids(){}

    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(EMCWorld.MODID);

    public static final FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> AIR =
            r("air",0xFFD0E3F6);

    public static final FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> SODIUM_CYANIDE =
            r("sodium_cyanide",0xFFFFFAFA);


    private static FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> r(String name,int color,int temp){
        return FLUIDS.register(name,FluidAttributes.builder(Mekanism.rl("liquid/liquid"),Mekanism.rl("liquid/liquid_flow")).temperature(temp).color(color));
    }

    private static FluidRegistryObject<ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, FlowingFluidBlock, BucketItem> r(String name,int color){
        return r(name,color,100);
    }
}
