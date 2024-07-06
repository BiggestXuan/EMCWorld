package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/09
 */

import biggestxuan.emcworld.EMCWorld;
import biggestxuan.emcworld.api.item.IDisableAnvilItem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.AbstractRepairContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(RepairContainer.class)
public abstract class RepairContainerMixin extends AbstractRepairContainer{

    @Shadow
    @Final
    private IntReferenceHolder cost;

    public RepairContainerMixin(@Nullable ContainerType<?> p_i231587_1_, int p_i231587_2_, PlayerInventory p_i231587_3_, IWorldPosCallable p_i231587_4_) {
        super(p_i231587_1_, p_i231587_2_, p_i231587_3_, p_i231587_4_);
    }

    @Inject(method = "createResult",at = @At("HEAD"),cancellable = true)
    public void level(CallbackInfo ci){
        ItemStack itemstack = inputSlots.getItem(0);
        ItemStack itemStack1 = inputSlots.getItem(1);
        if(itemstack.getItem() instanceof IDisableAnvilItem || itemStack1.getItem() instanceof IDisableAnvilItem){
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            this.cost.set(EMCWorld.HOMO);
            ci.cancel();
        }
    }
}
