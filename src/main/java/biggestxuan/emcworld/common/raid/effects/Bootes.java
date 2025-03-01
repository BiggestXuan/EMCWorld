package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.registry.EWEffects;
import biggestxuan.emcworld.common.utils.MathUtils;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
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
public class Bootes extends RaidEffect {
    public Bootes(@Nonnull Raid raid) {
        super(raid, "bootes", ConstellationsAS.bootes);
    }

    @Override
    public float onPlayerHurt(PlayerEntity player, DamageSource source, float amount) {
        double chance = hasAlcara ? 0.0015 : 0.015;
        int time = hasAlcara ? 20 : 200;
        if(MathUtils.isRandom(chance)){
            player.addEffect(new EffectInstance(EWEffects.EMC_BROKEN.get(),time,(MathUtils.isRandom(0.8) ? 0 : 1)));
        }
        return amount;
    }

    @Override
    public void onIllagerTick(LivingEntity livingEntity) {
        if(world.getGameTime() % 200 == 0){
            livingEntity.addEffect(new EffectInstance(EffectsAS.EFFECT_DROP_MODIFIER,0,200));
        }
    }
}
