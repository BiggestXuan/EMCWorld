package biggestxuan.emcworld.common.mixin;

import biggestxuan.emcworld.api.EMCWorldSince;
import biggestxuan.emcworld.common.utils.MathUtils;
import divinerpg.entities.projectile.DivineThrowable;
import divinerpg.entities.projectile.EntitySoundOfCarols;
import divinerpg.entities.projectile.EntitySoundOfMusic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/07/26
 */

@Mixin(EntitySoundOfMusic.class)
@EMCWorldSince("1.0.2")
public abstract class EntitySoundOfMusicMixin extends DivineThrowable {
    public EntitySoundOfMusicMixin(EntityType<? extends ThrowableEntity> type, LivingEntity entity, World world) {
        super(type, entity, world);
    }

    /**
     * @author Biggest_Xuan
     * @reason Add damage
     */
    @Overwrite(remap = false)
    protected void onHitEntity(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        float damage = 10;
        if(getOwner() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) getOwner();
            damage = MathUtils.getMusicShooterDamage(damage,player.getMainHandItem());
        }
        entity.hurt(DamageSource.thrown(this, getOwner()), damage);

        if (!this.level.isClientSide) {
            this.kill();
        }

    }
}
