package biggestxuan.emcworld.client.network;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/03
 */

import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.network.SkillPacket.DataPack;
import biggestxuan.emcworld.common.network.UtilPacket.UtilDataPack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientHandler {
    public static void handleUtilPacket(UtilDataPack pack, Supplier<NetworkEvent.Context> ctx){
        PlayerEntity player1 = Minecraft.getInstance().player;
        if(player1 != null){
            player1.getCapability(EMCWorldCapability.UTIL).ifPresent(cap->{
                cap.setRaid(pack.isRaid());
                cap.setState(pack.getState());
                cap.setPillager(pack.getPillagerAmount());
                cap.setVillager(pack.getVillagerAmount());
                cap.setWave(pack.getWave());
                cap.setMaxWave(pack.getMaxWave());
                cap.setRaidRate(pack.getRaidRate());
                cap.setCoolDown(pack.getCd());
                cap.setDifficulty(pack.getDifficulty());
                cap.setLevel(pack.getLevel());
                cap.setArcana(pack.getArcana());
                cap.setMaxArcana(pack.getMaxArcana());
                cap.setShowArcana(pack.isShowArcana());
            });
        }
    }
    public static void handleSkillPacket(DataPack pack,Supplier<NetworkEvent.Context> ctx){
        PlayerEntity player1 = Minecraft.getInstance().player;
        if(player1 != null){
            player1.getCapability(EMCWorldCapability.PLAYER_LEVEL).ifPresent(cap->{
                cap.setLevel(pack.getLevel());
                cap.setXP(pack.getXp());
                cap.setProfession(pack.getProfession());
                cap.setMaxLevel(pack.getMaxLevel());
                cap.setModify(pack.getModify());
                cap.setSkill(pack.getSkill());
            });
        }
    }
}
