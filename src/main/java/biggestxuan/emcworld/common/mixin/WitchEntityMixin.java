package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.compact.ScalingHealth.DifficultyHelper;
import biggestxuan.emcworld.common.config.ConfigManager;
import biggestxuan.emcworld.common.registry.EWEffects;
import biggestxuan.emcworld.common.utils.MathUtils;
import biggestxuan.emcworld.common.utils.SkillUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/10/18
 */

@EMCWorldSince("1.0.6")
@Mixin(WitchEntity.class)
public abstract class WitchEntityMixin extends AbstractRaiderEntity implements IRangedAttackMob {
    @Shadow public abstract boolean isDrinkingPotion();

    protected WitchEntityMixin(EntityType<? extends AbstractRaiderEntity> p_i50143_1_, World p_i50143_2_) {
        super(p_i50143_1_, p_i50143_2_);
    }

    /**
     * @author Biggest_Xuan
     * @reason Add difficulty.
     */
    @Overwrite
    @Override
    public void performRangedAttack(@Nonnull LivingEntity p_82196_1_, float p_82196_2_) {
        if (!this.isDrinkingPotion()) {
            Vector3d vector3d = p_82196_1_.getDeltaMovement();
            double d0 = p_82196_1_.getX() + vector3d.x - this.getX();
            double d1 = p_82196_1_.getEyeY() - (double)1.1F - this.getY();
            double d2 = p_82196_1_.getZ() + vector3d.z - this.getZ();
            float f = MathHelper.sqrt(d0 * d0 + d2 * d2);
            List<EffectInstance> list = new ArrayList<>();
            double difficulty = DifficultyHelper.getLivingDifficulty(this);
            if (p_82196_1_ instanceof AbstractRaiderEntity) {
                if (p_82196_1_.getHealth() / p_82196_1_.getMaxHealth() <= 0.25D) {
                    list.add(SkillUtils.getEffect(Effects.HEAL,1,(int)(difficulty / 50)));
                } else {
                    list.add(SkillUtils.getEffect(Effects.REGENERATION,600,(int)(difficulty / 50)));
                    list.add(SkillUtils.getEffect(Effects.DAMAGE_RESISTANCE,600,(int)(Math.min(difficulty / 250,3))));
                    list.add(SkillUtils.getEffect(Effects.FIRE_RESISTANCE,2400,0));
                }
                if(MathUtils.isRandom(0.25 * ConfigManager.DIFFICULTY.get())){
                    list.add(SkillUtils.getEffect(Effects.DAMAGE_BOOST,300,(int)(difficulty / 50)));
                    list.add(SkillUtils.getEffect(Effects.MOVEMENT_SPEED,300,(int)(Math.min(5,(difficulty / 200)))));
                }
                this.setTarget(null);
            } else if (MathUtils.isRandom(0.15)) {
                list.add(SkillUtils.getEffect(Effects.DIG_SLOWDOWN,300,(Math.min(5,(int)(difficulty / 150)))));
                list.add(SkillUtils.getEffect(EWEffects.EMC_FLAMING.get(),300,(int)(difficulty / 75)));
                list.add(SkillUtils.getEffect(Effects.MOVEMENT_SLOWDOWN,300,(int)(Math.min(5,(difficulty / 200)))));
            } else if (p_82196_1_.getHealth() >= 8.0F && !p_82196_1_.hasEffect(Effects.POISON)) {
                list.add(SkillUtils.getEffect(Effects.POISON,300,(int)(difficulty / 65)));
                list.add(SkillUtils.getEffect(EWEffects.EMC_BROKEN.get(),450,(int)(difficulty / 110)));
            } else if (f <= 3.0F && !p_82196_1_.hasEffect(Effects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
                list.add(SkillUtils.getEffect(Effects.WEAKNESS,300,(int)(difficulty / 90)));
                list.add(SkillUtils.getEffect(Effects.WITHER,300,(int)(difficulty / 120)));
            }

            PotionEntity potionentity = new PotionEntity(this.level, this);
            potionentity.setItem(PotionUtils.setCustomEffects(new ItemStack(Items.SPLASH_POTION),list));
            potionentity.xRot -= -20.0F;
            potionentity.shoot(d0, d1 + (double)(f * 0.2F), d2, 0.75F, 8.0F);
            if (!this.isSilent()) {
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            }

            this.level.addFreshEntity(potionentity);
        }
    }
}
