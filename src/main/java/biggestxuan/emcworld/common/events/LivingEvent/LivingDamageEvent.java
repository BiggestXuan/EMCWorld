package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/24
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon.CharaSword;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.registry.EWEffects;
import biggestxuan.emcworld.common.utils.MathUtils;
import mekanism.api.MekanismAPI;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
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
        DamageSource source = event.getSource();
        if(entity.level.isClientSide) return;
        if(!(event.getEntityLiving() instanceof PlayerEntity) && MathUtils.isMaxDifficulty()) {
            damage *= 0.67f;
        }
        if(source instanceof EWDamageSource || source.equals(DamageSource.MAGIC)){
            EffectInstance instance = entity.getEffect(EWEffects.MAGIC_PROTECT.get());
            if(instance != null){
                int level = instance.getAmplifier() + 1;
                damage = (float) Math.max(damage * (1 - level * 0.1),0);
            }
        }
        event.setAmount(damage);
        if(source instanceof EWDamageSource.ReallyDamage){
            EWDamageSource.ReallyDamage really = (EWDamageSource.ReallyDamage) source;
            PlayerEntity player = really.getPlayer();
            if(player != null){
                addPlayerRaidDamage(player,damage,entity);
            }
        }
        if(source.getDirectEntity() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) source.getDirectEntity();
            ItemStack stack = player.getMainHandItem();
            addPlayerRaidDamage(player,damage,entity);
            if(!(entity instanceof PlayerEntity)){
                if(stack.getItem() instanceof CharaSword){
                    CharaSword sword = (CharaSword) stack.getItem();
                    double r = sword.getLevel(stack) * 11.4514 ;
                    MekanismAPI.getRadiationManager().radiate(entity,r);
                }
            }
        }
    }

    private static void addPlayerRaidDamage(PlayerEntity player,float value,LivingEntity target){
        if(player == null) return;
        if(target instanceof AbstractRaiderEntity){
            IUtilCapability cap = EMCWorldAPI.getInstance().getUtilCapability(player);
            MinecraftServer server = player.getServer();
            assert server != null;
            ServerWorld world = server.overworld();
            if(world.isRaided(new BlockPos(player.position()))){
                cap.addRaidDamage(Math.min(value, target.getHealth()));
            }
        }
    }
}
