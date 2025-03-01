package biggestxuan.emcworld.common.raid;

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.utils.ASUtils;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.Message;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class RaidEffectExecutor {
    private final MinecraftServer server;
    private final ServerWorld world;
    private final Raid raid;

    public RaidEffectExecutor(Raid raid) {
        this.raid = raid;
        server = raid.getLevel().getServer();
        world = raid.level;
    }

    public void onTick(){
        for(RaidEffect effect : getAllEffects()){
            effect.tick();
        }
    }

    public void onPlayerTick(PlayerEntity player){
        for(RaidEffect effect : getAllEffects()){
            effect.onPlayerTick(player);
        }
    }

    public void onIllagerTick(LivingEntity living){
        for(RaidEffect effect : getAllEffects()){
            effect.onIllagerTick(living);
        }
    }

    public float onPlayerAttack(PlayerEntity player, Entity target, float amount){
        for(RaidEffect effect : getAllEffects()){
            EMCWorld.DevLogger(effect.constellation);
            amount = effect.onPlayerAttack(player, target, amount);
        }
        return amount;
    }

    public float onIllagerAttack(LivingEntity attacker,Entity target,float amount){
        for(RaidEffect effect : getAllEffects()){
            amount = effect.onIllagerAttack(attacker, target, amount);
        }
        return amount;
    }

    public float onIllagerHurt(LivingEntity illager, DamageSource source, float amount){
        for(RaidEffect effect : getAllEffects()){
            amount = effect.onIllagerHurt(illager,source,amount);
        }
        return amount;
    }

    public float onPlayerHurt(PlayerEntity player, DamageSource source, float amount){
        for(RaidEffect effect : getAllEffects()){
            amount = effect.onPlayerHurt(player,source,amount);
        }
        return amount;
    }

    public float onPlayerHeal(PlayerEntity healer, float amount){
        for(RaidEffect effect : getAllEffects()){
            amount = effect.onPlayerHeal(healer,amount);
        }
        return amount;
    }

    public EMCSource<?> onPlayerEMCCost(PlayerEntity player, EMCSource<?> source){
        for(RaidEffect effect : getAllEffects()){
            source = effect.onPlayerEMCCost(player,source);
        }
        return source;
    }

    public void onIllagerSpawner(LivingEntity entity){
        for(RaidEffect effect : getAllEffects()){
            effect.onSpawnIllager(entity);
        }
    }

    public float onPlayerEMCShieldCost(PlayerEntity player, DamageSource source, float amount){
        for(RaidEffect effect : getAllEffects()){
            amount = effect.onPlayerEMCShiedCost(player,source,amount);
        }
        return amount;
    }

    public void onIllagerDeath(LivingEntity entity,DamageSource source){
        for(RaidEffect effect : getAllEffects()){
            effect.onIllagerDeath(entity,source);
        }
    }

    public void announcementPlayer(PlayerEntity player){
        RaidEffectsRegistry.init(raid);
        ITextComponent textComponent = EMCWorld.tc("emcworld.raid_night");
        if(world.isRaided(player.blockPosition())){
            Message.sendMessage(player,textComponent);
            getAllEffects(true).forEach(effect -> Message.sendMessage(player,effect.getText()));
        }
    }

    public Set<RaidEffect> getAllEffects(){
        return getAllEffects(false);
    }

    public Set<RaidEffect> getAllEffects(boolean pass){
        Set<RaidEffect> effects = new HashSet<>();
        if(!pass && !world.isNight()){
            return effects;
        }
        RaidEffectsRegistry.init(raid);
        List<IConstellation> list = ASUtils.getWorldConstellation(world);
        list.forEach(i -> {
            RaidEffect effect = getRaidEffect(i);
            if(effect != null){
                effect.setRaid(raid);
                effects.add(effect);
            }
        });
        return effects;
    }

    @Nullable
    public static RaidEffect getRaidEffect(IConstellation constellation){
        for(RaidEffect effect : RaidEffect.effects){
            if(effect.isConstellation(constellation)){
                return effect;
            }
        }
        return null;
    }
}
