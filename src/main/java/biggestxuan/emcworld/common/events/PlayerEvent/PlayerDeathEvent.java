package biggestxuan.emcworld.common.events.PlayerEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/08/02
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.common.compact.Projecte.EMCHelper;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerDeathEvent {
    @SubscribeEvent
    public static void playerDeathEvent(LivingDeathEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        if(livingEntity.level.isClientSide) return;
        if(livingEntity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) livingEntity;
            MinecraftServer server = livingEntity.level.getServer();
            assert server != null;
            ServerWorld world = server.overworld();
            BlockPos deathPos = new BlockPos(livingEntity.position());
            Raid raid = world.getRaidAt(deathPos);
            if(raid != null){
                for(PlayerEntity p: getNearPlayer(raid)){
                    Message.sendMessage(p, EMCWorld.tc("message.raid.loss"));
                }
                raid.status = Raid.Status.LOSS;
            }
            IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
            if(cap.getProfession() == 2 && cap.getModify() == 1 && cap.getSkills()[40] != 0 && cap.getSkills()[42] == 0){
                event.setCanceled(true);
                player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE,cap.getSkills()[40]/500,127));
            }
            long costEMC = MathUtils.getPlayerDeathBaseCost(player);
            if(costEMC == 0) return;
            if(EMCHelper.getPlayerEMC(player) >= costEMC){
                Message.sendMessage(player, EMCWorld.tc("message.evt.playerdeath",MathUtils.format(String.valueOf(Math.min(EMCHelper.getPlayerEMC(player),costEMC)))));
                EMCHelper.modifyPlayerEMC(player,Math.negateExact(costEMC),true);
            }
            else{
                player.inventory.dropAll();
            }
        }
    }

    public static List<? extends PlayerEntity> getNearPlayer(Raid raid){
        List<PlayerEntity> players = new ArrayList<>();
        World world = raid.getLevel();
        List<? extends PlayerEntity> allPlayer = world.players();
        for (PlayerEntity player : allPlayer){
            assert player.getServer() != null;
            ServerWorld serverWorld = player.getServer().overworld();
            BlockPos playerPos = new BlockPos(player.position());
            Raid playerRaid = serverWorld.getRaidAt(playerPos);
            if(playerRaid != null){
                if(serverWorld.isRaided(playerPos) && playerRaid.equals(raid)){
                    players.add(player);
                }
            }
        }
        return players;
    }
    public static List<? extends PlayerEntity> getNearPlayer(Raid raid,ServerWorld world){
        List<PlayerEntity> players = new ArrayList<>();
        List<? extends PlayerEntity> allPlayer = world.players();
        for (PlayerEntity player : allPlayer){
            assert player.getServer() != null;
            BlockPos playerPos = new BlockPos(player.position());
            Raid playerRaid = world.getRaidAt(playerPos);
            if(playerRaid != null){
                if(world.isRaided(playerPos) && playerRaid.equals(raid)){
                    players.add(player);
                }
            }
        }
        return players;
    }
}
