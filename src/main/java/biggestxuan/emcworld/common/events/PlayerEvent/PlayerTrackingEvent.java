package biggestxuan.emcworld.common.events.PlayerEvent;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/02
 */

import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.common.network.toClient.SkillPacket.DataPack;
import biggestxuan.emcworld.common.network.toClient.SkillPacket.SkillNetworking;
import biggestxuan.emcworld.common.network.toClient.UtilPacket.UtilDataPack;
import biggestxuan.emcworld.common.network.toClient.UtilPacket.UtilNetworking;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerTrackingEvent {
    @SubscribeEvent
    public static void playerTicking(TickEvent.PlayerTickEvent event){
        /**if(!event.player.getCommandSenderWorld().isClientSide){
            if(event.player.isDeadOrDying()) return;
            event.player.getCapability(EMCWorldCapability.PLAYER_LEVEL).ifPresent(cap -> SkillNetworking.INSTANCE.send(PacketDistributor.PLAYER.with(()->(ServerPlayerEntity) event.player),new DataPack(
                    cap.getLevel(),cap.getXP(), cap.getProfession(),cap.getMaxLevel(),cap.getModify(), cap.getSkills()
            )));
            event.player.getCapability(EMCWorldCapability.UTIL).ifPresent(cap -> UtilNetworking.INSTANCE.send(PacketDistributor.PLAYER.with(()->(ServerPlayerEntity) event.player),new UtilDataPack(
                    cap.isRaid(), cap.getState(), cap.getPillager(), cap.getVillager(),cap.getWave(), cap.getMaxWave(),cap.getRaidRate(),cap.getCoolDown(),cap.getDifficulty(),cap.getLevel(),cap.getArcana(),cap.getMaxArcana(), cap.showArcana(),cap.getSHDifficulty(),cap.getShield(), cap.getMaxShield(),cap.isLastShield(),cap.gaiaPlayer()
            )));
        }*/
    }
}
