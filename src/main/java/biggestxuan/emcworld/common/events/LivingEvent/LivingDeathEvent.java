package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IKillCountItem;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.entity.Player.Tulye;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingDeathEvent {
    @SubscribeEvent
    public static void livingDeath(net.minecraftforge.event.entity.living.LivingDeathEvent event){
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        if(source.getEntity() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) source.getEntity();
            if(entity instanceof Tulye){
                player.removeAllEffects();
                Message.sendMessage(player,EMCWorld.tc("message.hurt.tulye1"));
            }
            addPlayerCount(player);
        }
        if(source instanceof EWDamageSource){
            EWDamageSource d = (EWDamageSource) source;
            if(d.getPlayer() != null){
                addPlayerCount(d.getPlayer());
            }
        }
    }

    private static void addPlayerCount(PlayerEntity player){
        if(player.getMainHandItem().getItem() instanceof IKillCountItem){
            IKillCountItem weapon = (IKillCountItem) player.getMainHandItem().getItem();
            weapon.addKillCount(player.getMainHandItem());
        }
    }
}
