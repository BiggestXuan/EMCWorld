#priority 54
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;

import mods.emcworld.Infuser;
import mods.emcworld.DifficultyItem;
import mods.emcworld.EMCHelper;
import mods.emcworld.CrTRequirement;
import mods.emcworld.CrTWeightItem;

public class Ice{
    public static var INSTANCE as Ice = new Ice();

    var sn as IItemStack[]=[
        <item:minecraft:snowball>,
        <item:minecraft:snow_block>,
        <item:minecraft:ice>,
        <item:minecraft:packed_ice>,
        <item:minecraft:blue_ice>,
        <item:emcworld:cold_ingot>
    ];

    public getIce() as IItemStack[]{
        return this.sn;
    }
}

public function getModifyRecipeAmount() as int{
    var amount as int = 6;
    var r as double = getGameDifficulty();
    if(r == 0.5){
        amount = 64;
    }
    else if(r <= 1){
        amount = 16;
    }
    else if(r <= 2){
        amount = 12;
    }
    else if(r <=2.5){
        amount = 10;
    }
    return amount;
}

public function infuserRecipe(input as IIngredient[],output as IItemStack,time as int,emc as int,level as int) as void{
    if(DifficultyItem.isReachDifficulty(output)){
        <recipetype:emcworld:infuser>.addRecipe(getCountRecipeName(output)+"_infuser",input,output,level,time,emc);  
    }
}

public function mythicInfuserRecipe(input as IIngredient[],output as IItemStack,mana as int,color as int,color1 as int) as void{
    <recipetype:mythicbotany:infusion>.addRecipe(getRecipeName(output)+"_mythic_infuser",output,input,mana,color,color1);
}

public function steelFurnaceRecipe(input as IIngredient[],output as IItemStack,time as int) as void{
    //<recipetype:emcworld:steel_furnace>.addRecipe(getRecipeName(output)+"_steel_furnace_"+time,input,output,time);
}

public function EMCWorldRecipe(item as IItemStack,item1 as IItemStack) as void{
    addCraftShapedRecipeNoName([
        [item,item,item],
        [item,<item:minecraft:air>,item],
        [item,item,item]
    ],item1);
}

public function EMCWorldIce(input as IItemStack,amt as int) as void{
    infusionConversionRecipe(input,<infuse_type:emcworld:ice>*amt);
}

public function torchRecipe(i as IItemStack,k as IItemStack,v as IItemStack) as void{
    extendedCraftingShapedRecipe([
        [k,i,i,k,k,k,i,i,k],
        [i,k,i,i,k,i,i,k,i],
        [i,i,k,i,i,i,k,i,i],
        [k,i,i,k,i,k,i,i,k],
        [k,i,i,i,v,i,i,i,k],
        [k,i,i,k,i,k,i,i,k],
        [i,i,k,i,i,i,k,i,i],
        [i,k,i,i,i,i,i,k,i],
        [k,i,i,k,k,k,i,i,k]
    ],<item:torcherino:torcherino>,4);
}

public function torchRecipe1(i1 as int,i2 as int)as void{
    extendedCompressionRecipe(<item:torcherino:torcherino>,<item:torcherino:compressed_torcherino>,i1,1);
    extendedCompressionRecipe(<item:torcherino:compressed_torcherino>,<item:torcherino:double_compressed_torcherino>,i2,1);
}

public function copyFinalIngot(i as IItemStack) as void{
    var bx = <item:emcworld:biggest_xuan_ingot>;
    infuserRecipe([i,bx,bx,bx,bx],i*5,600000,1500000000,4);
}

public function setEMCStage(item as ItemStack,emc as long,stage as int) as void{
    EMCHelper.setItemEMC(item,emc);
    addEMCStage(item.asIItemStack(),stage);
}

public function addEMCStage(item as IItemStack,num as int) as void{
    var stage as string = getStageName(num);
    <recipetype:emcworld:emc_stage_limit>.addRecipe(getRecipeName(item)+"_emc_stage",item,stage);
}

public function setEMCStageName(item as ItemStack,emc as long,stage as int) as void{
    EMCHelper.setItemEMC(item,emc);
    <recipetype:emcworld:emc_stage_limit>.addRecipe(getRecipeName(item)+"_emc_stage_"+emc,item,getStageName(stage));
}

public function staffRecipe(input as IItemStack,output as IItemStack) as void{
    var a = <item:minecraft:air>;
    addCraftShapedRecipe([
        [a,input,<item:emcworld:big_emc_gem>],
        [a,<tag:items:forge:rods/wooden>,input],
        [<tag:items:forge:rods/wooden>,a,a]
    ],output,"_staff");
}

public function crockPotRecipe(re as CrTRequirement,output as IItemStack,pri as int) as void{
    <recipetype:crockpot:crock_pot_cooking>.addRecipe(getRecipeName(output)+"_crock_pot",re,output,pri);
}

public function getStageName(num as int) as string{
    switch(num){
        case 1:
            return "one";
        case 2:
            return "two";
        case 3:
            return "three";
        case 4:
            return "four";
        case 5:
            return "five";
        case 6:
            return "six";
        case 7:
            return "seven";
        case 8:
            return "eight";
        default :
            return "disabled";
    }
}

public function piglinTrade(input as IIngredient,output as CrTWeightItem[]) as void{
    var name as string = getRecipeName(input.items[0]);
    <recipetype:crockpot:piglin_bartering>.addRecipe(name,input,output);
}

public function getColorBlockCount() as int{
    var d = getGameDifficulty();
    if(d == 0.5) return 64;
    if(d <= 1) return 60;
    if(d <= 2) return 48;
    if(d <3) return 32;
    return 16;
}