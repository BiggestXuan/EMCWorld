#priority 48
import mods.mekanism.api.ingredient.ChemicalStackIngredient.SlurryStackIngredient;
import mods.mekanism.api.ingredient.ChemicalStackIngredient.GasStackIngredient;
import mods.mekanism.api.ingredient.ItemStackIngredient;
import mods.mekanism.api.ingredient.FluidStackIngredient;

import crafttweaker.api.item.ItemStack;

public function emcOreProcessing(ore as emcOres) as void{
    var input as ItemStackIngredient = ItemStackIngredient.from(ore.getItem(),1);
    var recipeName as string= ore.getItem().asIItemStack().registryName.getPath();
    var rate = ore.getRate();
    DLRecipeSlurry(ore.getItem(),<gas:emcworld:cosmic_flow>,<slurry:emcworld:dirty_emc_gem>*3000*rate,"_cosmic");
    DLRecipeSlurry(ore.getItem(),<gas:emcworld:hydrogen_cyanide>,<slurry:emcworld:dirty_emc_gem>*1600*rate,"_sodium");
    DLRecipeSlurry(ore.getItem(),<gas:mekanism:sulfuric_acid>,<slurry:emcworld:dirty_emc_gem>*1000*rate,"_acid");
    var gemStack as ItemStack[]=[
        <item:emcworld:crystal_emc_gem>,
        <item:emcworld:shard_emc_gem>,
        <item:emcworld:clump_emc_gem>,
        <item:emcworld:dirty_dust_emc_gem>,
        <item:emcworld:dust_emc_gem>,
        <item:emcworld:big_emc_gem>
    ];
    var inputOre as ItemStack = ore.getItem();
    if(rate == 1){
        washingRecipe(<fluid:minecraft:water>*10,<slurry:emcworld:dirty_emc_gem>,<slurry:emcworld:clean_emc_gem>);
        crystallizingRecipe(<slurry:emcworld:clean_emc_gem>*200,<item:emcworld:crystal_emc_gem>);
        injectingRecipe(gemStack[0],gemStack[1],1);
        purifyingRecipe(gemStack[1],gemStack[2],1);
        crushingRecipe(gemStack[2],gemStack[3],1);
        enrichingRecipe(gemStack[3],gemStack[4],1);
        furnaceRecipe(recipeName+"_processing_furnace",gemStack[5],<item:emcworld:dust_emc_gem>, 0.2, 200);
    }
    injectingRecipe(inputOre,gemStack[1],rate*4);
    purifyingRecipe(inputOre,gemStack[2],rate*3);
    enrichingRecipe(inputOre,gemStack[4],rate*2);
    furnaceRecipeNoName(gemStack[5].asIItemStack()*rate,ore.getItem(),1);
}