package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import biggestxuan.emcworld.common.utils.MathUtils;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Ulteria extends RaidEffect {
    public Ulteria(@Nonnull Raid raid) {
        super(raid,"ulteria", ConstellationsAS.ulteria);
    }

    double ratePerBlock = hasAlcara ? 0.0003 : 0.003;

    @Override
    public float onPlayerAttack(PlayerEntity player, Entity target, float amount) {
        float rate = (float) (1 + ratePerBlock * getCenterDistance(player));
        return amount * rate;
    }

    @Override
    public float onIllagerAttack(LivingEntity livingEntity, Entity target, float amount) {
        float rate = (float) (1 + ratePerBlock * getCenterDistance(livingEntity));
        return amount * rate;
    }

    private double getCenterDistance(LivingEntity entity){
        BlockPos pos = entity.blockPosition();
        return MathUtils.getDistance(pos,raid.center);
    }
}
