package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/02/26
 */

import biggestxuan.emcworld.EMCWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    public ItemEntityMixin(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
    }

    @Inject(method = "tick",at = @At("HEAD"))
    public void tickEntity(CallbackInfo ci){
        ItemEntity entity = (ItemEntity) (Object) this;
        ItemStack stack = entity.getItem();
        if(shouldRemove(stack)){
            entity.remove();
        }
    }

    private static boolean shouldRemove(ItemStack stack){
        List<ItemStack> list = new ArrayList<>();
        list.add(EMCWorld.getItem("scalinghealth:power_crystal"));
        list.add(EMCWorld.getItem("scalinghealth:cursed_heart"));
        list.add(EMCWorld.getItem("scalinghealth:enchanted_heart"));
        list.add(EMCWorld.getItem("scalinghealth:chance_heart"));
        for(ItemStack s : list){
            if(stack.getItem().equals(s.getItem())){
                return true;
            }
        }
        return false;
    }
}
