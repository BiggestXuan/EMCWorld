package biggestxuan.emcworld.common.capability;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/18
 */

import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class EMCWorldCapability {
    @CapabilityInject(IPlayerSkillCapability.class)
    public static Capability<IPlayerSkillCapability> PLAYER_LEVEL;
    @CapabilityInject(IUtilCapability.class)
    public static Capability<IUtilCapability> UTIL;
}

