package biggestxuan.emcworld.common.network;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/11/03
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.client.lottery.ChooseLotteryScreen;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.network.toClient.BuyLotteryClientPacket;
import biggestxuan.emcworld.common.network.toClient.ChangeRotPacket;
import biggestxuan.emcworld.common.network.toClient.SkillPacket.DataPack;
import biggestxuan.emcworld.common.network.toClient.UtilPacket.UtilDataPack;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientPacketHandler {
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
                cap.setSHDifficulty(pack.getSh_difficulty());
                cap.setShield(pack.getShield());
                cap.setMaxShield(pack.getMaxShield());
                cap.setLastShield(pack.isLastShield());
                cap.setGaiaPlayer(pack.getGaiaPlayer());
                cap.setLiveMode(pack.isLiveMode());
                cap.setMV(pack.getMV());
                cap.setLastAttackTime(pack.getLastAttackTime());
                cap.setRaidTime(pack.getRaidTime());
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

    public static <T> void handlePacket(T packet, Supplier<NetworkEvent.Context> ctx){
        if(packet instanceof ChangeRotPacket){
            ClientPlayerEntity player = Minecraft.getInstance().player;
            IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
            if(cap.getProfession() == 6){
                double chance = cap.getSkills()[8] / 10000d;
                if(MathUtils.isRandom(chance)){
                    return;
                }
            }
            ChangeRotPacket p = (ChangeRotPacket) packet;
            if(player != null && !player.isDeadOrDying()){
                if(p.getX() != -100){
                    player.xRot = p.getX();
                }
                if(p.getY() != -100){
                    player.yRot = p.getY();
                }
            }
        }
        if(packet instanceof BuyLotteryClientPacket){
            Minecraft.getInstance().setScreen(new ChooseLotteryScreen());
        }
    }
}
