package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.utils.MathUtils;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.raid.Raid;
import wayoftime.bloodmagic.potion.BloodMagicPotions;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Horologium extends RaidEffect {
    public Horologium(@Nonnull Raid raid) {
        super(raid,"horologium", ConstellationsAS.horologium);
    }

    @Override
    public float onIllagerAttack(LivingEntity attacker, Entity target, float amount) {
        if(MathUtils.isRandom(0.114514)){
            attacker.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,7,1000));
        }
        return amount;
    }

    @Override
    public float onPlayerAttack(PlayerEntity player, Entity entity, float amount) {
        if(entity instanceof LivingEntity){
            LivingEntity target = (LivingEntity) entity;
            target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,4,300));
            target.addEffect(new EffectInstance(BloodMagicPotions.GRAVITY,0,300));
        }
        return amount;
    }
}
