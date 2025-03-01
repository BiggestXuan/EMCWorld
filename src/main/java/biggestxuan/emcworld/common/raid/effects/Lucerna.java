package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.utils.MathUtils;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Lucerna extends RaidEffect {
    double chance = hasAlcara ? 0.02 : 0.2;

    public Lucerna(@Nonnull Raid raid) {
        super(raid, "lucerna", ConstellationsAS.lucerna);
    }

    @Override
    public float onIllagerHurt(LivingEntity livingEntity, DamageSource source, float amount) {

        float heal = hasAlcara ? 0.8F : 8F;
        if(MathUtils.isRandom(chance)){
            livingEntity.heal(heal);
        }
        return amount;
    }

    @Override
    public void onIllagerDeath(LivingEntity livingEntity, DamageSource source) {
        PlayerEntity player = getKilledPlayer(source);
        int time = hasAlcara ? 12 : 120;
        if(player != null && MathUtils.isRandom(chance)){
            player.addEffect(new EffectInstance(Effects.ABSORPTION,time,0));
        }
    }
}
