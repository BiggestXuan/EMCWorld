package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.utils.EMCLog.EMCSource;
import biggestxuan.emcworld.common.utils.MathUtils;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Vicio extends RaidEffect {
    public Vicio(@Nonnull Raid raid) {
        super(raid,"vicio", ConstellationsAS.vicio);
    }

    @Override
    public void onSpawnIllager(LivingEntity livingEntity) {
        double chance = hasAlcara ? 0.05 : 0.005;
        if(MathUtils.isRandom(chance)){
            giveEffect(livingEntity,(MathUtils.isRandom(0.33) ? 0 : (MathUtils.isRandom(0.33) ? 1 : 2)));
        }
    }

    @Override
    public EMCSource<?> onPlayerEMCCost(PlayerEntity player, EMCSource<?> source) {
        double rate = hasAlcara ? 0.99 : 0.9;
        if(source.emc() < 0){
            source.setEmc((long) (source.emc() * rate));
        }
        return source;
    }

    private void giveEffect(LivingEntity livingEntity, int level){
        int time = hasAlcara ? 1800 : 180;
        livingEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,time,level));
    }
}
