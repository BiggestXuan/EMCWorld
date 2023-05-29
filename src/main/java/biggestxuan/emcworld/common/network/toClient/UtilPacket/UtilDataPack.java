package biggestxuan.emcworld.common.network.toClient.UtilPacket;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/17
 */

import biggestxuan.emcworld.common.network.ClientPacketHandler;
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
    private final int[] level;
    private final float arcana;
    private final float maxArcana;
    private final boolean showArcana;
    private final double sh_difficulty;
    private final float shield;
    private final float maxShield;
    private final boolean isLastShield;
    private final int gaiaPlayer;
    private final boolean liveMode;

    public UtilDataPack(boolean isRaid, int state, int pillagerAmount, int villagerAmount,int wave,int maxWave,float raidRate,long cd,double difficulty,int[] level,float arcana,float maxArcana,boolean showArcana,double sh_difficulty,float shield,float maxShield,boolean lastShield,int gaiaPlayer,boolean liveMode){
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
        this.arcana = arcana;
        this.maxArcana = maxArcana;
        this.showArcana = showArcana;
        this.sh_difficulty = sh_difficulty;
        this.shield = shield;
        this.maxShield = maxShield;
        this.isLastShield = lastShield;
        this.gaiaPlayer = gaiaPlayer;
        this.liveMode = liveMode;
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
        level = buffer.readVarIntArray();
        arcana = buffer.readFloat();
        maxArcana = buffer.readFloat();
        showArcana = buffer.readBoolean();
        sh_difficulty = buffer.readDouble();
        shield = buffer.readFloat();
        maxShield = buffer.readFloat();
        isLastShield = buffer.readBoolean();
        gaiaPlayer = buffer.readInt();
        liveMode = buffer.readBoolean();
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
        buffer.writeVarIntArray(level);
        buffer.writeFloat(arcana);
        buffer.writeFloat(maxArcana);
        buffer.writeBoolean(showArcana);
        buffer.writeDouble(sh_difficulty);
        buffer.writeFloat(shield);
        buffer.writeFloat(maxShield);
        buffer.writeBoolean(isLastShield);
        buffer.writeInt(gaiaPlayer);
        buffer.writeBoolean(liveMode);
    }

    public void handle(Supplier<NetworkEvent.Context> context){
        NetworkEvent.Context c = context.get();
        c.enqueueWork(()-> DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()-> ClientPacketHandler.handleUtilPacket(this,context)));
        c.setPacketHandled(true);
    }

    public int[] getLevel() {
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

    public boolean isShowArcana() {
        return showArcana;
    }

    public float getArcana() {
        return arcana;
    }

    public float getMaxArcana() {
        return maxArcana;
    }

    public double getSh_difficulty() {
        return sh_difficulty;
    }

    public float getMaxShield() {
        return maxShield;
    }

    public float getShield() {
        return shield;
    }

    public boolean isLastShield() {
        return isLastShield;
    }

    public int getGaiaPlayer() {
        return gaiaPlayer;
    }

    public boolean isLiveMode() {
        return liveMode;
    }
}

