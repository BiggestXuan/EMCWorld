#priority 60

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;

import mods.emcworld.CrTSingularity;

public function addEMCWorldTimeConstBlockRecipes() as void{
    var con as IItemStack[][] = getConstBlocks();
    var alt = <item:bloodmagic:altarcapacityrune>;
    var sing as IItemStack[][] = getSingularity();
    var a = <item:minecraft:air>;
    var baseqd as IIngredient = new CrTSingularity("iron",2).addSingularity("steel","nickel","copper","lead","aluminum","silver").asIIngredient(); 
    
    infuserRecipe([
        sing[0][21],baseqd,<item:projecte:catalytic_lens>,<item:projecte:gem_of_eternal_density>,con[0][7]
    ],con[1][7],4000,1500000,3);
    addCraftShapedRecipeNoName([
        [a,con[0][0],a],
        [con[0][0],<item:thermal:machine_output_augment>,con[0][0]],
        [a,con[0][0],a]
    ],con[1][0]*4);
    addCraftShapedRecipeNoName([
        [alt,con[0][1],alt],
        [con[0][1],<item:candyworld:teleporter>,con[0][1]],
        [<tag:items:atum:godshards>,con[0][1],<tag:items:atum:godshards>]
    ],con[1][1]*4);
    mythicInfuserRecipe([
        con[0][2],<item:bloodmagic:reagentbinding>,<item:mythicbotany:alfsteel_nugget>
    ],con[1][2],100000,0xffffff,0xffffff);
    infuserRecipe([
        con[0][4],con[0][4],<item:naturesaura:infused_iron>,
        <item:naturesaura:token_euphoria>,
        <item:stalwart_dungeons:tungsten_ingot>
    ],con[1][4]*2,800,200000,2);
    treeRitualRecipe([
        con[0][5],con[0][5],<item:stalwart_dungeons:ancient_fire>,
        <item:cataclysm:ignitium_ingot>,
        <item:thermal:upgrade_augment_3>,
        <item:mekanism:pellet_polonium>
    ],<item:twilightforest:transformation_sapling>,con[1][5]*2);
    infuserRecipe([
        con[0][6],con[0][6],<item:undergarden:cloggrum_block>,
        <item:undergarden:virulent_mix_bucket>,
        <item:stalwart_dungeons:chorundum>
    ],con[1][6]*2,1800,1000000,3);
}