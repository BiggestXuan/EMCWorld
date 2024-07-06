package biggestxuan.emcworld.common.events.LivingEvent;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.items.ModPack.EndLight;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/11/07
 */

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class EnderTeleportEvent {
    @SubscribeEvent
    public static void enderTeleportEvent(net.minecraftforge.event.entity.living.EnderTeleportEvent event){
        LivingEntity living = event.getEntityLiving();
        if(!living.level.isClientSide){
            AxisAlignedBB aabb = MathUtils.expandAABB(living.blockPosition(),6);
            for(PlayerEntity player : living.level.getLoadedEntitiesOfClass(PlayerEntity.class,aabb)){
                if(EndLight.playerHasEndLightArmor(player) >= 4){
                    event.setCanceled(true);
                    break;
                }
            }
        }
    }
}
