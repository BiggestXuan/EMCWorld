#priority 54
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.blocks.MCBlock;
import crafttweaker.api.blocks.MCBlockState;

import math.Functions;

public function astralAltarRecipe(input as IIngredient[][],output as IItemStack,level as int) as void{
    var l as string = "";
    var c as int = 0;
    switch (level){
        case 1:
            l = "DISCOVERY";
            c = 50;
            break;
        case 2:
            l = "ATTUNEMENT";
            c = 200;
            break;
        case 3:
            l = "CONSTELLATION";
            c = 500;
            break;
        default:
            l = "RADIANCE";
            c = 2000;
            break;
    }
    <recipetype:astralsorcery:altar>.addRecipe(
        getRecipeName(output)+"_astral_altar",
        l,
        output,
        input,
        50,c
    );
}

public function astralTransmutationRecipe(input as MCBlockState,output as MCBlock) as void{
    <recipetype:astralsorcery:block_transmutation>.addRecipe(output.getRegistryName().getPath()+"_astral_transmutation",output.getDefaultState(),input,true,Functions.round(100 * getDifficultyLoss()));
}

public function astralInfusionRecipe(input as IIngredient,output as IItemStack) as void{
    <recipetype:astralsorcery:infusion>.addRecipe(getRecipeName(output),output,input,<fluid:astralsorcery:liquid_starlight>,100,10,false,true,false);
}

public function removeAstralAltarRecipe(item as IItemStack[]) as void{
    for i in item{
        <recipetype:astralsorcery:altar>.removeByName("astralsorcery:altar/"+i.registryName.path);
    }
}

public function removeAstralTransmutationRecipe(input as MCBlockState) as void{
    <recipetype:astralsorcery:block_transmutation>.removeRecipe(input);
}