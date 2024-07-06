#priority 45
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.recipe.Replacer;

import mods.mekanism.api.ingredient.ItemStackIngredient;
import mods.mekanism.api.chemical.GasStack;
import math.Functions;

public function removeInfusionRecipe(name as string[]) as void{
    for i in name{
        <recipetype:mekanism:metallurgic_infusing>.removeByName(i);
    }
}

public function removeCompressedRecipe(input as string[]) as void{
    for i in input{
        <recipetype:mekanism:compressing>.removeByName(i);
    }
}

public function removeReactionRecipe(input as string[]) as void{
    for i in input{
        <recipetype:mekanism:reaction>.removeByName(i);
    }
}

public function removeMetallurgicRecipe(input as string[]) as void{
    for i in input{
        <recipetype:mekanism:metallurgic_infusing>.removeByName(i);
    }
}

public function addGobberInfusionRecipe(input as ItemStack,output as ItemStack,cost as int) as void{
    metallurgicInfusingRecipe(input,<infuse_type:emcworld:gobber>*cost,output);
}

public function nucleosyRecipe(input as IIngredient,gas as GasStack,output as IItemStack,time as int) as void{
    <recipetype:mekanism:nucleosynthesizing>.addRecipe(getRecipeName(output),ItemStackIngredient.from(input),gas,output,time);
}

public function iron(input as IItemStack,amt as int) as void{
    infusionConversionRecipe(input,<infuse_type:emcworld:iron>*amt);
}

public function nickel(input as IItemStack,amt as int) as void{
    infusionConversionRecipe(input,<infuse_type:emcworld:nickel>*amt);
}

public function silver(input as IItemStack,amt as int) as void{
    infusionConversionRecipe(input,<infuse_type:emcworld:silver>*amt);
}

public function ender(input as IItemStack,amt as int) as void{
    infusionConversionRecipe(input,<infuse_type:emcworld:ender>*amt);
}

public function gobber(input as IItemStack,amt as int) as void{
    infusionConversionRecipe(input,<infuse_type:emcworld:gobber>*amt);
}

public function hellforged(input as IItemStack,amt as int) as void{
    infusionConversionRecipe(input,<infuse_type:emcworld:hellforged>*amt);
}