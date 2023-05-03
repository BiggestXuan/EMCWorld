package biggestxuan.emcworld.common.network.toServer;

/***
 * EMC WORLD MOD
 * @Author Biggest_Xuan
 * 2022/10/10
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.skill.PlayerSkillModify;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class AdminPacket {
    private static final Logger LOGGER = LogManager.getLogger("EMCWorld/Admin_System");
    private final int index;

    public AdminPacket(int index){
        this.index = index;
    }

    public static void encode(AdminPacket message, PacketBuffer bf){
        bf.writeInt(message.index);
    }

    public static AdminPacket decode(PacketBuffer bf){
        return new AdminPacket(bf.readInt());
    }

    public static void handle(AdminPacket msg, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(()-> {
            int index = msg.index;
            ServerPlayerEntity player = context.get().getSender();
            if (context.get().getDirection().getReceptionSide().isServer() && player != null) {
                String name = player.getScoreboardName();
                if (player.hasPermissions(4) && player.isCreative() && ConfigManager.ADMIN_MENU.get()) {
                    int x = player.blockPosition().getX();
                    int y = player.blockPosition().getY();
                    int z = player.blockPosition().getZ();
                    IUtilCapability util = player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new);
                    IPlayerSkillCapability skill = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
                    switch (index) {
                        case 1:
                            if (skill.getProfession() == 0) {
                                Message.sendMessage(player, EMCWorld.tc("message.admin.deny"));
                                break;
                            }
                            skill.setMaxLevel(100);
                            for (int i = 10; i < 110; i += 10) {
                                PlayerSkillModify.makePlayerGetDefaultSkill(player, i, 0);
                            }
                            LOGGER.info(name+" set his max level to 100!");
                            break;
                        case 2:
                            skill.addLevel();
                            LOGGER.info(name+" add his level! Now is: "+skill.getLevel());
                            break;
                        case 3:
                            for (int i = 0; i < 10; i++) {
                                skill.addLevel();
                            }
                            LOGGER.info(name+" add his 10 level! Now is: "+skill.getLevel());
                            break;
                        case 4:
                            skill.setLevel(1);
                            skill.setProfession(0);
                            for (int i = 0; i < skill.getSkills().length; i++) {
                                skill.setSkills(i, 0);
                            }
                            LOGGER.info(name+" clear his skills and level!");
                            break;
                        case 5:
                            skill.setSkills(43, 0);
                            skill.setSkills(7, 0);
                            skill.setSkills(19, 0);
                            skill.setSkills(42, 0);
                            LOGGER.info(name+" clear his skills cooldown!");
                            break;
                        case 6:
                            EMCHelper.modifyPlayerEMC(player,new EMCSource.CommandEMCSource(-EMCHelper.getPlayerEMC(player),player,null,0),true);
                            LOGGER.info(name+" clear his all EMC!");
                            break;
                        case 7:
                            for (AbstractRaiderEntity entity : player.level.getLoadedEntitiesOfClass(AbstractRaiderEntity.class, new AxisAlignedBB(new BlockPos(x + 128, y + 128, z + 128), new BlockPos(x - 128, 0, z - 128)))) {
                                entity.hurt(EWDamageSource.REALLY, entity.getMaxHealth()*1.5f);
                            }
                            LOGGER.info(name+" clear all raid entity!");
                            break;
                        case 8:
                            util.setCoolDown(0);
                            LOGGER.info(name+" clear his crafting cooldown!");
                            break;
                    }
                }
                else {
                    LOGGER.warn(name+" try to use admin command, but this player hasn't enough permission!");
                    Message.sendMessage(player, EMCWorld.tc("message.admin.permission"));
                }
            }

        });
        context.get().setPacketHandled(true);
    }
}
