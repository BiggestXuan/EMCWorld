package biggestxuan.emcworld.common.registry;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/16
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class EWSounds {
    public static final DeferredRegister<SoundEvent> SOUND = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EMCWorld.MODID);

    public static final RegistryObject<SoundEvent> CXK_DEATH = register("cxk_death");
    public static final RegistryObject<SoundEvent> CXK_ADVENTURE = SOUND.register("cxk_adventure",() -> new SoundEvent(EMCWorld.rl("cxk_adventure")));
    public static final RegistryObject<SoundEvent> CXK_RICH = SOUND.register("cxk_rich",() -> new SoundEvent(EMCWorld.rl("cxk_rich")));
    public static final RegistryObject<SoundEvent> CXK_RABBIT = SOUND.register("cxk_rabbit",() -> new SoundEvent(EMCWorld.rl("cxk_rabbit")));
    public static final RegistryObject<SoundEvent> NONE = SOUND.register("null",() -> new SoundEvent(EMCWorld.rl("null")));

    private static RegistryObject<SoundEvent> register(String name){
        return SOUND.register(name,() -> new SoundEvent(EMCWorld.rl(name)));
    }
}
