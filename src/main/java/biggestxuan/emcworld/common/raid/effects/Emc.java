package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.events.LivingEvent.LivingDeathDropsEvent;
import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.registry.EWDamageSource;
import biggestxuan.emcworld.common.registry.EWStarlight;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Emc extends RaidEffect {
    double rate = hasAlcara ? 0.025 : 0.25;
    public Emc(@Nonnull Raid raid) {
        super(raid, "emc", EWStarlight.EMC_STAR.get());
    }

    @Override
    public float onIllagerAttack(LivingEntity attacker, Entity target, float amount) {
        target.hurt(EWDamageSource.TRUE, hasAlcara ? amount * 0.03F : 0.3F);
        return amount * (hasAlcara ? 0.97F : 0.7F);
    }

    @Override
    public void onIllagerDeath(LivingEntity livingEntity, DamageSource source) {
        PlayerEntity player = getKilledPlayer(source);
        if(player != null && MathUtils.isRandom(rate)){
            int level = Math.max(1,waves/(hasAlcara ? 10 : 4));
            LivingDeathDropsEvent.dropGems(livingEntity,level);
        }
    }
}
