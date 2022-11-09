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
            FLUIDS.register("air", FluidAttributes.builder(Mekanism.rl("liquid/liquid"),
                    Mekanism.rl("liquid/liquid_flow")).temperature(100).color(0xFFD0E3F6));
}
