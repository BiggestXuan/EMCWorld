package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/30
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.container.*;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EWContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, EMCWorld.MODID);

    public static final RegistryObject<ContainerType<AdvancedUpdateContainer>> advancedUpdateContainer = registerMenuType(AdvancedUpdateContainer::new,"advanced_update_container");
    public static final RegistryObject<ContainerType<InfuserContainer>> infuserContainer = registerMenuType(InfuserContainer::new, "infuser_container");
    public static final RegistryObject<ContainerType<WeaponUpgradeContainer>> weaponUpgradeContainer = registerMenuType(WeaponUpgradeContainer::new, "weapon_upgrade_container");
    public static final RegistryObject<ContainerType<GemstoneContainer>> gemstoneContainer = registerMenuType(GemstoneContainer::new, "gemstone_container");
    public static final RegistryObject<ContainerType<SteelFurnaceCoreContainer>> steelFurnaceContainer = registerMenuType(SteelFurnaceCoreContainer::new, "steel_furnace_container");
    public static final RegistryObject<ContainerType<PrefixContainer>> prefixContainer = registerMenuType(PrefixContainer::new, "prefix_container");
    public static final RegistryObject<ContainerType<SuperEMCContainer>> superEMCContainer = registerMenuType(SuperEMCContainer::new, "super_emc_container");
    public static final RegistryObject<ContainerType<TopCoreContainer>> topCoreContainer = registerMenuType(TopCoreContainer::new, "top_core_container");
    public static final RegistryObject<ContainerType<EMCOreCoreContainer>> emcOreCoreContainer = registerMenuType(EMCOreCoreContainer::new,"emc_core_container");
    public static final RegistryObject<ContainerType<PersonalLinkContainer>> personalLinkContainer = registerMenuType(PersonalLinkContainer::new,"personal_link_container");
    public static final RegistryObject<ContainerType<EMCCoreContainer.Assembler>> emcCoreAssemblerContainer = registerMenuType(EMCCoreContainer.Assembler::new,"emc_core_assembler_container");
    public static final RegistryObject<ContainerType<EMCCoreContainer.Puller>> emcCorePullerContainer = registerMenuType(EMCCoreContainer.Puller::new,"emc_core_puller_container");
    public static final RegistryObject<ContainerType<EMCCoreContainer.Generator>> emcCoreGeneratorContainer = registerMenuType(EMCCoreContainer.Generator::new,"emc_core_generator_container");
    public static final RegistryObject<ContainerType<EMCCoreContainer.Puncher>> emcCorePuncherContainer = registerMenuType(EMCCoreContainer.Puncher::new,"emc_core_puncher_container");

    private static <T extends Container> RegistryObject<ContainerType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return CONTAINERS.register(name, () -> IForgeContainerType.create(factory));
    }

}
