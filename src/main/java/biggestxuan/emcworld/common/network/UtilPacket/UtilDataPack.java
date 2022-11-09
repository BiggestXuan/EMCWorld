package biggestxuan.emcworld.common.network.UtilPacket;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/17
 */

import biggestxuan.emcworld.client.network.ClientHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class UtilDataPack {
    private final boolean isRaid;
    private final int state;
    private final int pillagerAmount;
    private final int villagerAmount;
    private final int wave;
    private final int maxWave;
    private final float raidRate;
    private final long cd;
    private final double difficulty;
    private final int level;

    public UtilDataPack(boolean isRaid, int state, int pillagerAmount, int villagerAmount,int wave,int maxWave,float raidRate,long cd,double difficulty,int level){
        this.isRaid = isRaid;
        this.state = state;
        this.pillagerAmount = pillagerAmount;
        this.villagerAmount = villagerAmount;
        this.wave = wave;
        this.maxWave = maxWave;
        this.raidRate = raidRate;
        this.cd = cd;
        this.difficulty = difficulty;
        this.level = level;
    }

    public UtilDataPack(PacketBuffer buffer){
        isRaid = buffer.readBoolean();
        state = buffer.readInt();
        pillagerAmount = buffer.readInt();
        villagerAmount = buffer.readInt();
        wave = buffer.readInt();
        maxWave = buffer.readInt();
        raidRate = buffer.readFloat();
        cd = buffer.readLong();
        difficulty = buffer.readDouble();
        level = buffer.readInt();
    }

    public void encode(PacketBuffer buffer){
        buffer.writeBoolean(isRaid);
        buffer.writeInt(state);
        buffer.writeInt(pillagerAmount);
        buffer.writeInt(villagerAmount);
        buffer.writeInt(wave);
        buffer.writeInt(maxWave);
        buffer.writeFloat(raidRate);
        buffer.writeLong(cd);
        buffer.writeDouble(difficulty);
        buffer.writeInt(level);
    }

    public void handle(Supplier<NetworkEvent.Context> context){
        NetworkEvent.Context c = context.get();
        c.enqueueWork(()->{
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()-> ClientHandler.handleUtilPacket(this,context));
        });
        c.setPacketHandled(true);
    }

    public int getLevel() {
        return level;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public boolean isRaid() {
        return isRaid;
    }

    public float getRaidRate() {
        return raidRate;
    }

    public int getMaxWave() {
        return maxWave;
    }

    public int getPillagerAmount() {
        return pillagerAmount;
    }

    public int getState() {
        return state;
    }

    public int getVillagerAmount() {
        return villagerAmount;
    }

    public int getWave() {
        return wave;
    }

    public long getCd() {
        return cd;
    }
}

