package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/15
 */

import biggestxuan.emcworld.api.item.IUpgradeableItem;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import mekanism.common.item.gear.ItemSpecialArmor;
import mekanism.common.item.interfaces.IModeItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemMekaSuitArmor.class)
public abstract class ItemMekaSuitArmorMixin extends ItemSpecialArmor implements IModuleContainerItem, IModeItem, IUpgradeableItem {
    protected ItemMekaSuitArmorMixin(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public int getLevel(ItemStack stack) {
        return getMaxLevel();
    }

    @Inject(method = "tryAbsorbAll",at = @At("HEAD"),cancellable = true,remap = false)
    private static void _inject(PlayerEntity player, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
    }

    @Inject(method = "getDamageAbsorbed*",at = @At("HEAD"),cancellable = true,remap = false)
    private static void _inject1(PlayerEntity player, DamageSource source, float amount, List<Runnable> energyUseCallbacks, CallbackInfoReturnable<Float> cir){
        cir.setReturnValue(0F);
    }
}
