package biggestxuan.emcworld.common.capability.EntityUtil;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/12
 */

import biggestxuan.emcworld.api.capability.IEntityUtilCapability;
import net.minecraft.nbt.CompoundNBT;

public class EntityUtilCapability implements IEntityUtilCapability {
    private boolean isRaidEntity;
    private double ProjectileCostRate;

    public EntityUtilCapability(){
        isRaidEntity = false;
        ProjectileCostRate = 1D;
    }

    @Override
    public void setProjectileCostRate(double rate) {
        ProjectileCostRate = rate;
    }

    @Override
    public double getProjectileCostRate() {
        return ProjectileCostRate;
    }

    @Override
    public boolean isRaidEntity() {
        return isRaidEntity;
    }

    @Override
    public void setRaidEntity() {
        isRaidEntity = true;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean("raid",isRaidEntity);
        nbt.putDouble("projectileRate",ProjectileCostRate);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        isRaidEntity = nbt.getBoolean("raid");
        ProjectileCostRate = nbt.getDouble("projectileRate");
    }
}
