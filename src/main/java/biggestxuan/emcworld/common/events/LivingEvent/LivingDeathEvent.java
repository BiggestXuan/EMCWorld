package biggestxuan.emcworld.common.events.LivingEvent;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/10/07
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.api.item.IKillCountItem;
import biggestxuan.emcworld.api.item.equipment.weapon.BaseEMCGodSword;
import biggestxuan.emcworld.common.entity.AmmoEntity;
import biggestxuan.emcworld.common.raid.RaidEffectExecutor;
import biggestxuan.emcworld.common.registry.EWEffects;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.Message;
import biggestxuan.emcworld.common.entity.Player.Tulye;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EMCWorld.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class LivingDeathEvent {
    @SubscribeEvent
    public static void livingDeath(net.minecraftforge.event.entity.living.LivingDeathEvent event){
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        World world = entity.level;
        if(world.isClientSide) return;
        ServerWorld world1 = (ServerWorld) world;
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
        ResourceLocation rl = entity.getType().getRegistryName();
        if(rl != null && rl.getPath().contains("warden")){
            if(MathUtils.isRandom(0.01)){
                BlockPos pos = entity.blockPosition();
                world.addFreshEntity(new ItemEntity(world1,pos.getX(),pos.getY(),pos.getZ(),new ItemStack(EWItems.SCROLL_MYSTIC.get())));
            }
        }
        if(source.getEntity() instanceof AmmoEntity){
            AmmoEntity ammo = (AmmoEntity) source.getEntity();
            if(ammo.getOwner() instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) ammo.getOwner();
                IPlayerSkillCapability cap = EMCWorldAPI.getInstance().getPlayerSkillCapability(player);
                if(cap.getProfession() == 6 && cap.getModify() == 1){
                    double chance = cap.getSkills()[36]/10000d;
                    if(MathUtils.isRandom(chance)){
                        player.addEffect(new EffectInstance(EWEffects.ACCURACY.get(),4,200));
                    }
                }
            }
        }
        if(entity instanceof AbstractIllagerEntity){
            AbstractIllagerEntity illager = (AbstractIllagerEntity) entity;
            BlockPos pos = illager.blockPosition();
            if(world1.isRaided(pos)){
                Raid raid = world1.getRaidAt(pos);
                if (raid != null){
                    new RaidEffectExecutor(raid).onIllagerDeath(illager,source);
                }
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
