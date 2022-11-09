package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/24
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon.CharaSword;
import biggestxuan.emcworld.common.utils.MathUtils;
import mekanism.api.MekanismAPI;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingDamageEvent {
    @SubscribeEvent
    public static void livingDamageEvent(net.minecraftforge.event.entity.living.LivingDamageEvent event){
        LivingEntity entity = event.getEntityLiving();
        float damage = event.getAmount();
        if(entity.level.isClientSide) return;
        if(!(event.getEntityLiving() instanceof PlayerEntity) && MathUtils.isMaxDifficulty()) {
            damage *= 0.67f;
            event.setAmount(damage);
        }
        DamageSource source = event.getSource();
        if(source.getDirectEntity() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) source.getDirectEntity();
            ItemStack stack = player.getMainHandItem();
            if(entity instanceof AbstractRaiderEntity){
                IUtilCapability cap = player.getCapability(EMCWorldCapability.UTIL).orElseThrow(NullPointerException::new);
                MinecraftServer server = entity.getServer();
                assert server != null;
                ServerWorld world = server.overworld();
                if(world.isRaided(new BlockPos(player.position()))){
                    cap.addRaidDamage(Math.min(damage, entity.getHealth()));
                }
            }
            if(!(entity instanceof PlayerEntity)){
                if(stack.getItem() instanceof CharaSword){
                    CharaSword sword = (CharaSword) stack.getItem();
                    double r = sword.getLevel(stack) * 11.4514 ;
                    MekanismAPI.getRadiationManager().radiate(entity,r);
                }
            }
        }
    }
}
