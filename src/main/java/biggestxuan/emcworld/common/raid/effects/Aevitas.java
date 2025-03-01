package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.raid.Raid;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Aevitas extends RaidEffect {
    public Aevitas(@Nonnull Raid raid) {
        super(raid, "aevitas", ConstellationsAS.aevitas);
    }

    @Override
    public void onIllagerTick(LivingEntity livingEntity) {
        if(world.getGameTime() % 20 == 0) {
            float heal = livingEntity.getMaxHealth() - livingEntity.getHealth();
            heal *= hasAlcara ? 0.001F : 0.01F;
            livingEntity.heal(heal);
        }
    }

    @Override
    public float onPlayerAttack(PlayerEntity player, Entity attacker, float amount) {
        player.heal(hasAlcara ? amount * 0.1F : amount);
        return amount;
    }

    @Override
    public void onPlayerTick(PlayerEntity player) {
        int time = hasAlcara ? 2000 : 200;
        if(world.getGameTime() % time == 0) {
            player.heal(hasAlcara ? 0.2F : 2F);
        }
    }
}
