package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/12/17
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.common.registry.EWItems;
import biggestxuan.emcworld.common.utils.Message;
import com.github.alexthe666.rats.server.entity.EntityRat;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(EntityRat.class)
public abstract class RatMixin extends TameableEntity {

    @Shadow @Nullable public abstract LivingEntity getOwner();

    protected RatMixin(EntityType<? extends TameableEntity> p_i48574_1_, World p_i48574_2_) {
        super(p_i48574_1_, p_i48574_2_);
    }

    @Inject(method = "mobInteract",cancellable = true,at = @At("HEAD"))
    public void use(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResultType> cir){
        if(!player.level.isClientSide){
            ItemStack stack = player.getMainHandItem();
            if(stack.getItem().equals(EWItems.MONEY.get()) && super.getOwner() == null){
                stack.shrink(1);
                super.tame(player);
                cir.setReturnValue(ActionResultType.CONSUME);
                cir.cancel();
                Message.sendMessage(player, EMCWorld.tc("message.cr.1"));
            }
        }
    }
}
