package biggestxuan.emcworld.common.registry;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.enchantments.EMCLootingEnchantment;
import biggestxuan.emcworld.common.enchantments.EMCReduceEnchantment;
import biggestxuan.emcworld.common.enchantments.EMCRepairEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/08/01
 */

@EMCWorldSince("1.0.3")
public class EWEnchantments {

    public static final DeferredRegister<Enchantment> EN = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EMCWorld.MODID);

    public static final RegistryObject<Enchantment> EMC_REPAIR = EN.register("emc_repair", EMCRepairEnchantment::new);
    public static final RegistryObject<Enchantment> EMC_REDUCE = EN.register("emc_reduce", EMCReduceEnchantment::new);
    public static final RegistryObject<Enchantment> EMC_LOOTING = EN.register("emc_looting", EMCLootingEnchantment::new);
}
