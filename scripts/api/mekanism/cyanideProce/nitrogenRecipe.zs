#priority 49
import mods.mekanism.api.ingredient.ChemicalStackIngredient.GasStackIngredient;
import mods.mekanism.api.ingredient.ItemStackIngredient;
import mods.mekanism.api.ingredient.FluidStackIngredient;
import mods.mekanism.api.chemical.GasStack;

import mods.emcworld.math;

import crafttweaker.api.fluid.IFluidStack;
import crafttweaker.api.item.ItemStack;
import math.Functions;


public function addNitrogenRecipe() as void{
    var amt as int = math.getDifficultyMulti(150);
    DLRecipeGas(<item:minecraft:end_stone>,<gas:mekanism:sulfuric_acid>,<gas:emcworld:end_air>*amt);
    getAir(<item:emcworld:activated_charcoal>,<fluid:minecraft:water>*10,<gas:emcworld:end_air>*1000);
    rotaryRecipe(<fluid:emcworld:air>,<gas:emcworld:air>);
    separatingRecipe(<fluid:emcworld:air>*5,<gas:mekanism:oxygen>,<gas:emcworld:nitrogen>*4);
    oxidizingRecipe(<item:botania:ender_air_bottle>,<gas:emcworld:end_air>*amt);
}