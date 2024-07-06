package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/01
 */

import biggestxuan.emcworld.EMCWorld;
import mekanism.api.chemical.infuse.InfuseType;
import mekanism.common.registration.impl.InfuseTypeDeferredRegister;
import mekanism.common.registration.impl.InfuseTypeRegistryObject;

public class EWInfuseTypes {
    private EWInfuseTypes(){}

    public static final InfuseTypeDeferredRegister INFUSE_TYPES = new InfuseTypeDeferredRegister(EMCWorld.MODID);

    public static final InfuseTypeRegistryObject<InfuseType> IRON = INFUSE_TYPES.register("iron",0xe6dbd3);
    public static final InfuseTypeRegistryObject<InfuseType> NICKEL = INFUSE_TYPES.register("nickel",0x6d848d);
    public static final InfuseTypeRegistryObject<InfuseType> SILVER = INFUSE_TYPES.register("silver",0xf8f8ff);
    public static final InfuseTypeRegistryObject<InfuseType> ENDER = INFUSE_TYPES.register("ender",0x0a2f56);
    public static final InfuseTypeRegistryObject<InfuseType> GOBBER = INFUSE_TYPES.register("gobber",0x4c6799);
    public static final InfuseTypeRegistryObject<InfuseType> HELLFORGED = INFUSE_TYPES.register("hellforged",0xa0ddd1);
    public static final InfuseTypeRegistryObject<InfuseType> ICE = INFUSE_TYPES.register("ice",0x00eeee);

}
