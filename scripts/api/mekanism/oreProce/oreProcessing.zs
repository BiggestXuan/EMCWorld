#priority 48
import mods.mekanism.api.ingredient.ChemicalStackIngredient.GasStackIngredient;
import mods.mekanism.api.ingredient.ChemicalStackIngredient.SlurryStackIngredient;
import mods.mekanism.api.ingredient.ItemStackIngredient;
import mods.mekanism.api.ingredient.FluidStackIngredient;
import mods.mekanism.api.chemical.ChemicalStack;
import mods.mekanism.api.chemical.InfusionStack;
import mods.mekanism.api.chemical.GasStack;
import mods.mekanism.api.chemical.PigmentStack;
import mods.mekanism.api.chemical.SlurryStack;

import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.fluid.IFluidStack;

public function DLRecipeSlurry(input as ItemStack,gasInput as GasStack,output as SlurryStack,addon as string) as void{
    var recipeName as string = getRecipeName(input)+"_dissolution"+addon;
    <recipetype:mekanism:dissolution>.addRecipe(recipeName,ItemStackIngredient.from(input.asIItemStack()),GasStackIngredient.from(gasInput),output);
}

public function DLRecipeGas(input as ItemStack,gasInput as GasStack,output as GasStack) as void{
    var recipeName as string = getRecipeName(input)+"_dissolution";
    <recipetype:mekanism:dissolution>.addRecipe(recipeName,ItemStackIngredient.from(input.asIItemStack()),GasStackIngredient.from(gasInput),output);
}

public function washingRecipe(fluid as IFluidStack,input as SlurryStack,output as SlurryStack) as void{
    var recipeName as string = getSlurryRecipeName(input,"washing");
    <recipetype:mekanism:washing>.addRecipe(recipeName,FluidStackIngredient.from(fluid),SlurryStackIngredient.from(input),output);
}

public function crystallizingRecipe(slurry as SlurryStack,output as ItemStack) as void{
    var recipeName as string = getSlurryRecipeName(slurry,"crystallizing");
    <recipetype:mekanism:crystallizing>.addRecipe(recipeName,SlurryStackIngredient.from(slurry),output);
}

public function crystallizingGasRecipe(slurry as GasStack,output as ItemStack) as void{
    var recipeName as string = getRecipeName(output)+"_crystallizing";
    <recipetype:mekanism:crystallizing>.addRecipe(recipeName,GasStackIngredient.from(slurry),output);
}

public function injectingRecipe(input as ItemStack,output as ItemStack,rate as int) as void {
    var recipeName as string = getRecipeName(input)+"_injecting";
    <recipetype:mekanism:injecting>.addRecipe(recipeName,input.asIItemStack(),GasStackIngredient.from(<gas:mekanism:hydrogen_chloride>, 1),output.asIItemStack()*rate);
}

public function purifyingRecipe(input as ItemStack,output as ItemStack,rate as int) as void{
    var recipeName as string = getRecipeName(input)+"_purifying";
    <recipetype:mekanism:purifying>.addRecipe(recipeName,input.asIItemStack(),<gas:mekanism:oxygen>,output.asIItemStack()*rate);
}

public function crushingRecipe(input as ItemStack,output as ItemStack,rate as int) as void{
    var recipeName as string = getRecipeName(input)+"_crashing";
    <recipetype:mekanism:crushing>.addRecipe(recipeName,input.asIItemStack(),output.asIItemStack()*rate);
}

public function enrichingRecipe(input as ItemStack,output as ItemStack,rate as int) as void{
    var recipeName as string = getRecipeName(input)+"_enriching";
    <recipetype:mekanism:enriching>.addRecipe(recipeName,input.asIItemStack(),output.asIItemStack()*rate);
}

public function combiningRecipe(input as ItemStack,extra as ItemStack,output as ItemStack) as void{
    var recipeName as string = getRecipeName(input)+"_combining";
    <recipetype:mekanism:combining>.addRecipe(recipeName,input.asIItemStack(),extra.asIItemStack(),output.asIItemStack());
}

public function blastRecipe(output as ItemStack,input as ItemStack) as void{
    var name as string = input.asIItemStack().registryName.getPath()+"_blast";
    blastFurnace.addRecipe(name,output,input,0.3,100);
}

public function infusionConversionRecipe(input as ItemStack,output as InfusionStack) as void{
    var recipeName as string = getRecipeName(input)+"_infusion_conversion";
    <recipetype:mekanism:infusion_conversion>.addRecipe(recipeName,input.asIItemStack(),output);
}

public function compressingRecipe(input as ItemStack,output as ItemStack) as void{
    <recipetype:mekanism:compressing>.addRecipe(getRecipeName(output)+"_compressing",input.asIItemStack(),GasStackIngredient.from(<gas:mekanism:liquid_osmium>),output);
}

public function pigmentRecipe(input as ItemStack,output as PigmentStack) as void{
    <recipetype:mekanism:pigment_extracting>.addRecipe(getRecipeName(input)+"_pigment",input.asIItemStack(),output);
}

public function metallurgicInfusingRecipe(input as ItemStack,inputInfuse as InfusionStack,output as ItemStack) as void{
    var recipeName as string = getRecipeName(input)+"_metallurgic_infusing_"+output.asIItemStack().amount;
    <recipetype:mekanism:metallurgic_infusing>.addRecipe(recipeName,input.asIItemStack(),inputInfuse,output.asIItemStack());
}

public function addNormalOreProceRecipe(i_117525_0 as ItemStack,dirty_slurry as SlurryStack,clean_slurry as SlurryStack,crystal as ItemStack,shard as ItemStack,clump as ItemStack,dirty_dust as ItemStack,dust as ItemStack,ingot as ItemStack)as void{
    DLRecipeSlurry(i_117525_0.asIItemStack(),<gas:emcworld:cosmic_flow>,dirty_slurry*3000,"_cosmic");
    DLRecipeSlurry(i_117525_0.asIItemStack(),<gas:emcworld:hydrogen_cyanide>,dirty_slurry*1600,"_hydrogen");
    DLRecipeSlurry(i_117525_0.asIItemStack(),<gas:mekanism:sulfuric_acid>,dirty_slurry*1000,"_acid");
    washingRecipe(<fluid:minecraft:water>*10,dirty_slurry,clean_slurry);
    crystallizingRecipe(clean_slurry*200,crystal.asIItemStack());
    injectingRecipe(crystal.asIItemStack(),shard.asIItemStack(),1);
    purifyingRecipe(shard.asIItemStack(),clump.asIItemStack(),1);
    crushingRecipe(clump.asIItemStack(),dirty_dust.asIItemStack(),1);
    enrichingRecipe(dirty_dust.asIItemStack(),dust.asIItemStack(),1);
    removeFurnaceRecipe([ingot]);
    furnaceRecipe(getRecipeName(i_117525_0)+"_processing_furnace",ingot.asIItemStack(),dust.asIItemStack(), 0.2, 200);
    furnaceRecipe(getRecipeName(i_117525_0)+"_inst_furnace",ingot.asIItemStack(),i_117525_0.asIItemStack(),0.2, 200);
    blastRecipe(ingot.asIItemStack(),i_117525_0.asIItemStack());
    blastRecipe(ingot.asIItemStack(),dust.asIItemStack());
    injectingRecipe(i_117525_0.asIItemStack(),shard.asIItemStack(),4);
    purifyingRecipe(i_117525_0.asIItemStack(),clump.asIItemStack(),3);
    enrichingRecipe(i_117525_0.asIItemStack(),dust.asIItemStack(),2);
}