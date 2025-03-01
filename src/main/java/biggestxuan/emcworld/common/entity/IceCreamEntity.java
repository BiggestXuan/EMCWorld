package biggestxuan.emcworld.common.entity;

import biggestxuan.emcworld.common.registry.EWEntities;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * EMC WORLD MOD
 *
 * @Author Biggest_Xuan
 * 2023/09/14
 */

public class IceCreamEntity extends ProjectileItemEntity {

    public IceCreamEntity(EntityType<? extends ProjectileItemEntity> p_i50155_1_, World p_i50155_2_) {
        super(p_i50155_1_, p_i50155_2_);
    }

    public IceCreamEntity(LivingEntity p_i50157_2_, World p_i50157_3_) {
        super(EWEntities.ice_cream,p_i50157_2_, p_i50157_3_);
    }

    @Override
    @Nonnull
    protected Item getDefaultItem() {
        return EWItems.ICE_CREAM.get();
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult p_230299_1_) {
        super.onHitBlock(p_230299_1_);
        SetSlow(level,p_230299_1_.getBlockPos());
        super.remove();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);
        Entity entity = p_213868_1_.getEntity();
        SetSlow(entity.level,entity.blockPosition());
        super.remove();
    }

    private static void SetSlow(World world, BlockPos pos){
        AxisAlignedBB aabb = MathUtils.expandAABB(pos,5);
        for(LivingEntity l : world.getLoadedEntitiesOfClass(LivingEntity.class,aabb)){
            if(!(l instanceof PlayerEntity) && !l.level.isClientSide){
                l.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN,200,255));
            }
        }
    }
}
