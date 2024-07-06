package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/04
 */

import biggestxuan.emcworld.api.EMCWorldAPI;
import biggestxuan.emcworld.common.config.ConfigManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wayoftime.bloodmagic.common.item.ItemDaggerOfSacrifice;

@Mixin(ItemDaggerOfSacrifice.class)
public abstract class ItemDaggerOfSacrificeMixin extends Item {
    public ItemDaggerOfSacrificeMixin(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Inject(method = "hurtEnemy",at = @At("HEAD"), cancellable = true)
    public void hurt(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir){
        if(!attacker.level.isClientSide && attacker instanceof PlayerEntity && target != null && ConfigManager.SUNDRY_DISABLE_SACRIFICE.get()){
            PlayerEntity player = (PlayerEntity) target;
            if(target.getHealth() > EMCWorldAPI.getInstance().getPlayerSkillCapability(player).getLevel()){
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }
}
