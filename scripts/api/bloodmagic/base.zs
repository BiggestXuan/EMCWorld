#priority 54
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.fluid.IFluidStack;
import crafttweaker.api.item.MCWeightedItemStack;

import math.Functions;

public function bloodAltarRecipe(input as IIngredient,output as IItemStack,costs as int,level as int) as void{
    var recipeName = getRecipeName(output)+"_blood_altar";
    var cost = Functions.round(costs * getDifficultyLoss()) as int;
    var costRate = cost / 100;
    var drainRate = cost / 2000;
    <recipetype:bloodmagic:altar>.addRecipe(recipeName,output,input,level,cost,costRate,drainRate);
}

public function modifyAltarRecipe(input as IIngredient,output as IItemStack,costs as int,level as int) as void{
    <recipetype:bloodmagic:altar>.removeRecipe(output);
    bloodAltarRecipe(input,output,costs,level);
}

public function tartaricForgeRecipe(input as IIngredient[],output as IItemStack,m as double,c as double) as void{
    var min = Functions.min(Functions.round(m * getDifficultyLoss()) as int,4096);
    var cost = Functions.min(Functions.round(c * getDifficultyLoss()) as int,4096);
    <recipetype:bloodmagic:soulforge>.addRecipe(getRecipeName(output)+"_tartaric_forge",output,input,min,cost);
}

public function reactionChamberRecipe(input as IIngredient,output as IItemStack,inputFluid as IFluidStack,outputFluid as IFluidStack,tool as IIngredient,addedItems as MCWeightedItemStack[]) as void{
    <recipetype:bloodmagic:arc>.addRecipe(getRecipeName(output)+"_reaction_chamber",output,outputFluid,input,inputFluid,<tag:items:bloodmagic:arc/tool>,false,addedItems);
}

public function alchemalTableRecipe(input as IIngredient[],output as IItemStack,cost as int,tier as int) as void{
    var costC = Functions.round(cost * getDifficultyLoss()) as int;
    <recipetype:bloodmagic:alchemytable>.addRecipe(getRecipeName(output)+"_alchemal_table",output,input,costC,100,tier);
}

public function removeAlchemalTableRecipe(input as IItemStack[]) as void{
    for i in input{
        <recipetype:bloodmagic:alchemytable>.removeRecipe(i);
    }
}

public function removeTartaricForgeRecipe(input as IItemStack[]) as void{
    for i in input{
        <recipetype:bloodmagic:soulforge>.removeRecipe(i);
    }
}

public function alchemalArrayRecipe(base as IIngredient,added as IIngredient,output as IItemStack) as void{
    <recipetype:bloodmagic:array>.addRecipe(getRecipeName(output)+"_alchemal_array",output,base,added,<resource:bloodmagic:textures/models/alchemyarrays/lavasigil.png>);
}

public function removeAlchemalArrayRecipe(input as IItemStack[]) as void{
    for i in input{
        <recipetype:bloodmagic:array>.removeRecipe(i);
    }
}