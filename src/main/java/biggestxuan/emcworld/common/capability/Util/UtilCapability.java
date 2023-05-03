package biggestxuan.emcworld.common.capability.Util;

/***
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
    private float arcana;
    private float maxArcana;
    private boolean showArcana;
    private double SHDifficulty;
    private float maxShield;
    private float shield;
    private boolean lastShield;
    private int gaiaPlayer;
    private float healPreTick;
    private int healTick;
    private int pickMode;
    private int netherTick;
    private int displayIndex;
    private boolean liveMode;
    private boolean online;

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
        this.arcana = 0f;
        this.maxArcana = 200f;
        this.showArcana = false;
        this.SHDifficulty = 0;
        this.maxShield = 0f;
        this.shield = 0f;
        this.lastShield = false;
        this.gaiaPlayer = 0;
        this.healPreTick = 0f;
        this.healTick = 0;
        this.pickMode = 0;
        this.netherTick = 0;
        this.displayIndex = 0;
        this.liveMode = false;
        this.online = true;
    }

    @Override
    public boolean getOnline() {
        return online;
    }

    @Override
    public void setOnline(boolean value) {
        online = value;
    }

    @Override
    public boolean getLiveMode() {
        return liveMode;
    }

    @Override
    public void setLiveMode(boolean value) {
        liveMode = value;
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
    public int getPickMode() {
        return pickMode;
    }

    @Override
    public void setPickMode(int mode) {
        pickMode = mode;
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
    public int getNetherTick() {
        return netherTick;
    }

    @Override
    public int getDisplayIndex() {
        return displayIndex;
    }

    @Override
    public void setDisplayIndex(int index) {
        displayIndex = index;
    }

    @Override
    public void setNetherTick(int tick) {
        netherTick = tick;
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
    public float getArcana() {
        return arcana;
    }

    @Override
    public void setArcana(float value) {
        arcana = value;
    }

    @Override
    public float getMaxArcana() {
        return maxArcana;
    }

    @Override
    public void setMaxArcana(float value) {
        this.maxArcana = value;
    }

    @Override
    public boolean showArcana() {
        return showArcana;
    }

    @Override
    public void setShowArcana(boolean value) {
        showArcana = value;
    }

    @Override
    public double getSHDifficulty() {
        return SHDifficulty;
    }

    @Override
    public void setSHDifficulty(double value) {
        SHDifficulty = value;
    }

    @Override
    public float getMaxShield() {
        return maxShield;
    }

    @Override
    public float getShield() {
        return shield;
    }

    @Override
    public void setMaxShield(float value) {
        maxShield = value;
    }

    @Override
    public void setShield(float value) {
        shield = value;
    }

    @Override
    public boolean isLastShield() {
        return lastShield;
    }

    @Override
    public void setLastShield(boolean value) {
        lastShield = value;
    }

    @Override
    public int gaiaPlayer() {
        return gaiaPlayer;
    }

    @Override
    public void setGaiaPlayer(int amt) {
        gaiaPlayer = amt;
    }

    @Override
    public float getHealPreTick() {
        return healPreTick;
    }

    @Override
    public void setHealPreTick(float amt) {
        healPreTick = amt;
    }

    @Override
    public int getHealTick() {
        return healTick;
    }

    @Override
    public void setHealTick(int amt) {
        healTick = amt;
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
        tag.putFloat("arcana",arcana);
        tag.putFloat("maxArcana",maxArcana);
        tag.putBoolean("showArcana",showArcana);
        tag.putDouble("sh_difficulty",SHDifficulty);
        tag.putFloat("shield",shield);
        tag.putFloat("maxShield",maxShield);
        tag.putBoolean("last_shield",lastShield);
        tag.putInt("gaia_player",gaiaPlayer);
        tag.putFloat("healPreTick",healPreTick);
        tag.putInt("healTick",healTick);
        tag.putInt("pickMode",pickMode);
        tag.putInt("netherTick",netherTick);
        tag.putInt("displayIndex",displayIndex);
        tag.putBoolean("liveMode",liveMode);
        tag.putBoolean("online",online);
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
        this.arcana = nbt.getFloat("arcana");
        this.maxArcana = nbt.getFloat("maxArcana");
        this.showArcana = nbt.getBoolean("showArcana");
        this.SHDifficulty = nbt.getFloat("sh_difficulty");
        this.shield = nbt.getFloat("shield");
        this.maxShield = nbt.getFloat("maxShield");
        this.lastShield = nbt.getBoolean("last_shield");
        this.gaiaPlayer = nbt.getInt("gaia_player");
        this.healPreTick = nbt.getFloat("healPreTick");
        this.healTick = nbt.getInt("healTick");
        this.pickMode = nbt.getInt("pickMode");
        this.netherTick = nbt.getInt("netherTick");
        this.displayIndex = nbt.getInt("displayIndex");
        this.liveMode = nbt.getBoolean("liveMode");
        this.online = nbt.getBoolean("online");
    }
}
