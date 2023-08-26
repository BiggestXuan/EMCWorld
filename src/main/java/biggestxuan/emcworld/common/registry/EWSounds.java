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

public class EWSounds {
    public static final DeferredRegister<SoundEvent> SOUND = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EMCWorld.MODID);

    public static final RegistryObject<SoundEvent> CXK_DEATH = register("cxk_death");
    public static final RegistryObject<SoundEvent> CXK_ADVENTURE = SOUND.register("cxk_adventure",() -> new SoundEvent(EMCWorld.rl("cxk_adventure")));

    private static RegistryObject<SoundEvent> register(String name){
        return SOUND.register(name,() -> new SoundEvent(EMCWorld.rl(name)));
    }
}
