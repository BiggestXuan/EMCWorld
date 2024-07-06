package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/26
 */

import biggestxuan.emcworld.api.event.LivingEatFoodEvent;
import biggestxuan.emcworld.common.items.SponsorsItem.IceCream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract boolean addEffect(EffectInstance p_195064_1_);

    @Shadow public abstract SoundEvent getEatingSound(ItemStack p_213353_1_);

    @Shadow protected abstract void addEatEffect(ItemStack p_213349_1_, World p_213349_2_, LivingEntity p_213349_3_);

    public LivingEntityMixin(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
    }

    @Redirect(
            method = "checkTotemDeathProtection",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/LivingEntity;setHealth(F)V")
    )
    public void checkTotem(LivingEntity instance, float p_70606_1_){
        instance.setHealth(instance.getMaxHealth());
    }

    /**
     * @author Biggest_Xuan
     * @reason None.
     */
    @Overwrite
    public ItemStack eat(World p_213357_1_, ItemStack p_213357_2_) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (p_213357_2_.isEdible()) {
            p_213357_1_.playSound(null, this.getX(), this.getY(), this.getZ(), this.getEatingSound(p_213357_2_), SoundCategory.NEUTRAL, 1.0F, 1.0F + (p_213357_1_.random.nextFloat() - p_213357_1_.random.nextFloat()) * 0.4F);
            this.addEatEffect(p_213357_2_, p_213357_1_, living);
            MinecraftForge.EVENT_BUS.post(new LivingEatFoodEvent(living,p_213357_2_.getItem().getFoodProperties(),p_213357_2_));
            if (!(living instanceof PlayerEntity) || !((PlayerEntity)living).abilities.instabuild && !(p_213357_2_.getItem() instanceof IceCream)) {
                p_213357_2_.shrink(1);
            }
        }

        return p_213357_2_;
    }
}
