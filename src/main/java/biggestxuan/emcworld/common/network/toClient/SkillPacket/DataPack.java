package biggestxuan.emcworld.common.network.toClient.SkillPacket;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/02
 */

import biggestxuan.emcworld.common.network.ClientPacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class DataPack {
    private final int profession;
    private final int level;
    private final int xp;
    private final int maxLevel;
    private final int modify;
    private final int[] skill;

    public DataPack(int level,int xp,int profession,int maxLevel,int modify,int[] skill){
        this.level = level;
        this.xp = xp;
        this.profession = profession;
        this.maxLevel = maxLevel;
        this.modify = modify;
        this.skill = skill;
    }

    public DataPack(PacketBuffer buffer){
        level = buffer.readInt();
        xp = buffer.readInt();
        profession = buffer.readInt();
        maxLevel = buffer.readInt();
        modify = buffer.readInt();
        skill = buffer.readVarIntArray();
    }

    public void encode(PacketBuffer buffer){
        buffer.writeInt(level);
        buffer.writeInt(xp);
        buffer.writeInt(profession);
        buffer.writeInt(maxLevel);
        buffer.writeInt(modify);
        buffer.writeVarIntArray(skill);
    }

    public void handle(Supplier<NetworkEvent.Context> context){
        NetworkEvent.Context c = context.get();
        c.enqueueWork(()->{
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()-> ClientPacketHandler.handleSkillPacket(this,context));
        });
        c.setPacketHandled(true);
    }

    public int getLevel() {
        return level;
    }

    public int getModify() {
        return modify;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getProfession() {
        return profession;
    }

    public int getXp() {
        return xp;
    }

    public int[] getSkill() {
        return skill;
    }
}
