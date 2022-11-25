package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/30
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.blocks.AdvancedUpdateBlock.AdvancedUpdateContainer;
import biggestxuan.emcworld.common.blocks.GemstoneBlock.GemstoneContainer;
import biggestxuan.emcworld.common.blocks.InfuserBlock.InfuserContainer;
import biggestxuan.emcworld.common.blocks.WeaponUpgradeBlock.WeaponUpgradeContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EWContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, EMCWorld.MODID);

    public static final RegistryObject<ContainerType<AdvancedUpdateContainer>> advancedUpdateContainer =
            registerMenuType(AdvancedUpdateContainer::new,"advanced_update_container");

    public static final RegistryObject<ContainerType<InfuserContainer>> infuserContainer =
            registerMenuType(InfuserContainer::new, "infuser_container");

    public static final RegistryObject<ContainerType<WeaponUpgradeContainer>> weaponUpgradeContainer =
            registerMenuType(WeaponUpgradeContainer::new, "weapon_upgrade_container");

    public static final RegistryObject<ContainerType<GemstoneContainer>> gemstoneContainer =
            registerMenuType(GemstoneContainer::new, "gemstone_container");


    private static <T extends Container> RegistryObject<ContainerType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return CONTAINERS.register(name, () -> IForgeContainerType.create(factory));
    }

}
