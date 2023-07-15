#priority 76
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;

import mods.extendedcrafting.TableCrafting;
import mods.extendedcrafting.CombinationCrafting;
import mods.extendedcrafting.EnderCrafting;
import mods.extendedcrafting.CompressionCrafting;

import math.Functions;

public function blockRecipe(input as ItemStack[]) as void{
    removeCraftRecipe(input);
    var i = input;
    addNuggetAndBlockRecipe(i[0],i[1],i[2]);
}

public function enderCraftingRecipe(input as IIngredient[][],output as IItemStack) as void{
    EnderCrafting.addShaped(getRecipeName(output)+"_ender_crafting",output,input,Functions.round(50.0d * getDifficultyLoss())as int);
}

public function enderCraftingShapelessRecipe(input as IIngredient[],output as IItemStack) as void{
    EnderCrafting.addShapeless(getRecipeName(output)+"_ender_crafting",output,input,Functions.round(50.0d * getDifficultyLoss())as int);
}

public function extendedCraftingShapedRecipe(input as IIngredient[][],output as IItemStack,tier as int) as void{
    TableCrafting.addShaped(getRecipeName(output)+"_extended_crafting_"+tier,tier,output,input);
}

public function extendedCraftingShapelessRecipe(input as IIngredient[],output as IItemStack,tier as int) as void{
    TableCrafting.addShapeless(getRecipeName(output)+"_extended_crafting_"+tier,tier,output,input);
}

public function removeExtendedCraftRecipe(item as IItemStack) as void{
    TableCrafting.remove(item);
}

public function extendedCombinationRecipe(input as IIngredient[],output as IItemStack) as void{
    CombinationCrafting.addRecipe(getRecipeName(output),output,100000,input);
}

public function extendedCompressionRecipe(input as IIngredient,output as IItemStack,amount as int,addon as int) as void{
    CompressionCrafting.addRecipe(getRecipeName(output)+"_compression_"+addon,input,output,amount,<item:extendedcrafting:ultimate_catalyst>,500000);
}

public function removeCompressionRecipe(output as IItemStack) as void{
    CompressionCrafting.remove(output);
}

public function modifyExtendedCompressionRecipe(input as IIngredient,output as IItemStack,amount as int,addon as int) as void{
    removeCompressionRecipe(output);
    extendedCompressionRecipe(input,output,amount,addon);
}