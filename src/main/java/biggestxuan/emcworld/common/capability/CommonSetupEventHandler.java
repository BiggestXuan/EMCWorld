package biggestxuan.emcworld.common.capability;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/18
 */

import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEventHandler {
    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> CapabilityManager.INSTANCE.register(
                IUtilCapability.class,
                new Capability.IStorage<IUtilCapability>() {
                    @Override
                    public INBT writeNBT(Capability<IUtilCapability> capability, IUtilCapability instance, Direction side) {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IUtilCapability> capability, IUtilCapability instance, Direction side, INBT nbt) {
                    }
                },
                () -> null
        ));
        event.enqueueWork(() -> CapabilityManager.INSTANCE.register(
                IPlayerSkillCapability.class,
                new Capability.IStorage<IPlayerSkillCapability>() {
                    @Override
                    public INBT writeNBT(Capability<IPlayerSkillCapability> capability, IPlayerSkillCapability instance, Direction side) {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IPlayerSkillCapability> capability, IPlayerSkillCapability instance, Direction side, INBT nbt) {
                    }
                },
                () -> null
        ));
    }
}
