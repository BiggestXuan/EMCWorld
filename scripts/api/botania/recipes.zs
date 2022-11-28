#priority 53
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.Ingredient;
import crafttweaker.api.item.IIngredient;
import mods.botania.PureDaisy;
import mods.botania.PetalApothecary;

import math.Functions;

public function manaInfusionRecipe(input as Ingredient,output as ItemStack,mana as int) as void{
    val manaC as int= mana * getAdditionCost();
    <recipetype:botania:mana_infusion>.addRecipe(getRecipeName(output)+"_mana_infusion",output.asIItemStack(),input.asIIngredient(),manaC);
}

public function removeManaInfusionRecipe(input as ItemStack[]) as void{
    for item in input{
        <recipetype:botania:mana_infusion>.removeRecipe(item.asIItemStack());
    }
}

public function runeAltarRecipe(input as IIngredient[],output as IItemStack,mana as int) as void{
    val manaC as int= mana * getAdditionCost();
    var recipeName = getRecipeName(output)+"_"+output.amount;
    <recipetype:botania:runic_altar>.addRecipe(recipeName,output,manaC,input);
}

public function removeRuneAltarRecipe(output as IItemStack) as void{
    <recipetype:botania:runic_altar>.removeRecipe(output);
}

public function modifyRuneAltarRecipe(input as IIngredient[],output as IItemStack,mana as int) as void{
    <recipetype:botania:runic_altar>.removeRecipe(output);
    runeAltarRecipe(input,output,mana);
}

public function modifyDaisyRecipe() as void{
    <recipetype:botania:pure_daisy>.addRecipe("marble_to_living_rock",<blockstate:botania:livingrock>,<tag:blocks:forge:marble>,70 * getAdditionCost());
}

public function terraPlateRecipe(input as IIngredient[],output as IItemStack,mana as int) as void{
    val recipeName = getRecipeName(output);
    val manaC as int= mana * getAdditionCost();
    <recipetype:botania:terra_plate>.addRecipe(recipeName+"_terra_plate",output,manaC,input);
}

public function apothecaryRecipe(input as IIngredient[], output as IItemStack) as void{
    <recipetype:botania:petal_apothecary>.addRecipe(getRecipeName(output)+"_apothecary",output,input);
}

public function removeApothecaryRecipe(output as IItemStack) as void{
    <recipetype:botania:petal_apothecary>.removeRecipe(output);
}

public function modifyApothecaryRecipe(input as IIngredient[],output as IItemStack) as void{
    removeApothecaryRecipe(output);
    apothecaryRecipe(input,output);
}