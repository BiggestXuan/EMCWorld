package biggestxuan.emcworld.common.capability.Util;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import biggestxuan.emcworld.api.capability.IUtilCapability;
import net.minecraft.nbt.CompoundNBT;

public class UtilCapability implements IUtilCapability {
    private int SponsorLevel;
    private long CoolDown;
    private int timer;
    private boolean isRaid;
    private int state;
    private int pillagerAmount;
    private int villagerAmount;
    private int wave;
    private int maxWave;
    private int F1;
    private int F2;
    private int F3;
    private int F4;
    private float raidDamage;
    private boolean displayDamage;
    private float raidRate;
    private double difficulty;
    private boolean modifyDifficulty;
    private int logAmount;
    private float speed;
    private boolean share;

    public UtilCapability(){
        this.SponsorLevel = 0;
        this.CoolDown = 0;
        this.timer = 0;
        this.isRaid = false;
        this.state = 1;
        this.pillagerAmount = 0;
        this.villagerAmount = 0;
        this.wave = 0;
        this.maxWave = 0;
        this.F1 = 0;
        this.F2 = 0;
        this.F3 = 0;
        this.F4 = 0;
        this.raidDamage = 0;
        this.displayDamage = false;
        this.raidRate = 1f;
        this.difficulty = 0;
        this.modifyDifficulty = false;
        this.speed = 0f;
    }

    @Override
    public void setLevel(int level) {
        this.SponsorLevel = level;
    }

    @Override
    public int getLevel() {
        return this.SponsorLevel;
    }

    @Override
    public long getCoolDown() {
        return this.CoolDown;
    }

    @Override
    public void setCoolDown(long tick) {
        this.CoolDown = tick;
    }

    @Override
    public int getTimer() {
        return this.timer;
    }

    @Override
    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public boolean isRaid() {
        return this.isRaid;
    }

    @Override
    public void setRaid(boolean raid) {
        this.isRaid = raid;
    }

    @Override
    public int getWave() {
        return this.wave;
    }

    @Override
    public int getMaxWave() {
        return this.maxWave;
    }

    @Override
    public void setWave(int wave) {
        this.wave = wave;
    }

    @Override
    public void setMaxWave(int wave) {
        this.maxWave = wave;
    }

    @Override
    public int getState() {
        return this.state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int getVillager() {
        return this.villagerAmount;
    }

    @Override
    public void setVillager(int amount) {
        this.villagerAmount = amount;
    }

    @Override
    public int getPillager() {
        return this.pillagerAmount;
    }

    @Override
    public void setPillager(int amount) {
        this.pillagerAmount = amount;
    }

    @Override
    public int getF1() {
        return this.F1;
    }

    @Override
    public int getF2() {
        return this.F2;
    }

    @Override
    public int getF3() {
        return this.F3;
    }

    @Override
    public int getF4() {
        return this.F4;
    }

    @Override
    public void setF1(int year) {
        this.F1 = year;
    }

    @Override
    public void setF2(int year) {
        this.F2 = year;
    }

    @Override
    public void setF3(int year) {
        this.F3 = year;
    }

    @Override
    public void setF4(int year) {
        this.F4 = year;
    }

    @Override
    public float getRaidDamage() {
        return this.raidDamage;
    }

    @Override
    public void setRaidDamage(float damage) {
        this.raidDamage = damage;
    }

    @Override
    public void addRaidDamage(float damage) {
        setRaidDamage(getRaidDamage()+damage);
    }

    @Override
    public boolean hasBeenDisplayDamage() {
        return this.displayDamage;
    }

    @Override
    public void setDisplayDamage(boolean state) {
        this.displayDamage = state;
    }

    @Override
    public float getRaidRate() {
        return this.raidRate;
    }

    @Override
    public void setRaidRate(float raidRate) {
        this.raidRate = raidRate;
    }

    @Override
    public void setDifficulty(double diff) {
        this.difficulty = diff;
    }

    @Override
    public double getDifficulty() {
        return this.difficulty;
    }

    @Override
    public void setModify(boolean modify) {
        this.modifyDifficulty = modify;
    }

    @Override
    public boolean getDiffModify() {
        return this.modifyDifficulty;
    }

    @Override
    public void setLogAmount(int amount) {
        this.logAmount = amount;
    }

    @Override
    public int getLogAmount() {
        return this.logAmount;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public boolean share() {
        return share;
    }

    @Override
    public void setShare(boolean value) {
        share = value;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("sponsorLevel",this.SponsorLevel);
        tag.putLong("CoolDown",this.CoolDown);
        tag.putInt("timer",this.timer);
        tag.putBoolean("isRaid",this.isRaid);
        tag.putInt("raid_state",this.state);
        tag.putInt("pillager_amount",this.pillagerAmount);
        tag.putInt("villager_amount",this.villagerAmount);
        tag.putInt("wave",this.wave);
        tag.putInt("maxWave",this.maxWave);
        tag.putInt("f1",this.F1);
        tag.putInt("f2",this.F2);
        tag.putInt("f3",this.F3);
        tag.putInt("f4",this.F4);
        tag.putFloat("raid_damage",this.raidDamage);
        tag.putBoolean("display",this.displayDamage);
        tag.putFloat("raid_rate",this.raidRate);
        tag.putDouble("difficulty",this.difficulty);
        tag.putBoolean("modi",this.modifyDifficulty);
        tag.putInt("log",this.logAmount);
        tag.putFloat("speed",this.speed);
        tag.putBoolean("share",share);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.SponsorLevel = nbt.getInt("sponsorLevel");
        this.CoolDown = nbt.getLong("CoolDown");
        this.timer = nbt.getInt("timer");
        this.isRaid = nbt.getBoolean("isRaid");
        this.state = nbt.getInt("raid_state");
        this.pillagerAmount = nbt.getInt("pillager_amount");
        this.villagerAmount = nbt.getInt("villager_amount");
        this.wave = nbt.getInt("wave");
        this.maxWave = nbt.getInt("maxWave");
        this.F1 = nbt.getInt("f1");
        this.F2 = nbt.getInt("f2");
        this.F3 = nbt.getInt("f3");
        this.F4 = nbt.getInt("f4");
        this.raidDamage = nbt.getFloat("raid_damage");
        this.displayDamage = nbt.getBoolean("display");
        this.raidRate = nbt.getFloat("raid_rate");
        this.difficulty = nbt.getDouble("difficulty");
        this.modifyDifficulty = nbt.getBoolean("modi");
        this.logAmount = nbt.getInt("log");
        this.speed = nbt.getFloat("speed");
        this.share = nbt.getBoolean("emc");
    }
}
