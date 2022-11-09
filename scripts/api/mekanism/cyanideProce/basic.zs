#priority 51

import mods.mekanism.api.ingredient.ChemicalStackIngredient.GasStackIngredient;
import mods.mekanism.api.ingredient.ItemStackIngredient;
import mods.mekanism.api.ingredient.FluidStackIngredient;
import mods.mekanism.api.FloatingLong;
import mods.mekanism.api.chemical.GasStack;

import crafttweaker.api.fluid.IFluidStack;
import crafttweaker.api.item.ItemStack;


public function getAir(inputItem as ItemStack,inputFluid as IFluidStack,inputGas as GasStack) as void{
    reactionRecipe(inputItem,inputFluid,inputGas,<item:mekanism:substrate>,<gas:emcworld:air>*1000);
}

public function rotaryRecipe(fluid as IFluidStack,gas as GasStack) as void{
    <recipetype:mekanism:rotary>.addRecipe(getFluidRecipeName(fluid,"rotary"),FluidStackIngredient.from(fluid,1),GasStackIngredient.from(gas,1),gas,fluid);
}

public function separatingRecipe(fluid as IFluidStack,left as GasStack,right as GasStack) as void{
    <recipetype:mekanism:separating>.addRecipe(getFluidRecipeName(fluid,"separating"),fluid,left,right);
}

public function chemicalInfusingRecipe(left as GasStack,right as GasStack,output as GasStack) as void{
    <recipetype:mekanism:chemical_infusing>.addRecipe(getGasRecipeName(output,"chemical_infusing"),GasStackIngredient.from(left),GasStackIngredient.from(right),output);
}

public function reactionRecipe(inputItem as ItemStack,inputFluid as IFluidStack,inputGas as GasStack,outputItem as ItemStack,outputGas as GasStack)as void{
    <recipetype:mekanism:reaction>.addRecipe(getRecipeName(outputItem.asIItemStack())+"_reaction_"+outputItem.asIItemStack().amount,inputItem.asIItemStack(),FluidStackIngredient.from(inputFluid),GasStackIngredient.from(inputGas),100,outputItem.asIItemStack(),outputGas);
}

public function oxidizingRecipe(inputItem as ItemStack,gas as GasStack) as void{
    <recipetype:mekanism:oxidizing>.addRecipe(getRecipeName(inputItem)+"_oxidizing",inputItem.asIItemStack(),gas);
}