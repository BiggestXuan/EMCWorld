package biggestxuan.emcworld.common.data;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/21
 */

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class DifficultyData extends WorldSavedData {
    private static final String NAME = "difficulty";
    private double difficulty = 3;

    public DifficultyData() {
        super(NAME);
    }

    public void putDifficulty(double difficulty){
        this.difficulty = difficulty;
        setDirty();
    }

    public double getDifficulty(){
        return this.difficulty;
    }

    public static DifficultyData getInstance(World world) {
        if(world.isClientSide){
            throw new RuntimeException();
        }
        ServerWorld serverWorld = world.getServer().overworld();
        DimensionSavedDataManager data = serverWorld.getDataStorage();
        return data.computeIfAbsent(DifficultyData::new,NAME);
    }

    @Override
    public void load(CompoundNBT p_76184_1_) {
        this.difficulty = p_76184_1_.getDouble("difficulty");
    }

    @Override
    public CompoundNBT save(CompoundNBT p_189551_1_) {
        p_189551_1_.putDouble("difficulty",this.difficulty);
        return p_189551_1_;
    }
}
