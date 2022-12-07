package biggestxuan.emcworld.api.capability;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IUtilCapability extends INBTSerializable<CompoundNBT> {
    void setLevel(int level);

    int getLevel();

    long getCoolDown();

    void setCoolDown(long tick);

    int getTimer();

    void setTimer(int timer);

    boolean isRaid();

    void setRaid(boolean raid);

    int getWave();

    int getMaxWave();

    void setWave(int wave);

    void setMaxWave(int wave);

    int getState();

    void setState(int state);

    int getVillager();

    void setVillager(int amount);

    int getPillager();

    void setPillager(int amount);

    int getF1();

    int getF2();

    int getF3();

    int getF4();

    void setF1(int year);

    void setF2(int year);

    void setF3(int year);

    void setF4(int year);

    float getRaidDamage();

    void setRaidDamage(float damage);

    void addRaidDamage(float damage);

    boolean hasBeenDisplayDamage();

    void setDisplayDamage(boolean state);

    float getRaidRate();

    void setRaidRate(float raidRate);

    void setDifficulty(double diff);

    double getDifficulty();

    void setModify(boolean modify);

    boolean getDiffModify();

    void setLogAmount(int amount);

    int getLogAmount();

    float getSpeed();

    void setSpeed(float speed);

    boolean share();

    void setShare(boolean value);
}
