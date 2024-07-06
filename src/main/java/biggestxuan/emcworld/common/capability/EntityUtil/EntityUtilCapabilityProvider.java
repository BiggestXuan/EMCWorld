package biggestxuan.emcworld.common.capability.EntityUtil;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import biggestxuan.emcworld.api.capability.IEntityUtilCapability;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityUtilCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private IEntityUtilCapability cap;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side){
        return cap == EMCWorldCapability.ENTITY_UTIL ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    @Nonnull
    IEntityUtilCapability getOrCreateCapability() {
        if (cap == null) {
            this.cap = new EntityUtilCapability();
        }
        return this.cap;
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
