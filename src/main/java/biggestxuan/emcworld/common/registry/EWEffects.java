package biggestxuan.emcworld.common.registry;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/10
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.potion.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EWEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, EMCWorld.MODID);

    public static final RegistryObject<Effect> EMC_PROTECT = EFFECTS.register("emc_protect", EMCProtectionEffect::new);
    public static final RegistryObject<Effect> MAGIC_PROTECT = EFFECTS.register("magic_protect", MagicProtectEffect::new);
    public static final RegistryObject<Effect> EMC_BROKEN = EFFECTS.register("emc_broken", EMCBrokenEffect::new);
    public static final RegistryObject<Effect> EMC_FLAMING = EFFECTS.register("emc_flaming", EMCFlamingEffect::new);
    public static final RegistryObject<Effect> ATTACK_RANGE = EFFECTS.register("attack_range",() -> new BaseEMCWorldEffect(EffectType.BENEFICIAL,0xFFDAB9));
    public static final RegistryObject<Effect> REMOTE_DAMAGE = EFFECTS.register("remote_damage",() -> new BaseEMCWorldEffect(EffectType.BENEFICIAL,0x9AFF9A));
    public static final RegistryObject<Effect> ATTACK_SPEED = EFFECTS.register("attack_speed",() -> new BaseEMCWorldEffect(EffectType.BENEFICIAL,0x1E90FF));
    public static final RegistryObject<Effect> ACCURACY = EFFECTS.register("accuracy",() -> new BaseEMCWorldEffect(EffectType.BENEFICIAL,0xcccc00));
}
