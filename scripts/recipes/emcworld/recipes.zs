#priority 51
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IIngredient;

import crafttweaker.api.recipe.Replacer;

import mods.emcworld.CrTFoodValue;

public function emcworldRecipe() as void{
    var al = <item:mythicbotany:alfheim_rune>;
    var a = <item:minecraft:air>;
    var sg = <item:emcworld:scroll_green>;
    var sp = <item:emcworld:scroll_purple>;
    var sw = <item:emcworld:scroll_white>;
    var ucc = <item:mekanism:ultimate_control_circuit>;
    var sr = <item:emcworld:scroll_red>;
    var ra = <item:good_nights_sleep:rainbow_ingot>;
    var gi = <item:minecraft:gold_ingot>;
    var um = <item:emcworld:unreal_metal>;
    var si = <item:thermal:signalum_ingot>;
    var bs = <item:naturesaura:birth_spirit>;
    var rl = <item:emcworld:raid_light>;
    var ifc = <item:emcworld:infinity_catalyst>;
    var mmas = <item:mythicbotany:alfsteel_sword>;
    var aqd = <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:alfsteel" as string});
    var gr = <item:gobber2:gobber2_rod>;
    var dm = <item:projecte:dark_matter>;
    var rm = <item:projecte:red_matter>;
    var sc = <item:mekanism:steel_casing>;
    var us = <item:emcworld:unobtainium_sword>;
    var gin = <item:gobber2:gobber2_ingot_nether>;
    var oi = <item:rats:oratchalcum_ingot>;
    var pp2 = <item:mekanism:pellet_plutonium>;
    var pp = <item:mekanism:pellet_polonium>;
    var oib = <item:rats:oratchalcum_block>;
    var eoi = <item:emcworld:orichalcos_ingot>;
    var un = <item:allthemodium:unobtainium_ingot>;
    var ci = <item:emcworld:chlorophyte_ingot>;
    var di = <item:emcworld:drystone_ingot>;
    var eus = <item:extendedcrafting:ultimate_singularity>;
    var ieg = <item:emcworld:infinity_emc_gem>;
    var mt = <item:mekanism:meka_tool>;
    var pfm = <item:emcworld:fading_matter>;
    var bx = <item:emcworld:biggest_xuan_ingot>;
    var hs = <item:emcworld:hard_steel>;
    var esi = <item:emcworld:sunlit_ingot>;
    var emcc = <item:emcworld:emc_core>;
    var rai = <item:emcworld:rainbow_ingot>;
    var ewi = <item:emcworld:wooden_ingot>;
    var essi = <item:emcworld:stone_ingot>;
    var evm = <item:emcworld:violet_matter>;
    var eri = <item:emcworld:rune_ingot>;
    var eni = <item:emcworld:nature_ingot>;
    var vaai = <item:allthemodium:vibranium_allthemodium_alloy_ingot>;
    var uvai = <item:allthemodium:unobtainium_vibranium_alloy_ingot>;
    var uaai = <item:allthemodium:unobtainium_allthemodium_alloy_ingot>;
    var ali = <item:allthemodium:allthemodium_ingot>;
    var alv = <item:allthemodium:vibranium_ingot>;
    var sb = <item:emcworld:scroll_blue>;
    var ds = <item:emcworld:dragon_steel>;
    var aui = <item:allthemodium:unobtainium_ingot>;
    var tui = <item:extendedcrafting:the_ultimate_ingot>;
    var ni = <item:atum:nebu_ingot>;
    var mhs = <item:mekanism:hdpe_sheet>;
    var cmi = <item:emcworld:crystal_matrix_ingot>;
    var pcm = <item:emcworld:clay_matter>;
    var nei = <item:minecraft:netherite_ingot>;
    var viqd = <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:vibranium" as string});
    var ss = <item:emcworld:stainless_steel>;
    var ki = <item:twilightforest:knightmetal_ingot>;
    var asi = <item:astralsorcery:starmetal_ingot>;
    var atmqd = <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:atm" as string});
    var csi = <item:emcworld:god_steel_ingot>;
    var eeg = <item:emcworld:epic_emc_gem>;
    var ai = <item:mythicbotany:alfsteel_ingot>;
    var unqd = <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:unobtainium" as string});
    var zi = <item:good_nights_sleep:zitrite_ingot>;
    var sa = <item:minecraft:spectral_arrow>;
    var ub = <item:emcworld:universal_ball>;
    var frc = <item:mekanismgenerators:fission_reactor_casing>;
    var aq = <item:astralsorcery:aquamarine>;
    var pa = <item:minecraft:paper>;
    var baseqd as IIngredient = 
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:iron" as string})|
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:steel" as string})|
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:nickel" as string})|
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:copper" as string})|
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:lead" as string})|
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:aluminum" as string})|
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:silver" as string})
    ;
    var bases as IIngredient =
        <item:emcworld:god_ice_sword>.withTag({level: 14 as int})|
        <item:emcworld:god_fire_sword>.withTag({level: 14 as int})|
        <item:emcworld:god_nature_sword>.withTag({level: 14 as int})|
        <item:emcworld:god_null_sword>.withTag({level: 14 as int})
    ;
    var adss as IIngredient = 
        <item:emcworld:god_ice_sword>.withTag({level: 20 as int})|
        <item:emcworld:god_fire_sword>.withTag({level: 20 as int})|
        <item:emcworld:god_nature_sword>.withTag({level: 20 as int})|
        <item:emcworld:god_null_sword>.withTag({level: 20 as int})
    ;
    var epss as IIngredient = 
        <item:emcworld:god_ice_sword>.withTag({level: 24 as int})|
        <item:emcworld:god_fire_sword>.withTag({level: 24 as int})|
        <item:emcworld:god_nature_sword>.withTag({level: 24 as int})|
        <item:emcworld:god_null_sword>.withTag({level: 24 as int})
    ;
    var amo as int[]=[
        1500,1000,10000,15000,5000,100,10000,5000,10000,6500,5000,5000,10000,10000,10000,8000,8000,8000,15000,6500
    ];
    var red_armor as ItemStack[]=[
        <item:emcworld:guardian_helmet>,
        <item:emcworld:guardian_chestplate>,
        <item:emcworld:guardian_leggings>,
        <item:emcworld:guardian_boots>,
        <item:emcworld:fire_red_helmet>.withTag({level: 12 as int}),
        <item:emcworld:fire_red_chestplate>.withTag({level: 12 as int}),
        <item:emcworld:fire_red_leggings>.withTag({level: 12 as int}),
        <item:emcworld:fire_red_boots>.withTag({level: 12 as int})
    ];
    var ender as IItemStack[]=[
        <item:gobber2:gobber2_helmet_end>,
        <item:gobber2:gobber2_chestplate_end>,
        <item:gobber2:gobber2_leggings_end>,
        <item:gobber2:gobber2_boots_end>
    ];
    var ngb as ItemStack[]=[
        <item:gobber2:gobber2_helmet_nether>,
        <item:gobber2:gobber2_chestplate_nether>,
        <item:gobber2:gobber2_leggings_nether>,
        <item:gobber2:gobber2_boots_nether>,
        <item:gobber2:gobber2_sword_nether>,
        <item:gobber2:gobber2_axe_nether>,
        <item:gobber2:gobber2_pickaxe_nether>,
        <item:gobber2:gobber2_shovel_nether>,
        <item:gobber2:gobber2_hoe_nether>,
        <item:gobber2:gobber2_hammer_nether>
    ];
    var tor as IItemStack[]=[
        <item:torcherino:torcherino>,
        <item:torcherino:compressed_torcherino>,
        <item:torcherino:double_compressed_torcherino>
    ];
    var tung as ItemStack[]=[
        <item:stalwart_dungeons:tungsten_helmet>,
        <item:stalwart_dungeons:tungsten_chestplate>,
        <item:stalwart_dungeons:tungsten_leggings>,
        <item:stalwart_dungeons:tungsten_boots>,
        <item:stalwart_dungeons:tungsten_sword>,
        <item:stalwart_dungeons:tungsten_axe>,
        <item:stalwart_dungeons:tungsten_pickaxe>,
        <item:stalwart_dungeons:tungsten_shovel>,
        <item:stalwart_dungeons:tungsten_hoe>,
        <item:stalwart_dungeons:tungsten_hammer>
    ];
    var atm as ItemStack[]=[
        <item:allthemodium:allthemodium_helmet>,
        <item:allthemodium:allthemodium_chestplate>,
        <item:allthemodium:allthemodium_leggings>,
        <item:allthemodium:allthemodium_boots>
    ];
    var ignis as IItemStack[]=[
        <item:cataclysm:ignitium_helmet>,
        <item:cataclysm:ignitium_chestplate>,
        <item:cataclysm:ignitium_leggings>,
        <item:cataclysm:ignitium_boots>
    ];
    var lg as IItemStack[]=[
        <item:emcworld:lucky_gem_blue>,
        <item:emcworld:lucky_gem_red>,
        <item:emcworld:lucky_gem_purple>,
        <item:emcworld:lucky_gem_gold>
    ];
    var gobber as IItemStack[]=[
        <item:gobber2:gobber2_helmet>,
        <item:gobber2:gobber2_chestplate>,
        <item:gobber2:gobber2_leggings>,
        <item:gobber2:gobber2_boots>
    ];
    var ne_armor as IItemStack[]=[
        <item:emcworld:fire_red_helmet>,
        <item:emcworld:fire_red_chestplate>,
        <item:emcworld:fire_red_leggings>,
        <item:emcworld:fire_red_boots>,
        <item:minecraft:netherite_helmet>,
        <item:minecraft:netherite_chestplate>,
        <item:minecraft:netherite_leggings>,
        <item:minecraft:netherite_boots>
    ];
    var armor as IItemStack[]=[
        <item:botania:terrasteel_helmet>,
        <item:botania:terrasteel_chestplate>,
        <item:botania:terrasteel_leggings>,
        <item:botania:terrasteel_boots>,
        <item:mythicbotany:alfsteel_helmet>,
        <item:mythicbotany:alfsteel_chestplate>,
        <item:mythicbotany:alfsteel_leggings>,
        <item:mythicbotany:alfsteel_boots>,
        <item:allthemodium:allthemodium_helmet>,
        <item:allthemodium:allthemodium_chestplate>,
        <item:allthemodium:allthemodium_leggings>,
        <item:allthemodium:allthemodium_boots>,
        <item:allthemodium:vibranium_helmet>,
        <item:allthemodium:vibranium_chestplate>,
        <item:allthemodium:vibranium_leggings>,
        <item:allthemodium:vibranium_boots>,
        <item:allthemodium:unobtainium_helmet>,
        <item:allthemodium:unobtainium_chestplate>,
        <item:allthemodium:unobtainium_leggings>,
        <item:allthemodium:unobtainium_boots>,
        <item:gobber2:gobber2_helmet_dragon>,
        <item:gobber2:gobber2_chestplate_dragon>,
        <item:gobber2:gobber2_leggings_dragon>,
        <item:gobber2:gobber2_boots_dragon>,
        <item:mekanism:mekasuit_helmet>,
        <item:mekanism:mekasuit_bodyarmor>,
        <item:mekanism:mekasuit_pants>,
        <item:mekanism:mekasuit_boots>
    ];
    var ngb1 as ItemStack[]=[
        <item:gobber2:gobber2_bow_nether>,
        <item:gobber2:gobber2_tree_axe_nether>,
        <item:gobber2:gobber2_excavator_nether>
    ];
    var nether as IItemStack[]=[
        <item:minecraft:netherite_helmet>,
        <item:minecraft:netherite_chestplate>,
        <item:minecraft:netherite_leggings>,
        <item:minecraft:netherite_boots>,
        <item:minecraft:netherite_sword>,
        <item:minecraft:netherite_axe>,
        <item:minecraft:netherite_pickaxe>,
        <item:minecraft:netherite_shovel>,
        <item:minecraft:netherite_hoe>,
        <item:stalwart_dungeons:netherite_hammer>
    ];
    var pdm as IItemStack[]=[
        <item:projecte:dm_helmet>,
        <item:projecte:dm_chestplate>,
        <item:projecte:dm_leggings>,
        <item:projecte:dm_boots>,
        <item:projecte:dm_sword>,
        <item:projecte:dm_shovel>,
        <item:projecte:dm_axe>,
        <item:projecte:dm_pick>,
        <item:projecte:dm_hoe>,
        <item:projecte:dm_shears>,
        <item:projecte:dm_hammer>,
        <item:projecte:rm_helmet>,
        <item:projecte:rm_chestplate>,
        <item:projecte:rm_leggings>,
        <item:projecte:rm_boots>,
        <item:projecte:rm_pick>,
        <item:projecte:rm_axe>,
        <item:projecte:rm_shovel>,
        <item:projecte:rm_sword>,
        <item:projecte:rm_hoe>,
        <item:projecte:rm_shears>,
        <item:projecte:rm_hammer>,
        <item:projecte:rm_katar>,
        <item:projecte:rm_morning_star>,
        <item:projecte:gem_helmet>,
        <item:projecte:gem_chestplate>,
        <item:projecte:gem_leggings>,
        <item:projecte:gem_boots>
    ];
    var sword as IItemStack[]=[
        <item:mythicbotany:alfsteel_sword>,
        <item:emcworld:atm_sword>,
        <item:emcworld:vibranium_sword>,
        <item:emcworld:unobtainium_sword>
    ];
    var sn as IItemStack[] = Ice.INSTANCE.getIce();
    var sing as IItemStack[][] = [
        [
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:atm" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:vibranium" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:invar" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:coal" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:electrum" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:alfsteel" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:redstone" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:emerald" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:glowstone" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:steel" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:diamond" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:uranium" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:iron" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:lead" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:copper" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:silver" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:tin" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:nickel" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:lapis_lazuli" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:bronze" as string})
        ],
        [
            <item:allthemodium:allthemodium_ingot>,
            <item:allthemodium:vibranium_ingot>,
            <item:thermal:invar_ingot>,
            <item:minecraft:coal>,
            <item:thermal:electrum_ingot>,
            <item:mythicbotany:alfsteel_ingot>,
            <item:minecraft:redstone>,
            <item:minecraft:emerald>,
            <item:minecraft:glowstone_dust>,
            <item:mekanism:ingot_steel>,
            <item:minecraft:diamond>,
            <item:mekanism:ingot_uranium>,
            <item:minecraft:iron_ingot>,
            <item:mekanism:ingot_lead>,
            <item:mekanism:ingot_copper>,
            <item:emcworld:silver_ingot>,
            <item:mekanism:ingot_tin>,
            <item:emcworld:nickel_ingot>,
            <item:minecraft:lapis_lazuli>,
            <item:mekanism:ingot_bronze>
        ]
    ];
    removeRecipe([mt]);
    violet(<item:projectex:purple_collector>,<item:projectex:violet_collector>);
    violet(<item:projectex:purple_relay>,<item:projectex:violet_relay>);
    blue(<item:projectex:violet_collector>,<item:projectex:blue_collector>);
    blue(<item:projectex:violet_relay>,<item:projectex:blue_relay>);
    //test recipe.
    treeRitualRecipe([
        bx,bx,bx,bx
    ],bx,bx*3); // end
    pink(<item:projectex:magenta_collector>,<item:projectex:pink_collector>);
    pink(<item:projectex:magenta_relay>,<item:projectex:pink_relay>);
    infuserRecipe([sp,sp,sp,sp,sp],sr,6000,1500000,3);
    infuserRecipe([sb,sb,sb,sb,sb],sp,2500,300000,2);
    addCraftShapelessRecipe([<item:minecraft:diamond_sword>,dm,dm],<item:emcworld:profession_sword>);
    addCraftShapelessRecipe([<item:minecraft:diamond_chestplate>,dm,dm],<item:emcworld:profession_tank>);
    addCraftShapedRecipeNoName([
        [a,dm,a],
        [dm,rm,dm],
        [a,dm,a]
    ],<item:emcworld:red_matter_crystal>);
    for i in 0 .. 4{
        nucleosyRecipe(ne_armor[i],<gas:mekanism:antimatter>*(200 * getDifficultyLoss()) as int,ne_armor[i+4],2000);
    }
    tartaricForgeRecipe([<item:emcworld:base_key>,<item:twilightforest:lamp_of_cinders>,<item:astralsorcery:rock_collector_crystal>.withTag({astralsorcery: {constellation: "astralsorcery:vicio" as string}}),<item:rats:dutchrat_wheel>],<item:emcworld:nether_key>,4096,1000);
    smithingRecipe(sword[0],atmqd*3,sword[1]);
    smithingRecipe(sword[1],viqd*3,sword[2]);
    smithingRecipe(sword[2],unqd*3,sword[3]);
    for i in 20 .. 24{
        removeSmithingRecipe(armor[i]);
    }
    for i in 24 .. 28{
        removeRecipe([armor[i],pdm[i]]);
    }
    modifyExtendedCompressionRecipe(<item:allthemodium:unobtainium_ingot>,unqd,500,100);
    for i in 0 .. 20{
        extendedCompressionRecipe(sing[1][i],sing[0][i],amo[i],i);
    }
    if(getGameDifficulty() >= 1){
        removeRecipe(tor);
    }
    if(getGameDifficulty() == 3){
        for i in 0 .. 4{
            combiningRecipe(red_armor[i+4],<item:emcworld:dragon_steel>,red_armor[i]);
        }
        extendedCraftingShapelessRecipe([
            tui,tui,tui,tui,tui,tui,tui,tui,tui,tui,tui,tui,eus,eus,eus,eus,eus,eus,eus
        ],ifc,4);
        extendedCraftingShapedRecipe([
            [a,a,a,a,a,a,cmi,cmi,cmi],
            [a,a,a,a,a,cmi,ifc,ifc,cmi],
            [a,a,a,a,cmi,ifc,ifc,ifc,cmi],
            [a,cmi,a,cmi,ifc,ifc,ifc,cmi,a],
            [a,a,cmi,ifc,ifc,ifc,cmi,a,a],
            [a,a,cmi,epss,cmi,cmi,a,a,a],
            [a,cmi,ifc,cmi,cmi,a,a,a,a],
            [cmi,ifc,cmi,a,a,cmi,a,a,a],
            [cmi,cmi,a,a,a,a,cmi,a,a]
        ],<item:emcworld:infinity_sword>,4);
    }
    if(getGameDifficulty() >= 2){
        for i in 24 .. 28{
            extendedCraftingShapedRecipe([
                [eus,tui,tui,eeg,eeg,eeg,tui,tui,eus],
                [tui,pcm,eeg,eeg,eeg,eeg,eeg,pcm,tui],
                [tui,eeg,pcm,eeg,eeg,eeg,pcm,eeg,tui],
                [eeg,eeg,eeg,pcm,ieg,pcm,eeg,eeg,eeg],
                [eeg,eeg,eeg,ieg,pdm[i-13],ieg,eeg,eeg,eeg],
                [eeg,eeg,eeg,pcm,ieg,pcm,eeg,eeg,eeg],
                [tui,eeg,pcm,eeg,eeg,eeg,pcm,eeg,tui],
                [tui,pcm,eeg,eeg,eeg,eeg,eeg,pcm,tui],
                [eus,tui,tui,eeg,eeg,eeg,tui,tui,eus]
            ],pdm[i],4);
        }
        extendedCompressionRecipe(ub,emcc,6,1);
        for i in pdm{
            Replacer.forTypes(craftingTable)
            .forMods("projecte")
            .forOutput(i,craftingTable)
            .replace(<item:projecte:dark_matter>,pfm)
            .replace(<item:projecte:red_matter>,pcm)
            .execute();
        }
        copyFinalIngot(ewi);
        copyFinalIngot(essi);
        copyFinalIngot(eri);
        copyFinalIngot(eni);
        removeRecipe([pdm[22]]);
        extendedCraftingShapedRecipe([
            [tui,tui,tui,eeg,eeg,eeg,tui,tui,tui],
            [tui,tui,eeg,eeg,eus,eeg,eeg,tui,tui],
            [tui,eeg,ieg,pcm,pcm,pcm,ieg,eeg,tui],
            [eeg,eeg,pcm,pcm,pdm[18],pcm,pcm,eeg,eeg],
            [eeg,eus,pcm,pdm[15],adss,pdm[16],pcm,eus,eeg],
            [eeg,eeg,pcm,pcm,pdm[23],pcm,pcm,eeg,eeg],
            [tui,eeg,ieg,pcm,pcm,pcm,ieg,eeg,tui],
            [tui,tui,eeg,eeg,eus,eeg,eeg,tui,tui],
            [tui,tui,tui,eeg,eeg,eeg,tui,tui,tui]
        ],pdm[22],4);
    }else{
        removeRecipe(pdm);
    }
    if(getGameDifficulty() >= 1){
        crystallizingGasRecipe(<gas:emcworld:cosmic_flow>*1000,ub);
        for i in 16 .. 20{
            smithingRecipe(armor[i-4],unqd,armor[i]);
        }
        for i in 20 .. 24{
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
        steelFurnaceRecipe([
            ub,<item:gobber2:dragon_star>,<item:emcworld:hard_steel>
        ],ds,600);
    }
    for i in 0 .. armor.length{
        if(i >= 4 & i < 8){
            removeSmithingRecipe(armor[i]);
            smithingRecipe(armor[i-4],aqd,armor[i]);
        }
        if(i >= 8 & i < 12){
            smithingRecipe(armor[i-4],atmqd,armor[i]);
        }
        if(i < 16 & i >= 12){
            smithingRecipe(armor[i-4],viqd,armor[i]);
        }
    }
    infuserRecipe([ub,aui,ali,ali,ali],<item:allthemodium:unobtainium_allthemodium_alloy_ingot>*4,30000,10000000,3);
    infuserRecipe([ub,aui,aui,ali,ali],<item:allthemodium:unobtainium_allthemodium_alloy_ingot>*6,30000,9000000,3);
    infuserRecipe([ub,aui,alv,alv,alv],<item:allthemodium:unobtainium_vibranium_alloy_ingot>*5,25000,7000000,3);
    infuserRecipe([ub,aui,aui,alv,alv],<item:allthemodium:unobtainium_vibranium_alloy_ingot>*7,25000,6000000,3);
    removeRecipe(armor);
    multiFurnaceRecipe(<tag:items:mekanism:colorable/concrete>,<item:emcworld:steel_furnace_brick>);
    removeSmithingRecipe(mmas);
    smithingRecipe(<item:mekanism:atomic_disassembler>,aqd*2,mmas);
    removeFurnaceRecipe([aq]);
    removeCraftRecipe([nei]);
    removeCraftRecipe(tung);
    furnaceRecipeNoName(aq*3,<item:emcworld:aquamarine_ore>,1);
    bloodAltarRecipe(<item:mekanism:steel_casing>,<item:emcworld:control_update_core>,30000,3);
    addNuggetAndBlockRecipe(
        <item:emcworld:copper_medal>,
        <item:emcworld:silver_medal>,
        <item:emcworld:gold_medal>
    );
    natureSpawnerRecipe([
        <item:naturesaura:birth_spirit>,bx,bx,bx
    ],500000,<entitytype:emcworld:biggest_xuan>,"bx");
    alchemalTableRecipe([
        <item:botania:gaia_ingot>,
        <item:good_nights_sleep:zitrite_ingot>,
        <item:good_nights_sleep:rainbow_ingot>
    ],<item:emcworld:unreal_metal>*getModifyRecipeAmount(),10000,4);
    addCraftShapedRecipeNoName([
        [al,csi,al],
        [ra,<item:emcworld:control_update_core>,zi],
        [frc,<item:extendedcrafting:advanced_catalyst>,frc]
    ],<item:emcworld:advanced_update_core>);
    terraPlateRecipe([
        <item:atum:anput_godshard>,
        <item:atum:anubis_godshard>,
        <item:atum:atem_godshard>,
        <item:atum:geb_godshard>,
        <item:atum:horus_godshard>,
        <item:atum:isis_godshard>,
        <item:atum:montu_godshard>,
        <item:atum:nepthys_godshard>,
        <item:atum:nuit_godshard>,
        <item:atum:osiris_godshard>,
        <item:atum:ptah_godshard>,
        <item:atum:ra_godshard>,
        <item:atum:seth_godshard>,
        <item:atum:shu_godshard>,
        <item:atum:tefnut_godshard>,
        <item:mythicbotany:alfsteel_ingot>,
        <item:bloodmagic:reagentbinding>
    ],<item:emcworld:god_steel_ingot>,200000);
    pigmentRecipe(<item:emcworld:biggest_xuan_ingot>,<pigment:emcworld:bx>*128*getModifyRecipeAmount());
    addNuggetAndBlockRecipe(
        <item:emcworld:base_emc_stored_totem>.withDamage(0),
        <item:emcworld:advanced_emc_stored_totem>.withDamage(0),
        <item:emcworld:elite_emc_stored_totem>.withDamage(0)
    );
    addCraftShapelessRecipe([
        <item:minecraft:book>,<item:emcworld:small_emc_gem>
    ],<item:patchouli:guide_book>.withTag({"patchouli:book": "emcworld:guide" as string}));
    addCraftShapelessRecipe([
        <tag:items:forge:stone>,<item:emcworld:small_emc_gem>
    ],<item:emcworld:emc_check>);
    removeCraftRecipe(atm);
    extendedCraftingShapedRecipe([
        [a,gi,um,si,a],
        [a,gi,<item:mekanism:teleportation_core>,si,a],
        [a,<item:good_nights_sleep:positite_gem>,<item:emcworld:god_steel_ingot>,<item:good_nights_sleep:negatite_gem>,a],
        [a,a,ai,a,a],
        [a,a,ai,a,a]
    ],<item:emcworld:base_key>,2);
    alchemalArrayRecipe(<item:emcworld:base_key>,<item:mythicbotany:kvasir_blood>,<item:emcworld:twilight_key>);
    addNuggetAndIngotRecipe(<item:emcworld:niobium_nugget>,<item:emcworld:niobium_ingot>);
    addCraftShapedRecipeNoName([
        [dm,sc,dm],
        [sc,dm,sc],
        [dm,sc,dm]
    ],<item:emcworld:update_base_purple>*getModifyRecipeAmount());
    addCraftShapedRecipeNoName([
        [oib,oi,oib],
        [oi,ucc,oi],
        [oib,oi,oib]
    ],<item:emcworld:nuclear_ball>.withDamage(10000));
    for i in 0 .. lg.length -1{
        EMCWorldRecipe(lg[i],lg[i+1]);
    }
    for i in 4 .. ngb.length{
        smithingRecipe(tung[i],gin*4,ngb[i].asIItemStack());
    }
    if(getGameDifficulty() == 3){
        torchRecipe(<item:emcworld:white_matter>,tui,ifc);
        torchRecipe1(45,100);
    }else if(getGameDifficulty() >=2){
        torchRecipe(<item:emcworld:emc_core>,tui,eus);
        torchRecipe1(25,45);
    }else if(getGameDifficulty() >=1){
        torchRecipe(<item:emcworld:orange_matter>,<item:emcworld:dragon_steel>,ub);
        torchRecipe1(15,25);
    }
    infuserRecipe([sa,sa,sa,sa,sa],<item:emcworld:raid_light>,15000,100000,1);
    infuserRecipe([ali,ali,ali,alv,alv],vaai*2,10000,500000,1);
    infuserRecipe([ali,ali,alv,alv,alv],vaai*3,10000,500000,1);
    infuserRecipe([ali,alv,a,a,a],vaai*32,10000,10000000,3);
    steelFurnaceRecipe([eoi,sn[5],ci,di,esi],rai,300);
    steelFurnaceRecipe([<item:gobber2:gobber2_ingot>,<item:mekanism:block_steel>],<item:mekanism:steel_casing>,300);
    steelFurnaceRecipe([<item:emcworld:enriched_gobber>,<item:mekanism:block_steel>],<item:mekanism:steel_casing>,600);
    steelFurnaceRecipe([ss,rai,ki,zi,asi],hs,1000);
    mythicInfuserRecipe([
        <item:atum:godforged_block>,<item:emcworld:god_steel_ingot>,<item:emcworld:orichalcos_ingot>
    ],<item:emcworld:infuser_core>,1500000,0xfaebd7,0xeec900);
    extendedCombinationRecipe([
        <item:minecraft:netherite_ingot>,gr,gr,gr,gr
    ],<item:emcworld:netherite_stick>);
    bloodAltarRecipe(<item:emcworld:stone_shard>,<item:emcworld:ancient_icon>,15000,4);
    EMCWorldIce(sn[0],1);
    EMCWorldIce(sn[1],4);
    EMCWorldIce(sn[2],4);
    EMCWorldIce(sn[3],36);
    EMCWorldIce(sn[4],324);
    EMCWorldIce(sn[5],600);
    for i in 0 .. sn.length-2{
        enrichingRecipe(sn[i]*2,sn[i+1],1);
    }
    removeMetallurgicRecipe([
        "mekanism:processing/netherite/scrap_to_dust"
    ]);
    for i in nether{
        removeSmithingRecipe(i);
    }
    for i in 0 .. gobber.length{
        smithingRecipe(gobber[i],nei,nether[i]);
    }
    for i in 0 .. tung.length{
        smithingRecipe(nether[i],<item:stalwart_dungeons:blaze_armor_scrap>,tung[i]);
    }
    for i in 0 .. ignis.length{
        smithingRecipe(tung[i],<item:cataclysm:ignitium_ingot>,ignis[i]);
    }
    for i in 0 .. 4{
        smithingRecipe(ignis[i],gin,ngb[i].asIItemStack());
    }
    addCraftShapelessRecipe([sg,sg,sg,sg],sb);
    addCraftShapelessRecipe([sw,sw,sw,sw],sg);
    infuserRecipe([pa,pa,pa,pa,pa],sw,300,5000,1);
    removeCraftRecipe(ngb);
    removeCraftRecipe(ngb1);
    smithingRecipe(<item:gobber2:gobber2_bow>,gin*6,ngb1[0].asIItemStack());
    smithingRecipe(ngb[5].asIItemStack(),gin*12,ngb1[1].asIItemStack());
    smithingRecipe(ngb[7].asIItemStack(),gin*9,ngb1[2].asIItemStack());
    removeFurnaceRecipeByName(["stalwart_dungeons:tungsten_ingot_recipe"]);
    combiningRecipe(<item:stalwart_dungeons:tungsten_ingot>,<item:minecraft:netherite_scrap>*2,nei);
    metallurgicInfusingRecipe(<item:mekanism:ingot_steel>*2,<infuse_type:emcworld:ice>*1000,<item:emcworld:stainless_steel>);
    <recipetype:mekanism:painting>.addRecipe("bx",<item:emcworld:update_base_purple>,<pigment:emcworld:bx>*128,<item:emcworld:update_base_bx_purple>);
    if(getGameDifficulty() >=2){
        extendedCraftingShapelessRecipe([
            <item:minecraft:iron_ingot>,
            <item:minecraft:gold_ingot>,
            <item:minecraft:netherite_ingot>,
            <item:mekanism:ingot_bronze>,
            <item:mekanism:ingot_refined_obsidian>,
            <item:mekanism:ingot_refined_glowstone>,
            <item:mekanism:ingot_steel>,
            <item:mekanism:ingot_osmium>,
            <item:mekanism:ingot_copper>,
            <item:mekanism:ingot_tin>,
            <item:mekanism:ingot_lead>,
            <item:mekanism:ingot_uranium>,
            <item:botania:manasteel_ingot>,
            <item:botania:terrasteel_ingot>,
            <item:botania:elementium_ingot>,
            <item:botania:gaia_ingot>,
            <item:gobber2:gobber2_ingot>,
            <item:gobber2:gobber2_ingot_nether>,
            <item:gobber2:gobber2_ingot_end>,
            <item:byg:pendorite_ingot>,
            <item:thermal:electrum_ingot>,
            <item:thermal:invar_ingot>,
            <item:thermal:constantan_ingot>,
            <item:thermal:signalum_ingot>,
            <item:thermal:lumium_ingot>,
            <item:thermal:enderium_ingot>,
            <item:atum:nebu_ingot>,
            <item:cataclysm:witherite_ingot>,
            <item:cataclysm:enderite_ingot>,
            <item:cataclysm:ignitium_ingot>,
            <item:good_nights_sleep:rainbow_ingot>,
            <item:good_nights_sleep:zitrite_ingot>,
            <item:rats:oratchalcum_ingot>,
            <item:the_afterlight:livingessenceingot>,
            <item:the_afterlight:moonsteel_ingot>,
            <item:the_afterlight:crystillium_ingot>,
            <item:the_afterlight:lunariteingot>,
            <item:twilightforest:ironwood_ingot>,
            <item:twilightforest:fiery_ingot>,
            <item:twilightforest:knightmetal_ingot>,
            <item:undergarden:cloggrum_ingot>,
            <item:undergarden:froststeel_ingot>,
            <item:undergarden:utherium_ingot>,
            <item:undergarden:regalium_ingot>,
            <item:undergarden:forgotten_ingot>,
            <item:theabyss:garnite_ingot>,
            <item:theabyss:unorithe_ingot>,
            <item:theabyss:florite_gem>,
            <item:theabyss:knight_ingot>,
            <item:hem:rose_copper_ingot>,
            <item:hem:bronze_ingot>,
            <item:hem:copparite>,
            <item:astralsorcery:starmetal_ingot>,
            <item:bloodmagic:ingot_hellforged>,
            <item:extendedcrafting:black_iron_ingot>,
            <item:extendedcrafting:redstone_ingot>,
            <item:extendedcrafting:enhanced_ender_ingot>,
            <item:extendedcrafting:crystaltine_ingot>,
            <item:mythicbotany:alfsteel_ingot>,
            <item:emcworld:aluminum_ingot>,
            <item:emcworld:nickel_ingot>,
            <item:emcworld:silver_ingot>,
            <item:emcworld:stainless_steel>,
            <item:emcworld:hard_steel>,
            <item:emcworld:rainbow_ingot>,
            <item:emcworld:drystone_ingot>,
            ewi,essi,eri,eni,
            <item:emcworld:god_steel_ingot>,
            <item:emcworld:biggest_xuan_ingot>,
            <item:emcworld:magnesium_ingot>,
            <item:emcworld:indium_ingot>,
            <item:emcworld:titanium_ingot>,
            <item:emcworld:niobium_ingot>,
            <item:allthemodium:allthemodium_ingot>,
            <item:allthemodium:vibranium_ingot>,
            <item:naturesaura:infused_iron>,
            <item:naturesaura:sky_ingot>,
            <item:stalwart_dungeons:tungsten_ingot>
        ],tui,4);
        extendedCraftingShapelessRecipe([
            bx,bx,
            <item:minecraft:oak_log>,
            <item:minecraft:spruce_log>,
            <item:minecraft:birch_log>,
            <item:minecraft:jungle_log>,
            <item:minecraft:acacia_log>,
            <item:minecraft:dark_oak_log>,
            <item:minecraft:crimson_stem>,
            <item:minecraft:warped_stem>,
            <item:botania:dreamwood>,
            <item:byg:aspen_log>,
            <item:byg:baobab_log>,
            <item:byg:blue_enchanted_log>,
            <item:byg:bulbis_stem>,
            <item:byg:cherry_log>,
            <item:byg:cika_log>,
            <item:byg:cypress_log>,
            <item:byg:ebony_log>,
            <item:byg:ether_log>,
            <item:byg:fir_log>,
            <item:byg:green_enchanted_log>,
            <item:byg:holly_log>,
            <item:byg:imparius_stem>,
            <item:byg:jacaranda_log>,
            <item:byg:lament_log>,
            <item:byg:mahogany_log>,
            <item:byg:mangrove_log>,
            <item:byg:maple_log>,
            <item:byg:imbued_nightshade_log>,
            <item:byg:palo_verde_log>,
            <item:byg:pine_log>,
            <item:byg:rainbow_eucalyptus_log>,
            <item:byg:redwood_log>,
            <item:byg:skyris_log>,
            <item:byg:willow_log>,
            <item:byg:witch_hazel_log>,
            <item:byg:zelkova_log>,
            <item:byg:sythian_stem>,
            <item:byg:embur_pedu>,
            <item:byg:withering_oak_log>,
            <item:atum:palm_log>,
            <item:atum:deadwood_log>,
            <item:good_nights_sleep:dream_log>,
            <item:good_nights_sleep:white_log>,
            <item:good_nights_sleep:dead_log>,
            <item:good_nights_sleep:blood_log>,
            <item:rats:pirat_log>,
            <item:aether:skyroot_log>,
            <item:aether:golden_oak_log>,
            <item:the_afterlight:moonlight_log>,
            <item:the_afterlight:gloom_wood>,
            <item:twilightforest:twilight_oak_log>,
            <item:twilightforest:canopy_log>,
            <item:twilightforest:mangrove_log>,
            <item:twilightforest:dark_log>,
            <item:twilightforest:time_log>,
            <item:twilightforest:transformation_log>,
            <item:twilightforest:mining_log>,
            <item:twilightforest:sorting_log>,
            <item:undergarden:smogstem_log>,
            <item:undergarden:wigglewood_log>,
            <item:undergarden:grongle_log>,
            <item:theabyss:rotten_log>,
            <item:theabyss:blaru_log>,
            <item:theabyss:jungle_log>,
            <item:theabyss:ruma_log>,
            <item:theabyss:roggen_log>,
            <item:theabyss:tantra_log>,
            <item:theabyss:bog_shroom_log>,
            <item:theabyss:sal_shroom_log>,
            <item:theabyss:slimed_log>,
            <item:theabyss:frozen_log>,
            <item:hem:blueleaf_log>,
            <item:hem:hardenedlog>,
            <item:hem:crystalized_log>,
            <item:hem:blueleafpinelog>,
            <item:hem:blueleafbirchlog>,
            <item:hem:redwood_log>,
            <item:astralsorcery:infused_wood>,
            <item:naturesaura:ancient_log>
        ],ewi,4);
        extendedCraftingShapelessRecipe([
            bx,bx,bx,bx,bx,bx,bx,
            <item:minecraft:stone>,
            <item:minecraft:smooth_stone>,
            <item:minecraft:mossy_cobblestone>,
            <item:minecraft:obsidian>,
            <item:minecraft:stone_bricks>,
            <item:minecraft:end_stone>,
            <item:minecraft:prismarine>,
            <item:minecraft:dark_prismarine>,
            <item:minecraft:crying_obsidian>,
            <item:minecraft:blackstone>,
            <item:botania:livingrock>,
            <item:botania:livingrock_bricks>,
            <item:botania:metamorphic_forest_stone>,
            <item:botania:metamorphic_plains_stone>,
            <item:botania:metamorphic_mountain_stone>,
            <item:botania:metamorphic_fungal_stone>,
            <item:botania:metamorphic_swamp_stone>,
            <item:botania:metamorphic_desert_stone>,
            <item:botania:metamorphic_taiga_stone>,
            <item:botania:metamorphic_mesa_stone>,
            <item:byg:dacite_cobblestone>,
            <item:byg:mossy_stone>,
            <item:byg:rocky_stone>,
            <item:byg:travertine>,
            <item:byg:scoria_stone>,
            <item:byg:soapstone>,
            <item:byg:brimstone>,
            <item:byg:quartzite_sand>,
            <item:byg:ether_stone>,
            <item:byg:bulbis_phycelium>,
            <item:byg:nightshade_phylium>,
            <item:byg:purpur_stone>,
            <item:byg:vermilion_sculk>,
            <item:byg:shulkren_phylium>,
            <item:byg:cryptic_stone>,
            <item:quark:sturdy_stone>,
            <item:quark:marble>,
            <item:quark:limestone>,
            <item:quark:jasper>,
            <item:quark:basalt>,
            <item:quark:myalite>,
            <item:quark:brimstone>,
            <item:quark:cobbedstone>,
            <item:quark:elder_prismarine>,
            <item:quark:elder_prismarine_bricks>,
            <item:quark:permafrost>,
            <item:quark:dusky_myalite>,
            <tag:items:thermal:rockwool>,
            <item:atum:limestone>,
            <item:atum:alabaster>,
            <item:cataclysm:polished_end_stone>,
            <item:cataclysm:obsidian_bricks>,
            <item:dead_guys_untitled_deep_dark_:deep_slate>,
            <item:good_nights_sleep:delusion_stone>,
            <item:rats:marbled_cheese_raw>,
            <item:rats:marbled_cheese_tile>,
            <item:aether:holystone>,
            <item:the_afterlight:strange_obsidian>,
            <item:the_afterlight:void_rock>,
            <item:undergarden:depthrock>,
            <item:undergarden:shiverstone>,
            <item:theabyss:tantra_stone>,
            <item:astralsorcery:marble_raw>,
            <item:bloodmagic:dungeon_stone>,
            <item:naturesaura:infused_stone>
        ],essi,4);
        extendedCraftingShapelessRecipe([
            bx,bx,bx,bx,bx,bx,
            <item:botania:rune_water>,
            <item:botania:rune_fire>,
            <item:botania:rune_earth>,
            <item:botania:rune_air>,
            <item:botania:rune_spring>,
            <item:botania:rune_summer>,
            <item:botania:rune_autumn>,
            <item:botania:rune_winter>,
            <item:botania:rune_mana>,
            <item:botania:rune_lust>,
            <item:botania:rune_gluttony>,
            <item:botania:rune_greed>,
            <item:botania:rune_sloth>,
            <item:botania:rune_wrath>,
            <item:botania:rune_envy>,
            <item:botania:rune_pride>,
            <item:mythicbotany:asgard_rune>,
            <item:mythicbotany:vanaheim_rune>,
            <item:mythicbotany:alfheim_rune>,
            <item:mythicbotany:midgard_rune>,
            <item:mythicbotany:joetunheim_rune>,
            <item:mythicbotany:muspelheim_rune>,
            <item:mythicbotany:niflheim_rune>,
            <item:mythicbotany:nidavellir_rune>,
            <item:mythicbotany:helheim_rune>,
            <item:quark:white_rune>,
            <item:quark:orange_rune>,
            <item:quark:magenta_rune>,
            <item:quark:light_blue_rune>,
            <item:quark:yellow_rune>,
            <item:quark:lime_rune>,
            <item:quark:pink_rune>,
            <item:quark:gray_rune>,
            <item:quark:light_gray_rune>,
            <item:quark:cyan_rune>,
            <item:quark:purple_rune>,
            <item:quark:blue_rune>,
            <item:quark:brown_rune>,
            <item:quark:green_rune>,
            <item:quark:red_rune>,
            <item:quark:black_rune>,
            <item:quark:rainbow_rune>,
            <item:quark:blank_rune>,
            <item:bloodmagic:blankrune>,
            <item:bloodmagic:speedrune>,
            <item:bloodmagic:sacrificerune>,
            <item:bloodmagic:selfsacrificerune>,
            <item:bloodmagic:dislocationrune>,
            <item:bloodmagic:altarcapacityrune>,
            <item:bloodmagic:bettercapacityrune>,
            <item:bloodmagic:orbcapacityrune>,
            <item:bloodmagic:accelerationrune>,
            <item:bloodmagic:chargingrune>
        ],eri,4);
        extendedCraftingShapelessRecipe([
            bx,bx,bx,
            <item:minecraft:grass>,
            <item:minecraft:fern>,
            <item:minecraft:dead_bush>,
            <item:minecraft:seagrass>,
            <item:minecraft:sea_pickle>,
            <item:minecraft:dandelion>,
            <item:minecraft:poppy>,
            <item:minecraft:blue_orchid>,
            <item:minecraft:allium>,
            <item:minecraft:azure_bluet>,
            <item:minecraft:red_tulip>,
            <item:minecraft:orange_tulip>,
            <item:minecraft:white_tulip>,
            <item:minecraft:pink_tulip>,
            <item:minecraft:oxeye_daisy>,
            <item:minecraft:cornflower>,
            <item:minecraft:lily_of_the_valley>,
            <item:minecraft:wither_rose>,
            <item:minecraft:vine>,
            <item:minecraft:lily_pad>,
            <item:botania:white_mystical_flower>,
            <item:botania:orange_mystical_flower>,
            <item:botania:magenta_mystical_flower>,
            <item:botania:light_blue_mystical_flower>,
            <item:botania:yellow_mystical_flower>,
            <item:botania:lime_mystical_flower>,
            <item:botania:pink_mystical_flower>,
            <item:botania:gray_mystical_flower>,
            <item:botania:light_gray_mystical_flower>,
            <item:botania:cyan_mystical_flower>,
            <item:botania:purple_mystical_flower>,
            <item:botania:blue_mystical_flower>,
            <item:botania:brown_mystical_flower>,
            <item:botania:green_mystical_flower>,
            <item:botania:red_mystical_flower>,
            <item:botania:black_mystical_flower>,
            <item:byg:ether_grass>,
            <item:byg:short_grass>,
            <item:byg:winter_grass>,
            <item:byg:weed_grass>,
            <item:byg:short_beach_grass>,
            <item:byg:clover_patch>,
            <item:byg:flower_patch>,
            <item:byg:alpine_bellflower>,
            <item:byg:azalea>,
            <item:byg:blue_sage>,
            <item:byg:california_poppy>,
            <item:byg:crocus>,
            <item:byg:daffodil>,
            <item:byg:lazarus_bellflower>,
            <item:byg:orange_daisy>,
            <item:byg:pink_daffodil>,
            <item:byg:pink_orchid>,
            <item:byg:protea_flower>,
            <item:byg:purple_orchid>,
            <item:byg:purple_sage>,
            <item:byg:red_cornflower>,
            <item:byg:red_orchid>,
            <item:byg:snowdrops>,
            <item:byg:white_anemone>,
            <item:good_nights_sleep:orange_flower>,
            <item:good_nights_sleep:cyan_flower>,
            <item:the_afterlight:moonflower>,
            <item:the_afterlight:mournblossom>,
            <item:the_afterlight:moontail>,
            <item:the_afterlight:sparkling_flower>,
            <item:undergarden:deepturf>,
            <item:undergarden:ashen_deepturf>,
            <item:undergarden:frozen_deepturf>,
            <item:undergarden:tall_deepturf>,
            <item:undergarden:shimmerweed>,
            <item:theabyss:loran_flower>,
            <item:theabyss:fortis_ultima>,
            <item:theabyss:grass_small>,
            <item:hem:blueleaf_grass>,
            <item:hem:blueleaf_flowering_lily>,
            <item:hem:lush_flower_1>,
            <item:naturesaura:aura_bloom>
        ],eni,4);
    }
}

private function pink(c as IItemStack,r as IItemStack) as void{
    infuserRecipe([
        <item:emcworld:pink_matter>,
        <item:rats:oratchalcum_block>,
        <item:rats:dutchrat_wheel>,
        c,
        <item:rats:arcane_technology>
    ],r,4000,5000000,2);
}

private function violet(c as IItemStack,r as IItemStack) as void{
    var evm = <item:emcworld:violet_matter>;
    treeRitualRecipe([
        <item:cataclysm:ignitium_block>,evm,evm,<item:emcworld:advanced_emc_gem>,<item:gobber2:gobber2_block_nether>,
        c,<item:byg:death_cap>,<item:naturesaura:token_euphoria>
    ],<item:byg:blue_enchanted_sapling>,r);
}

private function blue(c as IItemStack,r as IItemStack) as void{
    treeRitualRecipe([
        c,
        <item:undergarden:forgotten_block>,
        <item:undergarden:masticator_scales>,
        <item:the_afterlight:lunarite_block>,
        <item:extendedcrafting:elite_component>,
        <item:the_afterlight:block_of_radiance_shards>,
        <item:undergarden:regalium_block>,
        <item:emcworld:blue_matter>
    ],<item:undergarden:smogstem_sapling>,r);
}