package biggestxuan.emcworld.common.events;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/08
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.data.LotteryData;
import biggestxuan.emcworld.common.raid.RaidEffectExecutor;
import biggestxuan.emcworld.common.utils.EMCLog.EMCWriter;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID)
public class serverEvent {
    @SubscribeEvent
    public static void ServerStartEvent(FMLServerStartedEvent event){
        EMCWriter.Init();
    }

    @SubscribeEvent
    public static void ServerTick(TickEvent.ServerTickEvent event){
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.START) {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            ServerWorld world = server.overworld();
            LotteryData data = LotteryData.getInstance(server);
            if(world.getDayTime() % 240000 == 0 || world.getDayTime() == 0){
                data.openLottery();
            }
            if(world.getDayTime() == 0){
                data.setStoredEMC(2000000000L);
            }
            world.getEntities().forEach(entity -> {
                if(entity instanceof AbstractIllagerEntity){
                    AbstractIllagerEntity illager = (AbstractIllagerEntity) entity;
                    if(world.isRaided(illager.blockPosition())){
                        Raid raid = world.getRaidAt(illager.blockPosition());
                        assert raid != null;
                        new RaidEffectExecutor(raid).onIllagerTick(illager);
                    }
                }
            });
        }
    }
}
