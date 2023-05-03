package biggestxuan.emcworld.common.data;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/06
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class ShareEMCData extends WorldSavedData {
    private static final String NAME = "SHARE_EMC";

    private long EMC = 0;

    public ShareEMCData() {
        super(NAME);
    }

    public void addEMC(long value){
        setEMC(getEMC()+value);
    }

    public void setEMC(long value){
        if(value >= EMCWorld.MAX_EMC){
            EMC = EMCWorld.MAX_EMC;
        }
        if(value <= 0){
            EMC = 0;
        }
        EMC = value;
        setDirty();
    }

    public long getEMC() {
        return EMC;
    }

    public static ShareEMCData getInstance(World world){
        if(world.isClientSide){
            throw new RuntimeException();
        }
        MinecraftServer server = world.getServer();
        DimensionSavedDataManager data = server.overworld().getDataStorage();
        return data.computeIfAbsent(ShareEMCData::new,NAME);
    }

    @Override
    public void load(CompoundNBT p_76184_1_) {
        EMC = p_76184_1_.getLong("emc");
    }

    @Override
    public CompoundNBT save(CompoundNBT p_189551_1_) {
        p_189551_1_.putLong("emc",EMC);
        return p_189551_1_;
    }
}
