package biggestxuan.emcworld.common.raid.effects;

import biggestxuan.emcworld.common.raid.RaidEffect;
import hellfirepvp.astralsorcery.common.lib.ConstellationsAS;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.raid.Raid;
import wayoftime.bloodmagic.potion.BloodMagicPotions;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2024/7/12
 */
public class Gelu extends RaidEffect {
    public Gelu(@Nonnull Raid raid) {
        super(raid,"gelu", ConstellationsAS.gelu);
    }

    @Override
    public void onPlayerTick(PlayerEntity player) {
        Collection<EffectInstance> list = player.getActiveEffects();
        for (EffectInstance instance : list) {
            if(instance.getEffect().isBeneficial()){
                instance.tick(player,() -> {});
            }
        }
    }

    @Override
    public void onIllagerTick(LivingEntity livingEntity) {
        if(world.getGameTime() % 200 == 0){
            livingEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,0,200));
            livingEntity.addEffect(new EffectInstance(BloodMagicPotions.GRAVITY,0,200));
        }
    }
}
