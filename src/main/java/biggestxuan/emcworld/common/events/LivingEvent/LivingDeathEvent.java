package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodWeapon;
import biggestxuan.emcworld.client.Message;
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
        if(source instanceof EWDamageSource.ReallyDamage){
            EWDamageSource.ReallyDamage d = (EWDamageSource.ReallyDamage) source;
            if(d.getPlayer() != null){
                addPlayerCount(d.getPlayer());
            }
        }
    }

    private static void addPlayerCount(PlayerEntity player){
        if(player.getMainHandItem().getItem() instanceof BaseEMCGodWeapon){
            BaseEMCGodWeapon weapon = (BaseEMCGodWeapon) player.getMainHandItem().getItem();
            weapon.addKillCount(player.getMainHandItem());
        }
    }
}
