package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import hellfirepvp.astralsorcery.common.lib.EffectsAS;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Pelotrio extends RaidEffect {
    public Pelotrio(@Nonnull Raid raid) {
        super(raid,"pelotrio", ConstellationsAS.pelotrio);
    }

    @Override
    public void onIllagerTick(LivingEntity livingEntity) {
        int time = hasAlcara ? 20 : 200;
        if(world.getGameTime() % 1200 == 0) {
            livingEntity.addEffect(new EffectInstance(EffectsAS.EFFECT_CHEAT_DEATH,time,0));
        }
    }

    @Override
    public float onPlayerEMCShiedCost(PlayerEntity player, DamageSource source, float amount) {
        return amount * (hasAlcara ? 0.967F : 0.67F);
    }
}
