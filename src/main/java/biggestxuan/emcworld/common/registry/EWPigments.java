package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/15
 */

import biggestxuan.emcworld.EMCWorld;
import mekanism.api.chemical.pigment.Pigment;
import mekanism.common.registration.impl.PigmentDeferredRegister;
import mekanism.common.registration.impl.PigmentRegistryObject;

public class EWPigments {
    private EWPigments(){}

    public static PigmentDeferredRegister PIGMENTS = new PigmentDeferredRegister(EMCWorld.MODID);

    public static PigmentRegistryObject<Pigment> BX  = PIGMENTS.register("bx",0xd7c6f1);

}
