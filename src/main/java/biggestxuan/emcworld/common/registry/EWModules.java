package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/15
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.compact.Mekanism.Module.EMCProtect.EnergyProtectModuleUnit;
import biggestxuan.emcworld.common.compact.Mekanism.Module.Infinity.InfinityModuleUnit;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;
import net.minecraft.item.Rarity;

public class EWModules {
    public static final ModuleDeferredRegister MODULES = new ModuleDeferredRegister(EMCWorld.MODID);

    public static final ModuleRegistryObject<EnergyProtectModuleUnit> ENERGY_PROTECT_MODULE = MODULES.registerLegacy(
            "energy_protect_module",EnergyProtectModuleUnit::new, EWItems.ENERGY_PROTECT_MODULE::get, builder -> builder.maxStackSize(1).noDisable().rarity(Rarity.EPIC)
    );
    public static final ModuleRegistryObject<InfinityModuleUnit> INFINITY_MODULE_UNIT_MODULE = MODULES.registerLegacy(
            "infinity_module",InfinityModuleUnit::new, EWItems.INFINITY_MODULE::get, builder -> builder.maxStackSize(1).noDisable().rarity(Rarity.EPIC)
    );
}
