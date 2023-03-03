package biggestxuan.emcworld.common.mixin;

/*
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/01
 */

import biggestxuan.emcworld.common.items.Equipment.Weapon.Dagger.DaggerItem;
import biggestxuan.emcworld.common.items.Equipment.Weapon.WarHammer.WarHammerItem;
import cursedflames.bountifulbaubles.common.item.items.ItemGlovesDexterity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemGlovesDexterity.class)
public abstract class ItemGlovesDexterityMixin {
    @Inject(method = "onEquipmentChange",at = @At("HEAD"),cancellable = true,remap = false)
    private static void disable(LivingEquipmentChangeEvent event, CallbackInfo ci){
        if(event.getSlot() == EquipmentSlotType.MAINHAND && (isDisable(event.getFrom()) || isDisable(event.getTo()))){
            ci.cancel();
        }
    }

    private static boolean isDisable(ItemStack stack){
        Item item = stack.getItem();
        return item instanceof SwordItem || item instanceof AxeItem || item instanceof WarHammerItem || item instanceof DaggerItem;
    }
}
