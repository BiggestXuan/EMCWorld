package biggestxuan.emcworld.api.capability;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/26
 */

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IPlayerSkillCapability extends INBTSerializable<CompoundNBT> {
    int getLevel();

    void setLevel(int level);

    void addLevel();

    int getMaxLevel();

    void setMaxLevel(int level);

    int getXP();

    void addXP(int xp);

    int getProfession();

    void setProfession(int profession);

    void setXP(int xp);

    int[] getSkills();

    void setSkill(int[] skill);

    void setSkills(int slot,int value);

    void setModify(int modify);

    int getModify();

    long getCostEMC();

    void setCostEMC(long emc);
}
