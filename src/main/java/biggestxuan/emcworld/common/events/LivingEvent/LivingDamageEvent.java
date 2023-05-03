package biggestxuan.emcworld.common.events.LivingEvent;

/***
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/24
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IUtilCapability;
import biggestxuan.emcworld.common.compact.GameStage.GameStageManager;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.items.Equipment.Weapon.GodWeapon.CharaSword;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.registry.EWEffects;
import biggestxuan.emcworld.common.utils.MathUtils;
import mekanism.api.MekanismAPI;
import mekanism.common.registries.MekanismDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.raid.Raid;
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
        double diff = ConfigManager.DIFFICULTY.get();
        if(!(event.getEntityLiving() instanceof PlayerEntity) && diff == 3D) {
            damage *= 0.67f;
        }else{
            damage *= 1 + (3 - diff) / 3;
        }
        if(entity instanceof AbstractRaiderEntity){
            MinecraftServer server = entity.getServer();
            if(server == null) return;
            Raid raid = server.overworld().getRaidAt(entity.blockPosition());
            damage *= raid != null && (source.getDirectEntity() instanceof AbstractRaiderEntity || source.isFire() || source.isExplosion() || source.equals(DamageSource.FALL)) ? 0 : 1;
            if(raid != null && source.getDirectEntity() instanceof ProjectileEntity){
                ProjectileEntity entity1 = (ProjectileEntity) source.getDirectEntity();
                damage *= entity1.getOwner() instanceof AbstractRaiderEntity ? 0 : 1;
            }
        }
        if(source instanceof EWDamageSource || source.equals(DamageSource.MAGIC)){
            EffectInstance instance = entity.getEffect(EWEffects.MAGIC_PROTECT.get());
            if(instance != null){
                int level = instance.getAmplifier() + 1;
                damage = (float) Math.max(damage * (1 - level * 0.1),0);
            }
        }
        if(source instanceof EWDamageSource){
            PlayerEntity player = EWDamageSource.REALLY.getPlayer();
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
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            if(source.equals(MekanismDamageSource.RADIATION)){
                damage += player.getMaxHealth() * 0.25f;
            }
            if(source.getDirectEntity() instanceof AbstractRaiderEntity){
                damage *= GameStageManager.hasStage(player,"one") ? 1 : 3;
                damage *= GameStageManager.hasStage(player,"two") ? 1 : 3;
                damage *= GameStageManager.hasStage(player,"three") ? 1 : 2;
            }
            if(source.getDirectEntity() instanceof ProjectileEntity){
                ProjectileEntity entity1 = (ProjectileEntity) source.getDirectEntity();
                if(entity1.getOwner() instanceof AbstractRaiderEntity){
                    damage *= GameStageManager.hasStage(player,"one") ? 1 : 3;
                    damage *= GameStageManager.hasStage(player,"two") ? 1 : 2.5;
                    damage *= GameStageManager.hasStage(player,"three") ? 1 : 2;
                }
            }
        }
        event.setAmount(damage);
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
