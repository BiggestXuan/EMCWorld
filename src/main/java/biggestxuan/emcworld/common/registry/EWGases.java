package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/07/28
 */

import biggestxuan.emcworld.EMCWorld;
import mekanism.api.chemical.gas.Gas;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;

public class EWGases {
    private EWGases(){}

    public static final GasDeferredRegister GASES = new GasDeferredRegister(EMCWorld.MODID);

    public static final GasRegistryObject<Gas> END_AIR = GASES.register("end_air",0x202932);
    public static final GasRegistryObject<Gas> AIR = GASES.register("air",0xd0e3f6);
    public static final GasRegistryObject<Gas> NITROGEN = GASES.register("nitrogen",0xc1c7cd);
    public static final GasRegistryObject<Gas> AMMONIA = GASES.register("ammonia",0x8B4513);
    public static final GasRegistryObject<Gas> HYDROGEN_CYANIDE = GASES.register("hydrogen_cyanide",0xf0ffff);
    public static final GasRegistryObject<Gas> SODIUM_CYANIDE = GASES.register("sodium_cyanide",0xfffafa);
    public static final GasRegistryObject<Gas> COSMIC_FLOW = GASES.register("cosmic_flow",0x9a32cd);
    public static final GasRegistryObject<Gas> NO_SHAPE_VOID = GASES.register("no_shape_void",0x009093);
    public static final GasRegistryObject<Gas> CONDENSATION_VOID = GASES.register("condensation_void",0x074756);
    public static final GasRegistryObject<Gas> STABLE_VOID = GASES.register("stable_void",0x062d36);
}
