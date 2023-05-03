package biggestxuan.emcworld.api.capability;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/12
 */

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IEntityUtilCapability extends INBTSerializable<CompoundNBT> {
    boolean isRaidEntity();

    void setRaidEntity();

    void setProjectileCostRate(double rate);

    double getProjectileCostRate();
}
