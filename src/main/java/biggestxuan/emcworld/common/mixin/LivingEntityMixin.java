package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/26
 */

import biggestxuan.emcworld.common.items.SponsorsItem.IceCream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
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

    @Redirect(
            method = "eat",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/item/ItemStack;shrink(I)V")
    )
    public void _eat(ItemStack instance, int p_190918_1_){
        if(!(instance.getItem() instanceof IceCream)){
            instance.shrink(1);
        }
    }
}
