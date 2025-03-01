package biggestxuan.emcworld.common.events.PlayerEvent;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.event.PlayerEMCShiedCostEvent;
import biggestxuan.emcworld.common.raid.RaidEffectExecutor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/13
 */
@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEmcShieldEvent {
    @SubscribeEvent
    public static void event(PlayerEMCShiedCostEvent event){
        PlayerEntity player = event.getPlayer();
        World world = player.level;
        if(world.isClientSide) return;
        ServerWorld world1 = (ServerWorld) world;
        BlockPos pos = player.blockPosition();
        float amt = event.getAmt();
        if(world1.isRaided(pos)){
            Raid raid = world1.getRaidAt(pos);
            if(raid != null){
                amt = new RaidEffectExecutor(raid).onPlayerEMCShieldCost(player,event.getSource(),amt);
            }
        }
        event.setAmt(amt);
    }
}
