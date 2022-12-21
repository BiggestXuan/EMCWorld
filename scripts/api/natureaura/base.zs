#priority 56
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IIngredient;

import crafttweaker.api.entity.MCEntityType;

private function getAuraType(type as int) as string{
    switch(type){
        case 1:
            return "overworld";
        case 2:
            return "nether";
        case 3:
            return "end";
        default:
            return "other";
    }
}

public function natureAltarRecipe(input as IIngredient,output as ItemStack,type as int,aura as int) as void{
    <recipetype:naturesaura:altar>.addRecipe(getRecipeName(output)+"_nature_altar",output.asIItemStack(),input,type,aura);
}

public function removeNatureAltarRecipe(output as IItemStack[]) as void{
    for i in output{
        <recipetype:naturesaura:altar>.removeRecipe(i);
    }
}

public function offeringRecipe(input as IIngredient,input1 as IIngredient,output as IItemStack) as void{
    <recipetype:naturesaura:offering>.addRecipe(getRecipeName(output)+"_offering",input,input1,output);
}

public function natureSpawnerRecipe(input as IIngredient[],aura as int,entity as MCEntityType,name as string) as void{
    <recipetype:naturesaura:animal_spawner>.addRecipe(getEntityRecipeName(entity)+"_nature_spawning_"+name,entity,aura,200,input );
}

public function treeRitualRecipe(inputs as IIngredient[],sapling as IIngredient,output as IItemStack) as void{
    <recipetype:naturesaura:tree_ritual>.addRecipe(getRecipeName(output)+"_tree_ritual",<item:minecraft:oak_sapling>,output,inputs);
}

public function removeTreeRitualRecipe(output as IItemStack[]) as void{
    for i in output{
        <recipetype:naturesaura:tree_ritual>.removeRecipe(i);
    }
}

public function removeOfferingRecipe(output as IItemStack[]) as void{
    for i in output{
        <recipetype:naturesaura:offering>.removeRecipe(i);
    }
}