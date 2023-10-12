#priority 61

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.recipe.Replacer;

import mods.emcworld.ItemUtils;
import mods.emcworld.CrTSingularity;

public function D1Recipes() as void{
    var un = <item:allthemodium:unobtainium_ingot>;
    var ub = <item:emcworld:universal_ball>;
    var mt = <item:mekanism:meka_tool>;
    var us = <item:emcworld:unobtainium_sword>;
    var bases as IIngredient = ItemUtils.getEMCGodItemWithLevel(14);
    var iss = <item:mekanism:ingot_steel>;
    var unqd = new CrTSingularity("unobtainium",3).asIIngredient();
    var armor as IItemStack[] = getModsArmor();
    var baseqd as IIngredient = new CrTSingularity("iron",2).addSingularity("steel","nickel","copper","lead","aluminum","silver").asIIngredient(); 
    var pp2 = <item:mekanism:pellet_plutonium>;
    var pp = <item:mekanism:pellet_polonium>;
    var mhs = <item:mekanism:hdpe_sheet>;
    var ds = <item:emcworld:dragon_steel>;
    var uvai = <item:allthemodium:unobtainium_vibranium_alloy_ingot>;
    var uaai = <item:allthemodium:unobtainium_allthemodium_alloy_ingot>;

    crystallizingGasRecipe(<gas:emcworld:cosmic_flow>*1000,ub);
    for i in 16 .. 20{
        smithingRecipe(armor[i-4],unqd,armor[i]);
    }
    for i in 20 .. 24{
        removeSmithingRecipe(armor[i]);
        smithingRecipe(armor[i-4],<item:emcworld:dragon_steel>*3,armor[i]);
    }
    for i in 24 .. 28{
        extendedCraftingShapedRecipe([
            [baseqd,pp2,pp2,pp2,pp2,pp2,baseqd],
            [pp2,baseqd,mhs,mhs,mhs,baseqd,pp2],
            [pp2,mhs,ub,ub,ub,mhs,pp2],
            [pp2,mhs,ub,armor[i-4],ub,mhs,pp2],
            [pp2,mhs,ub,ub,ub,mhs,pp2],
            [pp2,baseqd,pp,pp,pp,baseqd,pp2],
            [baseqd,pp2,pp2,pp2,pp2,pp2,baseqd]
        ],armor[i],3);
    }
    extendedCraftingShapedRecipe([
        [us,mhs,mhs,mhs,mhs,mhs,us],
        [mhs,baseqd,baseqd,baseqd,baseqd,baseqd,mhs],
        [mhs,baseqd,ub,ub,ub,baseqd,mhs],
        [mhs,baseqd,ub,bases,ub,baseqd,mhs],
        [mhs,baseqd,ub,ub,ub,baseqd,mhs],
        [mhs,baseqd,baseqd,baseqd,baseqd,baseqd,mhs],
        [us,mhs,mhs,mhs,mhs,mhs,us]
    ],mt,3);
    extendedCraftingShapedRecipe([
        [uvai,uvai,uvai,uvai,uvai,uvai,uvai],
        [uvai,uaai,uaai,uaai,uaai,uaai,uvai],
        [uvai,uaai,un,un,un,uaai,uvai],
        [uvai,uaai,un,baseqd,un,uaai,uvai],
        [uvai,uaai,un,un,un,uaai,uvai],
        [uvai,uaai,uaai,uaai,uaai,uaai,uvai],
        [uvai,uvai,uvai,uvai,uvai,uvai,uvai]
    ],unqd,3);
    yellow(<item:projectex:lime_collector>,<item:projectex:yellow_collector>);
    yellow(<item:projectex:lime_relay>,<item:projectex:yellow_relay>);
    infuserRecipe([
        ub,<item:gobber2:dragon_star>,<item:emcworld:hard_steel>,<item:emcworld:hard_steel>,<item:emcworld:hard_steel>
    ],ds,6000,1000000000,4);
}