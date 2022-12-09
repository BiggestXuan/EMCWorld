#priority 82
import mods.mekanism.api.chemical.SlurryStack;
import mods.mekanism.api.chemical.GasStack;

import crafttweaker.api.fluid.IFluidStack;
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;

import crafttweaker.api.entity.MCEntityType;

public function getRecipeName(item as ItemStack) as string{
    return item.asIItemStack().registryName.getPath()+"_crafting";
}

public function name(item as IItemStack) as string{
    return item.registryName.getPath();
}

public function getFluidRecipeName(fluid as IFluidStack,addon as string) as string{
    return fluid.registryName.path+"_"+addon;
}

public function getSlurryRecipeName(slurry as SlurryStack,addon as string) as string{
    return slurry.getRegistryName().getPath()+"_"+addon;
}

public function getGasRecipeName(gas as GasStack,addon as string) as string{
    return gas.getRegistryName().getPath()+"_"+addon;
}

public function getEntityRecipeName(entity as MCEntityType) as string{
    return entity.registryName.getPath();
}

public function getCountRecipeName(item as ItemStack) as string{
    var c as int = item.asIItemStack().amount;
    return item.asIItemStack().registryName.getPath()+"_"+c;
}