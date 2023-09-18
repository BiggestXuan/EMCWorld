#priority 60

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;

import mods.emcworld.CrTSingularity;

public function addEMCWorldAddonConstBlockRecipes() as void{
    var con as IItemStack[][] = getConstBlocks();
    var a = <item:minecraft:air>;
    var camt = getColorBlockCount();
    var sar = <item:bloodmagic:sacrificerune>;
    var anti = <item:mekanism:pellet_antimatter>;
    var baseqd as IIngredient = new CrTSingularity("iron",2).addSingularity("steel","nickel","copper","lead","aluminum","silver").asIIngredient(); 
    var dm = <item:projecte:dark_matter>;
    var sel = <item:bloodmagic:selfsacrificerune>;

    addCraftShapedRecipeNoName([
        [a,con[0][0],a],
        [con[0][0],<item:botania:conjuration_catalyst>,con[0][0]],
        [a,con[0][0],a]
    ],con[2][0]*4);
    addCraftShapedRecipeNoName([
        [con[0][1],sar,con[0][1]],
        [sel,<item:bloodmagic:apprenticebloodorb>.reuse(),sel],
        [con[0][1],sar,con[0][1]]
    ],con[2][1]*4);
    reactionChamberRecipe(con[0][2],con[2][2],<fluid:emcworld:sodium_cyanide>*5000,<fluid:minecraft:empty>,<item:mythicbotany:nidavellir_rune>,[]);
    infuserRecipe([
        <item:hem:copparite>,<item:mythicbotany:asgard_rune>,<item:minecraft:nether_star>,con[0][4],con[0][4]
    ],con[2][4]*2,300,100000,1);
    treeRitualRecipe([
        con[0][5],con[0][5],
        anti,
        <item:cataclysm:ignitium_ingot>,
        <item:naturesaura:token_euphoria>,
        <item:naturesaura:token_rage>,
        <item:naturesaura:token_terror>,
        <item:naturesaura:token_grief>
    ],<item:emcworld:super_emc_gem>,con[2][5]*2);
    infuserRecipe([
        con[0][6],<item:aerialhell:obsidian_shard>,
        <item:undergarden:regalium_ingot>,
        <item:aerialhell:obsidian_shard>,
        <item:astralsorcery:ritual_link>
    ],con[2][6],3000,1000000,3);
    infuserRecipe([
        baseqd,baseqd,baseqd,baseqd,con[0][7]
    ],con[2][7],5000,10000000,4);
}