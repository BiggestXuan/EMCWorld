#priority 54
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;

import math.Functions;

public function modifyExtendedCraftingRecipe() as void{
    var a = <item:minecraft:air>;
    var ct = <item:minecraft:crafting_table>;
    var bs = <item:extendedcrafting:black_iron_slate>;
    var lm = <item:extendedcrafting:luminessence>;
    var c1 = <item:extendedcrafting:basic_component>;
    var c2 = <item:extendedcrafting:ender_component>;
    var c3 = <item:extendedcrafting:advanced_component>;
    var c4 = <item:extendedcrafting:elite_component>;
    var cc1 = <item:extendedcrafting:basic_catalyst>;
    var cc2 = <item:extendedcrafting:ender_catalyst>;
    var cc3 = <item:extendedcrafting:advanced_catalyst>;
    var cc4 = <item:extendedcrafting:elite_catalyst>;
    var s = <item:mekanism:ingot_steel>;
    var end = <item:extendedcrafting:ender_ingot>;
    var si = <item:naturesaura:sky_ingot>;
    var sc = <item:mekanism:steel_casing>;
    var ec = <item:extendedcrafting:elite_component>;
    var an = <item:mythicbotany:alfsteel_nugget>;
    var ki = <item:theabyss:knight_ingot>;
    var com = <item:extendedcrafting:compressor>;
    var gl = <tag:items:forge:glass>;
    var fr = <item:extendedcrafting:frame>;
    var ei = <item:thermal:electrum_ingot>;
    var et = <item:extendedcrafting:elite_table>;
    var eec = <item:extendedcrafting:elite_catalyst>;
    var at = <item:extendedcrafting:advanced_table>;
    removeRecipe([et]);
    extendedCraftingShapedRecipe([
        [a,a,a,a,a],
        [a,c4,cc4,c4,a],
        [a,ct,<item:mekanism:pellet_antimatter>,ct,a],
        [a,c4,<item:mekanism:sps_port>,c4,a],
        [a,a,a,a,a]
    ],et,2);
    modifyShapedRecipe([
        [ki,cc4,ki],
        [cc4,fr,cc4],
        [ki,ki,ki]
    ],com);
    modifyShapedRecipe([
        [ki,gl,ki],
        [gl,bs,gl],
        [ki,gl,ki]
    ],fr);
    modifyShapedRecipe([
        [si,ec,si],
        [ec,<item:mekanism:pellet_antimatter>,ec],
        [si,ec,si]
    ],eec);
    removeCraftRecipe([
        lm,
        bs,
        <item:extendedcrafting:basic_component>,
        <item:extendedcrafting:ender_crafter>,
        at
    ]);
    tartaricForgeRecipe([
        <item:aether:golden_amber>,
        <item:minecraft:glowstone_dust>,
        <item:botania:mana_powder>,
        <item:mekanism:dust_gold>
    ],<item:extendedcrafting:luminessence>*2,65,5);
    blockRecipe([
        <item:extendedcrafting:ender_nugget>,
        <item:extendedcrafting:ender_ingot>,
        <item:extendedcrafting:ender_ingot_block>
    ]);
    blockRecipe([
        <item:extendedcrafting:black_iron_nugget>,
        <item:extendedcrafting:black_iron_ingot>,
        <item:extendedcrafting:black_iron_block>
    ]);
    reactionChamberRecipe(
        <item:botania:manasteel_ingot>,
        <item:extendedcrafting:ender_ingot>,
        <fluid:thermal:ender>*250,
        <fluid:minecraft:empty>,
        <tag:items:bloodmagic:arc/resonator>,
        [<item:minecraft:ender_pearl>%50]
    );
    compressingRecipe(<item:mekanism:enriched_iron>,<item:extendedcrafting:black_iron_ingot>);
    combiningRecipe(<item:extendedcrafting:black_iron_ingot>,<item:mekanism:ingot_osmium>,bs);
    addCraftShapelessRecipe([
        bs,
        lm,
        <item:thermal:invar_ingot>,<item:thermal:invar_ingot>
    ],<item:extendedcrafting:basic_component>);
    modifyShapedRecipe([
        [s,c1,s],
        [c1,new Getter().getSlate()[2],c1],
        [s,c1,s]
    ],<item:extendedcrafting:basic_catalyst>);
    modifyShapelessRecipe([
        c1,<item:botania:pixie_dust>,end,end
    ],c2);
    modifyShapedRecipe([
        [end,c2,end],
        [c2,<item:minecraft:ender_eye>,c2],
        [end,c2,end]
    ],<item:extendedcrafting:ender_catalyst>);
    modifyShapedRecipe([
        [c1,cc1,c1],
        [ct,sc,ct],
        [c1,bs,c1]
    ],<item:extendedcrafting:basic_table>);
    extendedCraftingShapedRecipe([
        [c2,cc2,c2],
        [ct,sc,ct],
        [end,end,end]
    ],<item:extendedcrafting:ender_crafter>,1);
    modifyShapedRecipe([
        [a,<item:minecraft:ender_eye>,a],
        [end,sc,end],
        [c2,cc2,c2]
    ],<item:extendedcrafting:ender_alternator>);
    modifyShapedRecipe([
        [c1,<item:bloodmagic:masterbloodorb>.reuse()],
        [an,an]
    ],<item:extendedcrafting:advanced_component>);
    modifyShapedRecipe([
        [ei,c3,ei],
        [c3,new Getter().getSlate()[3],c3],
        [ei,c3,ei]
    ],cc3);
    enderCraftingRecipe([
        [c3,cc3,c3],
        [ct,<item:mythicbotany:alfsteel_armor_upgrade>,ct],
        [c3,sc,c3]
    ],at);
}