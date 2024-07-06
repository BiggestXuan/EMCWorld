package biggestxuan.emcworld.common.capability.PlayerLevel;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.nbt.CompoundNBT;

public class PlayerLevelCapability implements IPlayerSkillCapability {

    private int level;
    private int xp;
    private int maxLevel;
    private int profession;
    private int[] skills;
    private int modify;
    private long costEMC;

    public PlayerLevelCapability(){
        this.maxLevel = 10;
        this.level = 1;
        this.xp = 0;
        this.profession = 0;
        this.skills = new int[44];
        this.modify = 0;
        this.costEMC = 0;
    }

    @Override
    public long getCostEMC() {
        return this.costEMC;
    }

    @Override
    public void setCostEMC(long emc) {
        this.costEMC = emc;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void addLevel() {
        if(this.profession != 0){
            addXP(MathUtils.getNeedXPToLevel(getLevel()));
        }
    }

    private void addALevel(){
        this.level++;
    }

    @Override
    public int getMaxLevel() {
        return this.maxLevel;
    }

    @Override
    public void setMaxLevel(int level) {
        this.maxLevel = level;
    }

    @Override
    public int getXP() {
        if(this.xp < 0){
            this.xp = 0;
        }
        return xp;
    }

    @Override
    public void addXP(int xp) {
        if(profession == 0 || level >= maxLevel) {
            return;
        }
        int need = MathUtils.getNeedXPToLevel(this.level);
        this.xp += xp;
        if(this.xp < need) return;
        while(this.xp >=need){
            this.xp -= need;
            addALevel();
            if(level >= maxLevel){
                this.xp = 0;
                break;
            }
            need = MathUtils.getNeedXPToLevel(this.level);
        }
    }

    @Override
    public int getProfession() {
        return profession;
    }

    @Override
    public void setProfession(int profession) {
        this.profession = profession;
    }

    @Override
    public void setXP(int xp) {
        this.xp = xp;
    }

    @Override
    public int[] getSkills(){
        return this.skills;
    }

    @Override
    public void setSkills(int slot, int value) {
        this.skills[slot] = value;
    }

    @Override
    public void setModify(int modify) {
        this.modify = modify;
    }

    @Override
    public int getModify() {
        return this.modify;
    }

    @Override
    public void setSkill(int[] skill){
        this.skills = skill;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("maxLevel",this.maxLevel);
        tag.putInt("level",this.level);
        tag.putInt("xp",this.xp);
        tag.putInt("modify",this.modify);
        tag.putInt("profession",this.profession);
        tag.putIntArray("skills",this.skills);
        tag.putLong("costEMC",this.costEMC);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.maxLevel = nbt.getInt("maxLevel");
        this.level = nbt.getInt("level");
        this.xp = nbt.getInt("xp");
        this.profession = nbt.getInt("profession");
        this.skills = nbt.getIntArray("skills");
        this.modify = nbt.getInt("modify");
        this.costEMC = nbt.getLong("costEMC");
    }
}
