package biggestxuan.emcworld.common.capability.PlayerLevel;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerLevelCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private IPlayerSkillCapability playerLevelCapability;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side){
        return cap == EMCWorldCapability.PLAYER_LEVEL ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    @Nonnull
    IPlayerSkillCapability getOrCreateCapability() {
        if (playerLevelCapability == null) {
            this.playerLevelCapability = new PlayerLevelCapability();
        }
        return this.playerLevelCapability;
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
