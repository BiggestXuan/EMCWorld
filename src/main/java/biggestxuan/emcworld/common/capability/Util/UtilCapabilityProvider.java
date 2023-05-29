package biggestxuan.emcworld.common.capability.Util;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/18
 */

import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class UtilCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private IUtilCapability utilCapability;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == EMCWorldCapability.UTIL ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }
    @Nonnull
    IUtilCapability getOrCreateCapability() {
        if (utilCapability == null) {
            this.utilCapability = new UtilCapability();
        }
        return this.utilCapability;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getOrCreateCapability().deserializeNBT(nbt);
    }
}
