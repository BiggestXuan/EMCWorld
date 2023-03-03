#priority 44
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.recipe.Replacer;

import math.Functions;

public function modifyMEKRecipe() as void{
    removeInfusionRecipe([
        "mekanism:processing/iron/enriched",
        "mekanism:processing/steel/enriched_iron_to_dust",
        "mekanism:control_circuit/basic"
    ]);
    removeCompressedRecipe([
        "mekanism:processing/refined_obsidian/ingot/from_dust"
    ]);
    removeReactionRecipe([
        "mekanism:processing/lategame/polonium_pellet/from_reaction",
        "mekanism:processing/lategame/plutonium_pellet/from_reaction"
    ]);
    removeMetallurgicRecipe([
        "mekanism:processing/refined_obsidian/dust/from_obsidian_dust"
    ]);
    addGobberInfusionRecipe(<item:minecraft:iron_ingot>,<item:mekanism:enriched_iron>,10);
    addGobberInfusionRecipe(<item:mekanism:enriched_iron>,<item:mekanism:dust_steel>,10);
    
    var iron = <item:minecraft:iron_ingot>;
    var gobber = <item:gobber2:gobber2_ingot>;
    var pt = <item:mekanism:portable_teleporter>;
    var copper = <item:mekanism:ingot_copper>;
    var furnace = <item:minecraft:furnace>;
    var redstone = <item:minecraft:redstone>;
    var steel = <item:mekanism:ingot_steel>;
    var c1 = <item:mekanism:basic_control_circuit>;
    var c2 = <item:mekanism:advanced_control_circuit>;
    var c3 = <item:mekanism:elite_control_circuit>;
    var c4 = <item:mekanism:ultimate_control_circuit>;
    var t = <item:mekanism:thermal_evaporation_block>;
    var l = <item:mekanism:ingot_lead>;
    var pp = <item:mekanism:pellet_polonium>;
    var pp2 = <item:mekanism:pellet_plutonium>;
    var tc = <item:mekanism:teleportation_core>;
    var la = <item:mekanism:laser>;
    var et = <item:mekanism:energy_tablet>;
    var ad = <item:mekanism:atomic_disassembler>;
    var wa = <item:dead_guys_untitled_deep_dark_:warden_antler>;
    var uic = <item:mekanism:ultimate_induction_cell>;
    var vc = <item:stalwart_dungeons:void_crystal>;
    var cho = <item:stalwart_dungeons:chorundum>;
    var cei = <item:cataclysm:enderite_ingot>;
    var lfm = <item:mekanismgenerators:laser_focus_matrix>;
    var ai = <item:mekanism:alloy_infused>;
    var a = <item:minecraft:air>;
    var dl = <item:mekanism:dust_lithium>;
    var sc = <item:mekanism:steel_casing>;
    var si = <item:emcworld:emc_leaf>;
    var hs = <item:mekanism:hdpe_sheet>;
    var rg = <item:mekanismgenerators:reactor_glass>;
    var ar2 = <item:mekanism:alloy_reinforced>;
    var dm = <item:mekanism:digital_miner>;
    var a2 = <item:mekanism:alloy_reinforced>;
    var sia = <item:astralsorcery:starmetal_ingot>;
    var ro = <item:mekanism:ingot_refined_obsidian>;
    var irg = <item:mekanism:ingot_refined_glowstone>;
    var cop = <item:hem:copparite>;
    var iro = <item:mekanism:ingot_refined_obsidian>;
    var cost as int = Functions.round(750d * getDifficultyLoss()) as int;
    var ccc as IItemStack[]=[
        c1,c2,c3,c4
    ];
    var aaa as IItemStack[]=[
        ai,ar2,<item:mekanism:alloy_atomic>
    ];
    var ic as IItemStack[]=[
        <item:mekanism:basic_induction_cell>,
        <item:mekanism:advanced_induction_cell>,
        <item:mekanism:elite_induction_cell>,
        <item:mekanism:ultimate_induction_cell>
    ];
    var ip as IItemStack[]=[
        <item:mekanism:basic_induction_provider>,
        <item:mekanism:advanced_induction_provider>,
        <item:mekanism:elite_induction_provider>,
        <item:mekanism:ultimate_induction_provider>
    ];
    for i in 1 .. 4{
        removeRecipe([ic[i],ip[i]]);
    }
    modifyShapedRecipe([
        [iron,iron,iron],
        [<tag:items:minecraft:planks>,gobber,<tag:items:minecraft:planks>],
        [copper,furnace,copper]
    ],<item:mekanismgenerators:heat_generator>);
    modifyShapedRecipe([
        [iron,furnace,iron],
        [redstone,<item:divinerpg:shadow_bar>,redstone],
        [iron,furnace,iron]
    ],<item:mekanism:metallurgic_infuser>);
    modifyShapedRecipe([
        [redstone,c1,redstone],
        [iron,steel,iron],
        [redstone,c1,redstone]
    ],<item:mekanism:enrichment_chamber>);
    modifyShapedRecipe([
        [l,t,l],
        [t,l,t],
        [l,t,l]
    ],<item:mekanismgenerators:fission_reactor_casing>*4);
    modifyShapedRecipe([
        [a2,c3,a2],
        [c3,<item:emcworld:silver_medal>,c3],
        [a2,c3,a2]
    ],<item:mekanism:qio_drive_array>);
    modifyShapedRecipe([
        [a2,c3,a2],
        [c3,<item:emcworld:chlorophyte_ingot>,c3],
        [a2,c3,a2]
    ],<item:mekanism:qio_drive_base>);
    modifyShapedRecipe([
        [l,c3,l],
        [c3,<tag:items:forge:glass_panes>,c3],
        [l,<item:emcworld:silver_medal>,l]
    ],<item:mekanism:qio_dashboard>);
    modifyStageRecipe([
        [steel,ai,steel],
        [steel,ai,steel],
        [steel,ai,steel]
    ],<item:mekanismgenerators:turbine_rotor>,"one");
    modifyStageRecipe([
        [a,steel,a],
        [steel,ai,steel],
        [a,steel,a]
    ],<item:mekanismgenerators:turbine_blade>,"one");
    modifyStageRecipe([
        [a,steel,a],
        [steel,iron,steel],
        [a,steel,a]
    ],<item:mekanism:boiler_casing>,"one");
    modifyStageRecipe([
        [a,steel,a],
        [steel,<item:mekanism:ingot_osmium>,steel],
        [a,steel,a]
    ],<item:mekanismgenerators:turbine_casing>,"one");
    removeCraftRecipe([
        <item:mekanism:steel_casing>,
        <item:mekanism:advanced_control_circuit>,
        <item:mekanism:thermal_evaporation_block>,
        c3,
        <item:mekanism:qio_drive_hyper_dense>,
        c4,
        <item:mekanism:qio_drive_time_dilating>,
        <item:mekanism:qio_drive_supermassive>,
        pt,
        <item:mekanism:ultimate_tier_installer>
    ]);
    addGobberInfusionRecipe(<item:mekanism:block_steel>,sc,40);
    addGobberInfusionRecipe(<item:mekanism:ingot_osmium>,<item:mekanism:basic_control_circuit>,20);
    combiningRecipe(<item:mekanism:dust_refined_obsidian>,<item:mekanism:ingot_steel>,iro);
    modifyShapedRecipe([
        [a,steel,a],
        [steel,ar2,steel],
        [a,steel,a]
    ],<item:mekanism:induction_casing>*4);
    steelFurnaceRecipe([iron,<item:minecraft:coal>],<item:mekanism:enriched_iron>*1,100);
    reactionRecipe(si,<fluid:minecraft:water>*cost,<gas:mekanism:plutonium>*cost,pp2,<gas:mekanism:spent_nuclear_waste>*cost);
    reactionRecipe(si,<fluid:minecraft:water>*cost,<gas:mekanism:polonium>*cost,pp,<gas:mekanism:spent_nuclear_waste>*cost);
    for i in 0 .. aaa.length{
        addCraftShapedRecipeNoName([
            [dl,aaa[i],dl],
            [ccc[i+1],ic[i],ccc[i+1]],
            [dl,aaa[i],dl],
        ],ic[i+1]);
    }
    for i in 0 .. aaa.length{
        addCraftShapedRecipeNoName([
            [dl,aaa[i],dl],
            [ccc[i+1],ip[i],ccc[i+1]],
            [dl,aaa[i],dl],
        ],ip[i+1]);
    }
    metallurgicInfusingRecipe(<item:mekanism:dust_obsidian>,<infuse_type:emcworld:hellforged>*40,<item:mekanism:dust_refined_obsidian>);
    extendedCraftingShapedRecipe([
        [a,et,a],
        [c3,tc,c3],
        [pp,<item:astralsorcery:celestial_gateway>,pp]
    ],pt,1);
    modifyShapedRecipe([
        [a,rg,a],
        [rg,<item:astralsorcery:infused_glass>,rg],
        [a,rg,a]
    ],lfm*2);
    modifyShapedRecipe([
        [ar2,et,a],
        [ar2,sc,<item:astralsorcery:colored_lens_spectral>],
        [ar2,et,a]
    ],la);
    modifyShapedRecipe([
        [sia,ro,rg],
        [sia,<item:mekanism:ultimate_energy_cube>,<item:botania:lens_warp>],
        [sia,ro,rg]
    ],<item:mekanism:laser_amplifier>);
    modifyShapedRecipe([
        [cop,cop,cop],
        [c4,<item:mekanism:laser>,c4],
        [pp,pp,pp]
    ],<item:mekanism:supercharged_coil>);
    modifyShapedRecipe([
        [hs,pp,aaa[2]],
        [hs,pp2,pp],
        [<item:stalwart_dungeons:awful_gun>,<item:cataclysm:ignitium_ingot>,<item:mekanism:ultimate_chemical_tank>]
    ],<item:mekanism:flamethrower>);
    removeRecipe([ad,dm]);
    extendedCraftingShapedRecipe([
	    [wa, cho, cho, cei, cei, cei, <item:gobber2:gobber2_paxel_end>.withTag({Damage: 0 as int})], 
	    [a, a, a, a, uic , <item:mekanism:pellet_antimatter>, cei], 
	    [a, a, a, a, vc, uic, cei], 
	    [a, a, a, vc, a, a, cei], 
	    [a, a, <item:cataclysm:gauntlet_of_guard>, a, a, a, cho], 
	    [a, <item:mekanism:ultimate_control_circuit>, a, a, a, a, cho], 
	    [<item:astralsorcery:celestial_collector_crystal>.withTag({astralsorcery: {constellation: "astralsorcery:evorsio" as string}}), a, a, a, a, a, wa]
    ],ad,3);
    treeRitualRecipe([
        <item:cataclysm:void_core>,
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:steel" as string}),
        <item:dead_guys_untitled_deep_dark_:sculk_flagon>,
        <item:mekanism:personal_chest>,
        <item:astralsorcery:ritual_link>,
        <item:astralsorcery:celestial_collector_crystal>.withTag({astralsorcery: {constellation: "astralsorcery:mineralis" as string}}),
        <item:botania:orechid>,
        <item:botania:orechid_ignem>
    ],<item:byg:ether_sapling>,dm);
    removeRecipe([
        <item:mekanism:qio_drive_array>,
        <item:mekanism:qio_dashboard>,
        <item:mekanism:qio_importer>,
        <item:mekanism:qio_importer>,
        <item:mekanism:qio_drive_base>,
        <item:mekanism:qio_redstone_adapter>,
        <item:mekanism:portable_qio_dashboard>
    ]);
}
 