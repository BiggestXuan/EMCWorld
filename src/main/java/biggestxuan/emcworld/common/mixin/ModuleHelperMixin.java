package biggestxuan.emcworld.common.mixin;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/04/15
 */

import biggestxuan.emcworld.common.registry.EWModules;
import mekanism.api.gear.IModuleHelper;
import mekanism.api.gear.ModuleData;
import mekanism.common.content.gear.ModuleHelper;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import mekanism.common.item.gear.ItemMekaTool;
import mekanism.common.registries.MekanismItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;
import java.util.*;

@Mixin(ModuleHelper.class)
public abstract class ModuleHelperMixin implements IModuleHelper {
    @Shadow (remap = false)
    @Final
    private Map<ModuleData<?>, Set<Item>> supportedContainers;

    @Shadow (remap = false)
    @Final
    private Map<Item, Set<ModuleData<?>>> supportedModules;

    @Inject(method = "processIMC",at = @At("RETURN"),remap = false)
    public void _inject(CallbackInfo ci){
        List<Item> itemList = new ArrayList<>();
        itemList.add(MekanismItems.MEKASUIT_HELMET.getItem());
        itemList.add(MekanismItems.MEKASUIT_BODYARMOR.getItem());
        itemList.add(MekanismItems.MEKASUIT_PANTS.getItem());
        itemList.add(MekanismItems.MEKASUIT_BOOTS.getItem());
        HashSet<Item> set = new HashSet<>(itemList);
        supportedContainers.put(EWModules.ENERGY_PROTECT_MODULE.getModuleData(),set);
        HashSet<Item> set1 = new HashSet<>();
        set1.add(MekanismItems.MEKA_TOOL.getItem());
        supportedContainers.put(EWModules.INFINITY_MODULE_UNIT_MODULE.getModuleData(),set1);
    }

    /**
     * @author Biggest_Xuan
     * @reason Add something.
     */
    @Nonnull
    @Overwrite(remap = false)
    @Override
    public Set<ModuleData<?>> getSupported(ItemStack container) {
        Set<ModuleData<?>> set = supportedModules.getOrDefault(container.getItem(), Collections.emptySet());
        Set<ModuleData<?>> set1 = new HashSet<>(set);
        if(container.getItem() instanceof ItemMekaSuitArmor){
            set1.add(EWModules.ENERGY_PROTECT_MODULE.getModuleData());
        }
        if(container.getItem() instanceof ItemMekaTool){
            set1.add(EWModules.INFINITY_MODULE_UNIT_MODULE.getModuleData());
        }
        return set1;
    }
}
