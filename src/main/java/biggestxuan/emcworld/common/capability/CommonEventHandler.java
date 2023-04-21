package biggestxuan.emcworld.common.capability;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/18
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.capability.EntityUtil.EntityUtilCapabilityProvider;
import biggestxuan.emcworld.common.capability.PlayerLevel.PlayerLevelCapabilityProvider;
import biggestxuan.emcworld.common.capability.Util.UtilCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class CommonEventHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            event.addCapability(EMCWorld.rl("player_level"), new PlayerLevelCapabilityProvider());
            event.addCapability(EMCWorld.rl("utils"),new UtilCapabilityProvider());
        }
        else{
            event.addCapability(EMCWorld.rl("entity_util"),new EntityUtilCapabilityProvider());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        LazyOptional<IUtilCapability> oldUtilCap = event.getOriginal().getCapability(EMCWorldCapability.UTIL);
        LazyOptional<IUtilCapability> newUtilCap = event.getPlayer().getCapability(EMCWorldCapability.UTIL);
        LazyOptional<IPlayerSkillCapability> oldLevelCap = event.getOriginal().getCapability(EMCWorldCapability.PLAYER_LEVEL);
        LazyOptional<IPlayerSkillCapability> newLevelCap = event.getPlayer().getCapability(EMCWorldCapability.PLAYER_LEVEL);
        if (oldUtilCap.isPresent() && newUtilCap.isPresent()) {
            newUtilCap.ifPresent((newCap) -> oldUtilCap.ifPresent((oldCap) -> newCap.deserializeNBT(oldCap.serializeNBT())));
        }
        if (oldLevelCap.isPresent() && newLevelCap.isPresent()) {
            newLevelCap.ifPresent((newCap) -> oldLevelCap.ifPresent((oldCap) -> newCap.deserializeNBT(oldCap.serializeNBT())));
        }
    }
}
