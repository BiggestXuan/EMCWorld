package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/10
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.potion.*;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EWEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, EMCWorld.MODID);

    public static final RegistryObject<Effect> EMC_PROTECT = EFFECTS.register("emc_protect", EMCProtectionEffect::new);
    public static final RegistryObject<Effect> MAGIC_PROTECT = EFFECTS.register("magic_protect", MagicProtectEffect::new);
    public static final RegistryObject<Effect> EMC_BROKEN = EFFECTS.register("emc_broken", EMCBrokenEffect::new);
    public static final RegistryObject<Effect> EMC_FLAMING = EFFECTS.register("emc_flaming", EMCFlamingEffect::new);
}
