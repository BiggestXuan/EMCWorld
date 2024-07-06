#priority 82
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;

import mods.recipestages.Recipes;
import mods.emcworld.configHelper;

public function addCraftShapedRecipe(input as IIngredient[][],output as ItemStack,addon as string) as void{
    craftingTable.addShaped(getRecipeName(output)+addon,output,input);
}

public function addCraftShapedMirrorRecipe(input as IIngredient[][],output as ItemStack) as void{
    craftingTable.addShapedMirrored(getRecipeName(output),output,input);
}

public function addCraftShapedRecipeNoName(input as IIngredient[][],output as ItemStack) as void{
    var amount = output.asIItemStack().amount;
    if(amount == 1){
        craftingTable.addShaped(getRecipeName(output),output,input);
        return;
    }
    craftingTable.addShaped(getRecipeName(output)+output.asIItemStack().amount,output,input);
}

public function modifyShapedRecipe(input as IIngredient[][],output as ItemStack) as void{
    removeCraftRecipe([output]);
    addCraftShapedRecipeNoName(input,output);
}

public function modifyShapelessRecipe(input as IIngredient[],output as ItemStack) as void{
    removeCraftRecipe([output]);
    addCraftShapelessRecipe(input,output);
}

public function addCraftShapelessRecipe(input as IIngredient[],output as ItemStack) as void{
    var amount = output.asIItemStack().amount;
    if(amount == 1){
        craftingTable.addShapeless(getRecipeName(output),output,input);
        return;
    }
    craftingTable.addShapeless(getRecipeName(output)+output.asIItemStack().amount,output,input);
}

public function removeFurnaceRecipeByName(name as string[]) as void{
    for i in name{
        furnace.removeByName(i);
    }
}

public function removeCraftRecipe(input as ItemStack[]) as void{
    for item in input{
        craftingTable.removeRecipe(item.asIItemStack());
    }
}

public function removeCraftRecipeIItemStack(input as IItemStack[]) as void{
    for i in input{
       craftingTable.removeRecipe(i); 
    }
}

public function removeCraftRecipeByName(input as string[]) as void{
    for name in input{
        craftingTable.removeByName(name);
    }
}

public function removeFurnaceRecipe(input as ItemStack[]) as void{
    for item in input{
        furnace.removeRecipe(item.asIItemStack());
        smoker.removeRecipe(item.asIItemStack());
        blastFurnace.removeRecipe(item.asIItemStack());
    }
}

public function furnaceRecipe(name as string,output as ItemStack,input as ItemStack,exp as double,time as int) as void{
    furnace.addRecipe(name,output,input,exp,time);
}

public function furnaceRecipeNoName(output as ItemStack,input as ItemStack,nya as int) as void{
    var name as string = input.asIItemStack().registryName.getPath()+"_furnace";
    furnace.addRecipe(name,output,input,0.3,200);
    if(nya==1){
        blastFurnace.addRecipe(name+"_blast",output,input,0.3,100);
    }
}

public function multiFurnaceRecipe(input as IIngredient,output as ItemStack) as void{
    furnace.addRecipe(getRecipeName(output)+"_furnace",output,input,0.2,200);
}

public function removeAllRecipe(item as ItemStack[]) as void{
    removeCraftRecipe(item);
    removeFurnaceRecipe(item);
    for i in item{
        removeSmelterRecipe(i);
    }
}

public function modifyStageRecipe(input as IIngredient[][],output as ItemStack,stage as string) as void{
    removeCraftRecipe([output]);
    var s = stage;
    if(configHelper.isFreeMode()){
        s = "start";
    }
    var recipeName = getRecipeName(output)+"_stage_"+s;
    Recipes.addShaped(s,recipeName,output,input);
}

public function addNuggetAndIngotRecipe(nugget as ItemStack,ingot as ItemStack) as void{
    var n = nugget;
    addCraftShapedRecipe([
        [n,n,n],
        [n,n,n],
        [n,n,n]
    ],ingot,"_ingot");
    addCraftShapelessRecipe([ingot],nugget.asIItemStack()*9);
}

public function addNuggetAndBlockRecipe(nugget as ItemStack,ingot as ItemStack,block as ItemStack) as void{
    addNuggetAndIngotRecipe(nugget,ingot);
    var i = ingot;
    addCraftShapedRecipe([
        [i,i,i],
        [i,i,i],
        [i,i,i]
    ],block,"_block");
    addCraftShapelessRecipe([block],ingot.asIItemStack()*9);
}

public function craftTogether(input as ItemStack[]) as void{
    for i in 0 .. input.length-1{
        addCraftShapelessRecipe([input[i]],input[i+1]);
    }
    addCraftShapelessRecipe([input[input.length-1]],input[0]);
}

public function smithingRecipe(input as IIngredient,input1 as IIngredient,output as IItemStack) as void{
    smithing.addRecipe(getRecipeName(output)+"_smithing",output,input,input1);
}

public function removeSmithingRecipe(output as IItemStack) as void{
    smithing.removeRecipe(output);
}

public function helmetRecipe(i as IItemStack,output as ItemStack) as void{
    var a = <item:minecraft:air>;
    addCraftShapedRecipeNoName([
        [i,i,i],
        [i,a,i],
        [a,a,a]
    ],output);
}

public function chestRecipe(i as IItemStack,output as ItemStack) as void{
    var a = <item:minecraft:air>;
    addCraftShapedRecipeNoName([
        [i,a,i],
        [i,i,i],
        [i,i,i]
    ],output);
}

public function legRecipe(i as IItemStack,output as ItemStack) as void{
    var a = <item:minecraft:air>;
    addCraftShapedRecipeNoName([
        [i,i,i],
        [i,a,i],
        [i,a,i]
    ],output);
}

public function bootRecipe(i as IItemStack,output as ItemStack) as void{
    var a = <item:minecraft:air>;
    addCraftShapedRecipeNoName([
        [i,a,i],
        [i,a,i],
        [a,a,a]
    ],output);
}

public function warHammer(i as IItemStack,o as IItemStack) as void{
    var be = <item:emcworld:big_emc_gem>;
    var a = <item:minecraft:air>;
    addCraftShapedRecipeNoName([
        [i,be,i],
        [i,be,i],
        [a,<item:minecraft:stick>,a]
    ],o);
}

public function gun(aa as IItemStack,b as IItemStack) as void{
    var a = <item:minecraft:air>;
    addCraftShapedRecipeNoName([
        [aa,aa,aa],
        [aa,a,a],
        [aa,a,a]
    ],b);
}

public function dagger(aa as IItemStack,b as IItemStack) as void{
    var a = <item:minecraft:air>;
    var s = <tag:items:forge:rods/wooden>;
    addCraftShapedRecipeNoName([
        [a,a,aa],
        [a,aa,a],
        [s,a,a]
    ],b);
}