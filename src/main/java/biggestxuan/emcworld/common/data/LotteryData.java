package biggestxuan.emcworld.common.data;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/05/07
 */

import biggestxuan.emcworld.common.items.LotteryItem;
import biggestxuan.emcworld.common.utils.Lottery.LotteryUtils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;
import java.util.List;

public final class LotteryData extends WorldSavedData {
    static String name = "Lottery";
    private int index = 0;
    private List<Integer> num = new ArrayList<>();
    private long storedEMC = 0;
    public LotteryData() {
        super(name);
    }

    public static LotteryData getInstance(MinecraftServer server){
        ServerWorld world = server.overworld();
        DimensionSavedDataManager manager = world.getDataStorage();
        return manager.computeIfAbsent(LotteryData::new,name);
    }

    private void refreshNum(){
       num = LotteryUtils.getRandCode();
       setDirty();
    }

    public void setStoredEMC(long emc){
        storedEMC = emc;
        setDirty();
    }

    public void openLottery(){
        refreshNum();
        index++;
        setDirty();
    }

    public int getIndex() {
        return index;
    }

    public List<Integer> getNum() {
        return num;
    }

    public long getStoredEMC() {
        return storedEMC;
    }

    @Override
    public void load(CompoundNBT p_76184_1_) {
        index = p_76184_1_.getInt("index");
        num = LotteryItem.t(p_76184_1_.getIntArray("num"));
        storedEMC = p_76184_1_.getLong("storedEMC");
    }

    @Override
    public CompoundNBT save(CompoundNBT p_189551_1_) {
        p_189551_1_.putInt("index",index);
        p_189551_1_.putIntArray("num",num);
        p_189551_1_.putLong("storedEMC",storedEMC);
        return p_189551_1_;
    }
}
