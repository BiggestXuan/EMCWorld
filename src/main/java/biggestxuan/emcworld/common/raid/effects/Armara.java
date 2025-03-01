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
public class Armara extends RaidEffect {
    public Armara(@Nonnull Raid raid) {
        super(raid,"armara", ConstellationsAS.armara);
    }

    @Override
    public float onIllagerHurt(LivingEntity livingEntity, DamageSource source, float amount) {
        return amount * (hasAlcara ? 0.99F : 0.9F);
    }

    @Override
    public void onIllagerDeath(LivingEntity livingEntity,DamageSource source) {
        if(getKilledPlayer(source) != null){
            giveEffect(getKilledPlayer(source));
        }
    }

    private void giveEffect(PlayerEntity player){
        int time = hasAlcara ? 60 : 600;
        player.addEffect(new EffectInstance(EffectsAS.EFFECT_CHEAT_DEATH.getEffect(),time,0));
    }
}
