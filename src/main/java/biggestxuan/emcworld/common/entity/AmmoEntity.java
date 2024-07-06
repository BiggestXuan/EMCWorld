package biggestxuan.emcworld.common.entity;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/22
 */

import biggestxuan.emcworld.common.registry.EWEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class AmmoEntity extends ProjectileItemEntity {
    public AmmoEntity(EntityType<? extends ProjectileItemEntity> p_i50159_1_, World p_i50159_2_) {
        super(p_i50159_1_, p_i50159_2_);
    }

    public AmmoEntity(LivingEntity p_i50157_2_, World p_i50157_3_) {
        super(EWEntities.ammo,p_i50157_2_, p_i50157_3_);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte p_70103_1_) {

    }

    @Override
    protected void onHitBlock(BlockRayTraceResult p_70227_1_) {
        if (!this.level.isClientSide) {
            this.remove();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(position().y >= 256){
            remove();
        }
    }

    @Nonnull
    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }
}
