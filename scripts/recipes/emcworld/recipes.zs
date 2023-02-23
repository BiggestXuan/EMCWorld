#priority 51
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IIngredient;

import crafttweaker.api.recipe.Replacer;

import mods.emcworld.CrTFoodValue;
import mods.emcworld.CTRequirement;
import mods.emcworld.EWItem;

public function emcworldRecipe() as void{
    var al = <item:mythicbotany:alfheim_rune>;
    var a = <item:minecraft:air>;
    var beg = <item:emcworld:big_emc_gem>;
    var sg = <item:emcworld:scroll_green>;
    var em = <item:minecraft:emerald>;
    var sp = <item:emcworld:scroll_purple>;
    var sw = <item:emcworld:scroll_white>;
    var tun = <item:extendedcrafting:the_ultimate_nugget>;
    var alt = <item:bloodmagic:altarcapacityrune>;
    var ucc = <item:mekanism:ultimate_control_circuit>;
    var sr = <item:emcworld:scroll_red>;
    var ra = <item:good_nights_sleep:rainbow_ingot>;
    var gi = <item:minecraft:gold_ingot>;
    var bgi = <item:botania:gaia_ingot>;
    var ws = <item:emcworld:scroll_white>;
    var bge = <item:emcworld:biggest_emc_gem>;
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
    var pon = <item:minecraft:potion>.withTag({Potion: "minecraft:swiftness" as string});
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
    var eis = <item:emcworld:illager_shard>;
    var dee = <item:quark:deepslate>;
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
    var ip = <item:astralsorcery:illumination_powder>;
    var sar = <item:bloodmagic:sacrificerune>;
    var sel = <item:bloodmagic:selfsacrificerune>;
    var nei = <item:minecraft:netherite_ingot>;
    var viqd = <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:vibranium" as string});
    var ss = <item:emcworld:stainless_steel>;
    var wp = <item:endrem:witch_pupil>;
    var we = <item:endrem:witch_eye>;
    var ece = <item:endrem:end_crystal_eye>;
    var uldb = <item:dead_guys_untitled_deep_dark_:unactivated_lightn_dark_block>;
    var dwa = <item:dead_guys_untitled_deep_dark_:warden_antler>;
    var dso = <item:dead_guys_untitled_deep_dark_:sculk_ossein_shard>;
    var cei = <item:cataclysm:enderite_ingot>;
    var cha = <item:minecraft:charcoal>;
    var dsr = <item:dead_guys_untitled_deep_dark_:sculk_rambler_cut_root>;
    var aeg = <item:emcworld:advanced_emc_gem>;
    var seg = <item:emcworld:super_emc_gem>;
    var eeeg = <item:emcworld:epic_emc_gem>;
    var ki = <item:twilightforest:knightmetal_ingot>;
    var asi = <item:astralsorcery:starmetal_ingot>;
    var atmqd = <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:atm" as string});
    var csi = <item:emcworld:god_steel_ingot>;
    var eeg = <item:emcworld:epic_emc_gem>;
    var ai = <item:mythicbotany:alfsteel_ingot>;
    var unqd = <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:unobtainium" as string});
    var zi = <item:good_nights_sleep:zitrite_ingot>;
    var sa = <item:minecraft:spectral_arrow>;
    var iss = <item:mekanism:ingot_steel>;
    var ub = <item:emcworld:universal_ball>;
    var frc = <item:mekanismgenerators:fission_reactor_casing>;
    var aq = <item:astralsorcery:aquamarine>;
    var pa = <item:minecraft:paper>;
    var camt = getColorBlockCount();
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
        <item:emcworld:god_ice_sword>.withLevel(14)|
        <item:emcworld:god_fire_sword>.withLevel(14)|
        <item:emcworld:god_nature_sword>.withLevel(14)|
        <item:emcworld:god_null_sword>.withLevel(14)|
        <item:emcworld:purple_staff>.withLevel(14)|
        <item:emcworld:nature_staff>.withLevel(14)|
        <item:emcworld:creation>.withLevel(14)|
        <item:emcworld:super_star>.withLevel(14)|
        <item:emcworld:night_light>.withLevel(14)|
        <item:emcworld:red_green_dagger>.withLevel(14)
    ;
    var adss as IIngredient = 
        <item:emcworld:god_ice_sword>.withLevel(20)|
        <item:emcworld:god_fire_sword>.withLevel(20)|
        <item:emcworld:god_nature_sword>.withLevel(20)|
        <item:emcworld:god_null_sword>.withLevel(20)|
        <item:emcworld:purple_staff>.withLevel(20)|
        <item:emcworld:nature_staff>.withLevel(20)|
        <item:emcworld:creation>.withLevel(20)|
        <item:emcworld:super_star>.withLevel(20)|
        <item:emcworld:night_light>.withLevel(20)|
        <item:emcworld:red_green_dagger>.withLevel(20)
    ;
    var con as IItemStack[][] = [
        [
            <item:emcworld:update_base_purple>,
            <item:emcworld:update_base_bx_purple>,
            <item:emcworld:update_base_blue>,
            <item:emcworld:update_base_cyan>,
            <item:emcworld:update_base_green>,
            <item:emcworld:update_base_yellow>,
            <item:emcworld:update_base_orange>,
            <item:emcworld:update_base_red>
        ],
        [
            <item:emcworld:update_cost_purple>,
            <item:emcworld:update_cost_bx_purple>,
            <item:emcworld:update_cost_blue>,
            <item:emcworld:update_cost_cyan>,
            <item:emcworld:update_cost_green>,
            <item:emcworld:update_cost_yellow>,
            <item:emcworld:update_cost_orange>,
            <item:emcworld:update_cost_red>
        ],
        [
            <item:emcworld:update_addon_purple>,
            <item:emcworld:update_addon_bx_purple>,
            <item:emcworld:update_addon_blue>,
            <item:emcworld:update_addon_cyan>,
            <item:emcworld:update_addon_green>,
            <item:emcworld:update_addon_yellow>,
            <item:emcworld:update_addon_orange>,
            <item:emcworld:update_addon_red>
        ],
        [
            <item:emcworld:update_time_purple>,
            <item:emcworld:update_time_bx_purple>,
            <item:emcworld:update_time_blue>,
            <item:emcworld:update_time_cyan>,
            <item:emcworld:update_time_green>,
            <item:emcworld:update_time_yellow>,
            <item:emcworld:update_time_orange>,
            <item:emcworld:update_time_red>
        ]
    ];
    var epss as IIngredient = 
        <item:emcworld:god_ice_sword>.withMax()|
        <item:emcworld:god_fire_sword>.withMax()|
        <item:emcworld:god_nature_sword>.withMax()|
        <item:emcworld:god_null_sword>.withMax()|
        <item:emcworld:purple_staff>.withMax()|
        <item:emcworld:nature_staff>.withMax()|
        <item:emcworld:creation>.withMax()|
        <item:emcworld:super_star>.withMax()|
        <item:emcworld:night_light>.withMax()|
        <item:emcworld:red_green_dagger>.withMax()
    ;
    var amo as int[]=[
        1500,2000,10000,15000,5000,100,10000,6000,10000,6500,5000,5000,10000,10000,10000,8000,8000,8000,15000,6500,7000,800,400,10000,3000,1500
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
    var abag as ItemStack[] = [
        <item:projecte:light_gray_alchemical_bag>,
        <item:projecte:gray_alchemical_bag>,
        <item:projecte:red_alchemical_bag>,
        <item:projecte:magenta_alchemical_bag>,
        <item:projecte:pink_alchemical_bag>,
        <item:projecte:purple_alchemical_bag>,
        <item:projecte:blue_alchemical_bag>,
        <item:projecte:cyan_alchemical_bag>,
        <item:projecte:light_blue_alchemical_bag>,
        <item:projecte:green_alchemical_bag>,
        <item:projecte:lime_alchemical_bag>,
        <item:projecte:yellow_alchemical_bag>,
        <item:projecte:orange_alchemical_bag>,
        <item:projecte:white_alchemical_bag>,
        <item:projecte:brown_alchemical_bag>,
        <item:projecte:black_alchemical_bag>
    ];
    var sn as IItemStack[] = Ice.INSTANCE.getIce();
    var emc_stage as IItemStack[][] = [
        [
            <item:mekanism:ingot_tin>,
            <item:minecraft:iron_ingot>,
            <item:mekanism:ingot_bronze>,
            <item:mekanism:ingot_osmium>,
            <item:mekanism:ingot_copper>,
            <item:mekanism:ingot_lead>,
            <item:mekanism:block_tin>,
            <item:minecraft:iron_block>,
            <item:mekanism:block_bronze>,
            <item:mekanism:block_osmium>,
            <item:mekanism:block_copper>,
            <item:mekanism:block_lead>,
            <item:mekanism:nugget_tin>,
            <item:minecraft:iron_nugget>,
            <item:mekanism:nugget_bronze>,
            <item:mekanism:nugget_osmium>,
            <item:mekanism:nugget_copper>,
            <item:mekanism:nugget_lead>,
            <item:mekanism:dust_tin>,
            <item:mekanism:dust_iron>,
            <item:mekanism:dust_bronze>,
            <item:mekanism:dust_osmium>,
            <item:mekanism:dust_copper>,
            <item:mekanism:dust_lead>,
            <item:emcworld:silver_ingot>,
            <item:emcworld:magnesium_ingot>,
            <item:mekanism:dust_lapis_lazuli>,
            <item:minecraft:lapis_lazuli>,
            <item:minecraft:lapis_block>
        ],
        [
            <item:mekanism:ingot_uranium>,
            <item:mekanism:ingot_refined_glowstone>,
            <item:minecraft:gold_ingot>,
            <item:minecraft:diamond>,
            <item:minecraft:emerald>,
            <item:mekanism:block_uranium>,
            <item:mekanism:block_refined_glowstone>,
            <item:minecraft:gold_block>,
            <item:minecraft:diamond_block>,
            <item:minecraft:emerald_block>,
            <item:mekanism:nugget_uranium>,
            <item:mekanism:nugget_refined_glowstone>,
            <item:minecraft:gold_nugget>,
            <item:mekanism:dust_diamond>,
            <item:mekanism:dust_emerald>,
            <item:mekanism:dust_uranium>,
            <item:mekanism:dust_gold>,
            <item:emcworld:aluminum_ingot>,
            <item:emcworld:nickel_ingot>,
            <item:emcworld:drystone_ingot>,
            <item:mekanism:enriched_iron>,
            <item:mekanism:dust_steel>,
            <item:mekanism:ingot_steel>,
            <item:mekanism:block_steel>,
            <item:mekanism:nugget_steel>
        ],
        [
            <item:allthemodium:allthemodium_ingot>,
            <item:byg:pendorite_ingot>,
            <item:allthemodium:allthemodium_block>,
            <item:byg:pendorite_block>,
            <item:allthemodium:allthemodium_nugget>,
            <item:allthemodium:allthemodium_dust>,
            <item:emcworld:cold_ingot>,
            <item:emcworld:chlorophyte_ingot>,
            <item:emcworld:orichalcos_ingot>,
            <item:emcworld:sunlit_ingot>,
            <item:mekanism:dust_fluorite>,
            <item:mekanism:block_fluorite>
        ],
        [
            <item:allthemodium:vibranium_ingot>,
            <item:allthemodium:vibranium_block>,
            <item:allthemodium:vibranium_nugget>,
            <item:allthemodium:vibranium_dust>,
            <item:emcworld:niobium_ingot>,
            <item:minecraft:netherite_scrap>
        ],
        [
            <item:allthemodium:unobtainium_ingot>,
            <item:allthemodium:unobtainium_block>,
            <item:allthemodium:unobtainium_nugget>,
            <item:allthemodium:unobtainium_dust>
        ],
        [
            <item:emcworld:titanium_ingot>
        ]
    ];
    var weapon_gem as IItemStack[][] =[
        [
            <item:emcworld:blood_gemstone>,
            <item:emcworld:nature_gemstone>,
            <item:emcworld:lake_gemstone>,
            <item:emcworld:abyss_gemstone>
        ],
        [
            <item:the_afterlight:glyph_of_power>,
            <item:the_afterlight:glyph_of_sickness>,
            <item:the_afterlight:spectral_glyph>,
            <item:the_afterlight:glyph_of_knowledge>
        ]
    ];
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
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:bronze" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:clock" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:dark_matter" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:red_matter" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:sculk" as string}),
            <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:aluminum" as string}),
            unqd
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
            <item:mekanism:ingot_bronze>,
            <item:minecraft:clock>,
            <item:projecte:dark_matter>,
            <item:projecte:red_matter>,
            <item:dead_guys_untitled_deep_dark_:sculk>,
            <item:emcworld:aluminum_ingot>,
            <item:allthemodium:unobtainium_ingot>
        ]
    ];
    var gaia_item as IItemStack[]=[
        <item:emcworld:gaia_sword>,
        <item:emcworld:gaia_staff>,
        <item:emcworld:gaia_warhammer>,
        <item:emcworld:gaia_dagger>
    ];
    var staff_item as IItemStack[][]=[
        [
            <item:minecraft:oak_planks>,
            <item:minecraft:cobblestone>,
            <item:minecraft:iron_ingot>,
            <item:minecraft:gold_ingot>,
            <item:minecraft:diamond>
        ],
        [
            <item:emcworld:wooden_staff>,
            <item:emcworld:stone_staff>,
            <item:emcworld:iron_staff>,
            <item:emcworld:golden_staff>,
            <item:emcworld:diamond_staff>
        ],
        [
            <item:emcworld:wooden_warhammer>,
            <item:emcworld:stone_warhammer>,
            <item:emcworld:iron_warhammer>,
            <item:emcworld:golden_warhammer>,
            <item:emcworld:diamond_warhammer>
        ],
        [
            <item:emcworld:wooden_dagger>,
            <item:emcworld:stone_dagger>,
            <item:emcworld:iron_dagger>,
            <item:emcworld:golden_dagger>,
            <item:emcworld:diamond_dagger>
        ]
    ];
    for i in 1 .. 4{
        smithingRecipe(staff_item[i][4],bgi,gaia_item[i]);
    }
    smithingRecipe(<item:minecraft:diamond_sword>,bgi,gaia_item[0]);
    for i in 0 .. 5{
        staffRecipe(staff_item[0][i],staff_item[1][i]);
        warHammer(staff_item[0][i],staff_item[2][i]);
        dagger(staff_item[0][i],staff_item[3][i]);
    }
    staffRecipe(<item:emcworld:rainbow_ingot>,<item:emcworld:rainbow_staff>);
    craftingTable.removeByModid("projectex");
    //craftingTable.removeByModid("projecte");
    removeCraftRecipe(abag);
    smithingRecipe(staff_item[1][4],<item:cataclysm:ignitium_ingot>,<item:emcworld:netherite_staff>);
    smithingRecipe(staff_item[2][4],<item:cataclysm:ignitium_ingot>,<item:emcworld:netherite_warhammer>);
    add_emc_stage(emc_stage[0],3);
    add_emc_stage(emc_stage[1],4);
    add_emc_stage(emc_stage[2],5);
    add_emc_stage(emc_stage[3],6);
    add_emc_stage(emc_stage[4],7);
    add_emc_stage(emc_stage[5],8);
    setEMCStage(<item:mekanism:fluorite_gem>,1024,5);
    setEMCStage(<item:mekanism:dust_sulfur>,32,4);
    setEMCStage(<item:mekanism:pellet_antimatter>,46080000,114514);
    setEMCStage(<item:the_afterlight:moon_crystal>,19200,6);
    setEMCStage(<item:emcworld:universal_ball>,320000,8);
    removeRecipe([mt]);
    crockPotRecipe(new CTRequirement().addCategoryMin(new CrTFoodValue().put("meat",3.5)).addMustItem(<item:farmersdelight:ham>,1),<item:emcworld:ham_bat>,100);
    violet(<item:projectex:purple_collector>,<item:projectex:violet_collector>);
    violet(<item:projectex:purple_relay>,<item:projectex:violet_relay>);
    blue(<item:projectex:violet_collector>,<item:projectex:blue_collector>);
    blue(<item:projectex:violet_relay>,<item:projectex:blue_relay>);
    //test recipe.
    treeRitualRecipe([
        bx,bx,bx,bx
    ],bx,bx*3); // end
    addCraftShapedRecipeNoName([
        [dm,dm,dm],
        [dm,<item:emcworld:god_steel_ingot>,dm],
        [dm,dm,dm]
    ],<item:emcworld:emc_shield_supply>.withTag({emc_shield_speed: 4.0 as float, emc_shield: 0.0 as float, emc_maxShield: 30.0 as float}));
    pink(<item:projectex:magenta_collector>,<item:projectex:pink_collector>);
    pink(<item:projectex:magenta_relay>,<item:projectex:pink_relay>);
    infuserRecipe([sb,sb,sb,sb,sb],sp,2500,300000,2);
    addCraftShapedRecipeNoName([
        [sp,<item:mekanism:pellet_polonium>,sp],
        [<item:mekanism:pellet_plutonium>,sp,<item:mekanism:pellet_plutonium>],
        [sp,<item:mekanism:pellet_polonium>,sp]
    ],sr*2);
    addCraftShapedRecipeNoName([
        [tun,tun,tun],
        [tun,sr,tun],
        [tun,tun,tun]
    ],<item:emcworld:scroll_gold>);
    setEMCStage(<item:dead_guys_untitled_deep_dark_:sculk>,1024,8);
    addCraftShapelessRecipe([<item:minecraft:diamond_sword>,dm,dm],<item:emcworld:profession_sword>);
    addCraftShapelessRecipe([<item:minecraft:diamond_chestplate>,dm,dm],<item:emcworld:profession_tank>);
    addCraftShapedRecipeNoName([
        [a,dm,a],
        [dm,rm,dm],
        [a,dm,a]
    ],<item:emcworld:red_matter_crystal>);
    for i in 0 .. 4{
        nucleosyRecipe(ne_armor[i+4],<gas:mekanism:antimatter>*(10 * getDifficultyLoss()) as int,ne_armor[i],2000);
    }
    setEMCStage(<item:emcworld:niobium_nugget>,2048,5);
    addCraftShapedRecipeNoName([
        [iss,iss,iss],
        [iss,<item:minecraft:gold_block>,iss],
        [iss,iss,iss]
    ],<item:emcworld:emc_flower>);
    for i in 0 .. 4{
        nucleosyRecipe(weapon_gem[1][i],<gas:mekanism:antimatter>*100,weapon_gem[0][i],1500);
    }
    /*addCraftShapedRecipeNoName([
        [dee,dee,dee],
        [dee,<item:minecraft:iron_ingot>,dee],
        [dee,dee,dee]
    ],<item:emcworld:steel_furnace_core>);*/
    tartaricForgeRecipe([<item:emcworld:base_key>,<item:twilightforest:lamp_of_cinders>,<item:astralsorcery:rock_collector_crystal>.withTag({astralsorcery: {constellation: "astralsorcery:vicio" as string}}),<item:rats:dutchrat_wheel>],<item:emcworld:nether_key>,4096,1000);
    smithingRecipe(sword[0],atmqd*3,sword[1]);
    smithingRecipe(sword[1],viqd*3,sword[2]);
    terraPlateRecipe([<item:atum:ptah_godshard>],<item:good_nights_sleep:hope_mushroom>,125000);
    terraPlateRecipe([<item:atum:anput_godshard>],<item:good_nights_sleep:despair_mushroom>,125000);
    setEMCStage(<item:twilightforest:fiery_blood>,32768*2,6);
    setEMCStage(<item:twilightforest:fiery_tears>,32768*2,6);
    setEMCStage(<item:twilightforest:knightmetal_ingot>,16384,6);
    setEMCStage(<item:twilightforest:carminite>,8192,6);
    setEMCStage(<item:emcworld:unreal_metal>,(8192*2.5) as int,6);
    addNuggetAndIngotRecipe(<item:cataclysm:ignitium_ingot>,<item:cataclysm:ignitium_block>);
    modifyShapedRecipe([
        [<item:minecraft:lapis_lazuli>,<item:mekanism:alloy_reinforced>,<item:minecraft:lapis_lazuli>],
        [<item:minecraft:gold_ingot>,<item:minecraft:diamond>,<item:minecraft:gold_ingot>],
        [<item:minecraft:lapis_lazuli>,<item:mekanism:alloy_reinforced>,<item:minecraft:lapis_lazuli>],
    ],<item:mekanism:teleportation_core>);
    infuserRecipe([
        <item:astralsorcery:starmetal_ingot>,
        <item:astralsorcery:infused_crystal_sword>,
        <item:mekanism:pellet_polonium>|<item:mekanism:pellet_plutonium>,
        <item:mekanism:ingot_refined_obsidian>,
        <item:quark:rainbow_rune>
    ],<item:emcworld:purple_staff>,30000,1000000,2);
    infuserRecipe([
        <item:astralsorcery:starmetal_ingot>,
        <item:astralsorcery:infused_crystal_sword>,
        <item:mythicbotany:vanaheim_rune>,
        <item:mythicbotany:yggdrasil_branch>,
        <item:quark:rainbow_rune>
    ],<item:emcworld:nature_staff>,30000,1000000,2);
    smithingRecipe(sword[2],unqd*3,sword[3]);
    smithingRecipe(<item:mythicbotany:mjoellnir>,<item:minecraft:netherite_ingot>,<item:emcworld:creation>);
    smithingRecipe(<item:mythicbotany:mjoellnir>,<item:allthemodium:vibranium_allthemodium_alloy_ingot>,<item:emcworld:super_star>);
    smithingRecipe(<item:emcworld:diamond_dagger>,<item:minecraft:netherite_ingot>,<item:emcworld:night_light>);
    smithingRecipe(<item:emcworld:diamond_dagger>,<item:allthemodium:vibranium_allthemodium_alloy_ingot>,<item:emcworld:red_green_dagger>);
    crockPotRecipe(new CTRequirement().addCategoryMin(new CrTFoodValue().put("meat",1.5).put("sweetener",1.5)).addMustItem(<item:farmersdelight:smoked_ham>,1),<item:farmersdelight:honey_glazed_ham_block>,30);
    for i in 20 .. 24{
        removeSmithingRecipe(armor[i]);
    }
    for i in 24 .. 28{
        removeRecipe([armor[i],pdm[i]]);
    }
    addCraftShapedRecipeNoName([
        [a,em,a],
        [em,<item:emcworld:hardcore_stone>,em],
        [a,em,a]
    ],<item:emcworld:profession_core>);
    setEMCStage(emcc,112000000,114514);
    addCraftShapelessRecipe([
        <item:minecraft:gold_nugget>
    ],<item:pouchofunknown:pouch>);
    removeCompressionRecipe(unqd);
    for i in 0 .. amo.length{
        extendedCompressionRecipe(sing[1][i],sing[0][i],amo[i],i as int);
        if(EWItem.getItemEMC(sing[1][i]) != 0){
            var value as long = (EWItem.getItemEMC(sing[1][i]) * amo[i] as long) as long;
            setEMCStageName(sing[0][i],value,114514);
        }
    }
    if(getGameDifficulty() >= 1){
        removeRecipe(tor);
        yellow(<item:projectex:lime_collector>,<item:projectex:yellow_collector>);
        yellow(<item:projectex:lime_relay>,<item:projectex:yellow_relay>);
    }
    if(getGameDifficulty() == 3){
        for i in 0 .. 4{
            combiningRecipe(red_armor[i+4],<item:emcworld:dragon_steel>,red_armor[i]);
        }
        extendedCraftingShapelessRecipe([
            tui,tui,tui,tui,tui,tui,tui,tui,tui,tui,tui,tui,eus,eus,eus,eus,eus,eus,eus
        ],ifc,4);
        addCraftShapedRecipeNoName([
            [eis,eis,eis],
            [eis,eis,eis],
            [eis,eis,eis]
        ],tui);
        for i in [tui,<item:mekanism:pellet_antimatter>]{
            addCraftShapelessRecipe([
                eis,i
            ],i*8);
        }
        var kl as IItemStack[] = [ewi,essi,eri,eni];
        for i in kl{
            addCraftShapelessRecipe([
                eis,i
            ],i*64);
        }
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
        orange();
        white();
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
        extendedCompressionRecipe(ub,emcc,350,1);
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
        infuserRecipe([
            ub,<item:gobber2:dragon_star>,<item:emcworld:hard_steel>
        ],ds,6000,1000000000,4);
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
    //multiFurnaceRecipe(<tag:items:mekanism:colorable/concrete>,<item:emcworld:steel_furnace_brick>);
    removeSmithingRecipe(mmas);
    smithingRecipe(<item:mekanism:atomic_disassembler>,aqd*2,mmas);
    removeFurnaceRecipe([aq]);
    removeCraftRecipe([nei]);
    removeCraftRecipe(tung);
    furnaceRecipeNoName(aq*3,<item:emcworld:aquamarine_ore>,1);
    bloodAltarRecipe(<item:mekanism:steel_casing>,<item:emcworld:control_update_core>,15000,3);
    addNuggetAndBlockRecipe(
        <item:emcworld:copper_medal>,
        <item:emcworld:silver_medal>,
        <item:emcworld:gold_medal>
    );
    for i in 0 .. 15{
        var c as ItemStack = new Getter().getCollector()[i];
        var b as ItemStack = abag[i];
        addCraftShapelessRecipe([
            <item:pouchofunknown:pouch>,c
        ],b.asIItemStack());
    }
    combiningRecipe(<item:minecraft:stone>,<item:minecraft:obsidian>,<item:minecraft:blackstone>);
    removeFurnaceRecipe([<item:minecraft:nether_brick>]);
    runeAltarRecipe([<item:emcworld:drystone_ingot>,<item:botania:rune_fire>],<item:minecraft:nether_brick>*2,5000);
    addCraftShapelessRecipe([
        <item:emcworld:wooden_staff>,<item:projecte:dark_matter>,<item:projecte:dark_matter>
    ],<item:emcworld:nopower_staff>);
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
    removeCraftRecipe([we,ece,uldb]);
        extendedCombinationRecipe([
        <item:extendedcrafting:the_ultimate_block>,eeeg,eeeg,eeeg,eeeg
    ],<item:emcworld:infinity_emc_gem>);
    addNuggetAndIngotRecipe(cei,<item:cataclysm:enderite_block>);
    natureSpawnerRecipe([
        <item:dead_guys_untitled_deep_dark_:warden_antler>,
        <item:naturesaura:birth_spirit>,
        <item:stalwart_dungeons:void_crystal>,
        <item:cataclysm:void_jaw>
    ],10000,<entitytype:dungeonsmod:voidmaster>,"_void_master");
    combiningRecipe(<item:emcworld:big_emc_gem>*40,<item:minecraft:diamond>,<item:emcworld:biggest_emc_gem>);
    treeRitualRecipe([
        aeg,aeg,aeg,aeg,aeg,<item:cataclysm:ignitium_ingot>,<item:mekanism:pellet_antimatter>,<item:cataclysm:ignitium_ingot>
    ],<item:twilightforest:rainboak_sapling>,seg*2);
    infuserRecipe([
        seg,seg,seg,<item:cataclysm:void_core>,<item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:sculk" as string})
    ],eeeg,1000,50000000,4);
    green(<item:projectex:cyan_collector>,<item:projectex:green_collector>);
    green(<item:projectex:cyan_relay>,<item:projectex:green_relay>);
    lime(<item:projectex:green_collector>,<item:projectex:lime_collector>);
    lime(<item:projectex:green_relay>,<item:projectex:lime_relay>);
    modifyShapedRecipe([
        [dwa,<item:dead_guys_untitled_deep_dark_:miteasoma>,dwa],
        [dso,<item:dead_guys_untitled_deep_dark_:sculk_flagon>,dso],
        [dsr,<item:dead_guys_untitled_deep_dark_:sculk>,dsr]
    ],<item:dead_guys_untitled_deep_dark_:unactivated_darkn_light>);
    alchemalTableRecipe([
        <item:botania:brew_flask>.withTag({brewKey: "botania:overload" as string}),
        wp,
        <item:bloodmagic:tauoil>,
        <item:the_afterlight:spectral_glyph>,
        <item:undergarden:regalium_block>
    ],we,100000,4);
    oxidizingRecipe(<item:stalwart_dungeons:void_crystal>,<gas:emcworld:no_shape_void>*1000);
    reactionRecipe(<item:divinerpg:mortum_heart>,<fluid:mekanismgenerators:fusion_fuel>*10,<gas:emcworld:no_shape_void>*2000,<item:emcworld:super_emc_gem>,<gas:emcworld:condensation_void>*500);
    chemicalInfusingRecipe(<gas:emcworld:condensation_void>,<gas:emcworld:cosmic_flow>,<gas:emcworld:stable_void>);
    nucleosyRecipe(<item:endrem:end_crystal_block>,<gas:mekanism:antimatter>*100,ece,100);
    natureSpawnerRecipe([
        cei,
        <item:naturesaura:birth_spirit>,
        <item:minecraft:dragon_breath>,
        <item:stalwart_dungeons:chorundum>
    ],500000,<entitytype:cataclysm:ender_guardian>,"ender_guardian_summoning");
    extendedCraftingShapedRecipe([
        [a,gi,um,si,a],
        [a,gi,<item:mekanism:teleportation_core>,si,a],
        [a,<item:good_nights_sleep:positite_gem>,<item:emcworld:god_steel_ingot>,<item:good_nights_sleep:negatite_gem>,a],
        [a,a,ai,a,a],
        [a,a,ai,a,a]
    ],<item:emcworld:base_key>,2);
    metallurgicInfusingRecipe(con[0][1]*camt,<infuse_type:emcworld:gobber>*20,con[0][2]*camt);
    purifyingRecipe(<item:minecraft:coal_block>,<item:emcworld:activated_charcoal>,3);
    alchemalTableRecipe([
        <item:bloodmagic:looting_anointment_l>,
        <item:botania:brew_vial>.withTag({brewKey: "botania:overload" as string}),
        <item:bloodmagic:slate_vial>,
        <item:emcworld:big_emc_gem>
    ],<item:emcworld:skill_item1>*8,3000,3);
    addEMCStage(<item:emcworld:activated_charcoal>,3);
    runeAltarRecipe([
        con[0][2],con[0][2],con[0][2],con[0][2],con[0][2],<item:bloodmagic:defaultcrystal>
    ],con[0][3]*camt,10000);
    alchemalArrayRecipe(con[0][3]*camt,<item:rats:ghost_pirat_ectoplasm>,con[0][4]*camt);
    astralAltarRecipe([
        [a,a,a,a,a],
        [a,con[0][4],ip,con[0][4],a],
        [a,ip,con[0][4],ip,a],
        [a,con[0][4],ip,con[0][4],a],
        [a,a,a,a,a]
    ],con[0][5]*camt,1);
    addEMCStage(<item:emcworld:voucher>,114514);
    addCraftShapelessRecipe([<item:bloodmagic:strong_tau>,<item:bloodmagic:strong_tau>],<item:bloodmagic:weakbloodshard>);
    cyan(<item:projectex:blue_collector>,<item:projectex:cyan_collector>);
    cyan(<item:projectex:blue_relay>,<item:projectex:cyan_relay>);
    natureAltarRecipe(con[0][5]*camt,con[0][6]*camt,2,100000);
    removeRecipe([<item:the_afterlight:golden_shards_of_radiance>]);
    nucleosyRecipe(con[0][6]*camt,<gas:mekanism:antimatter>*3,con[0][7]*camt,100);
    addCraftShapedRecipeNoName([
        [a,con[0][0],a],
        [con[0][0],<item:botania:conjuration_catalyst>,con[0][0]],
        [a,con[0][0],a]
    ],con[2][0]*4);
    extraOresRecipe(<item:allthemodium:allthemodium_ore>,<slurry:allthemodium:dirty_allthemodium>,"atm_extra");
    extraOresRecipe(<item:allthemodium:vibranium_ore>,<slurry:allthemodium:dirty_vibranium>,"vib_extra");
    extraOresRecipe(<item:allthemodium:unobtainium_ore>,<slurry:allthemodium:dirty_unobtainium>,"uno_extra");
    addCraftShapedRecipeNoName([
        [con[0][1],sar,con[0][1]],
        [sel,<item:bloodmagic:apprenticebloodorb>.reuse(),sel],
        [con[0][1],sar,con[0][1]]
    ],con[2][1]*4);
    infuserRecipe([
        sing[0][20],sing[0][22],<item:botania:brew_flask>.withTag({brewKey: "botania:overload" as string}),con[0][7],con[0][7]
    ],con[3][7]*2,4000,1500000,3);
    infuserRecipe([
        sing[0][21],baseqd,<item:projecte:catalytic_lens>,<item:projecte:gem_of_eternal_density>,con[0][7]
    ],con[1][7],4000,1500000,3);
    reactionChamberRecipe(con[0][2],con[2][2],<fluid:emcworld:sodium_cyanide>*5000,<fluid:minecraft:empty>,<item:mythicbotany:nidavellir_rune>,[]);
    alchemalArrayRecipe(<item:emcworld:base_key>,<item:bloodmagic:life_essence_bucket>,<item:emcworld:twilight_key>);
    addNuggetAndIngotRecipe(<item:emcworld:niobium_nugget>,<item:emcworld:niobium_ingot>);
    addCraftShapedRecipeNoName([
        [dm,sc,dm],
        [sc,dm,sc],
        [dm,sc,dm]
    ],con[0][0]*camt);
    infuserRecipe([
        <item:hem:copparite>,<item:mythicbotany:asgard_rune>,<item:minecraft:nether_star>,con[0][4],con[0][4]
    ],con[2][4]*2,300,100000,1);
    treeRitualRecipe([
        con[0][5],con[0][5],
        <item:mekanism:pellet_antimatter>,
        <item:cataclysm:ignitium_ingot>,
        <item:naturesaura:token_euphoria>,
        <item:naturesaura:token_rage>,
        <item:naturesaura:token_terror>,
        <item:naturesaura:token_grief>
    ],<item:aether:golden_oak_sapling>,con[2][5]*2);
    infuserRecipe([
        con[0][6],<item:the_afterlight:livingessenceingot>,
        <item:undergarden:regalium_ingot>,
        <item:the_afterlight:glyph_of_power>,
        <item:astralsorcery:ritual_link>
    ],con[2][6],3000,1000000,3);
    infuserRecipe([
        baseqd,baseqd,baseqd,baseqd,con[0][7]
    ],con[2][7],5000,10000000,4);
    addCraftShapedRecipeNoName([
        [sg,sg,sg],
        [sg,<item:emcworld:hardcore_stone>,sg],
        [sg,sg,sg]
    ],<item:emcworld:prefix_core>);
    addCraftShapedRecipeNoName([
        [a,con[0][0],a],
        [con[0][0],<item:botania:hourglass>,con[0][0]],
        [a,con[0][0],a]
    ],con[3][0]*4);
    addCraftShapedRecipeNoName([
        [pon,con[0][1],pon],
        [con[0][1],<tag:items:atum:godshards>,con[0][1]],
        [pon,con[0][1],pon]
    ],con[3][1]*4);
    alchemalTableRecipe([
        <item:bloodmagic:etherealslate>,<item:bloodmagic:fortune_anointment_2>,<item:bloodmagic:quick_draw_anointment_l>,con[0][2]
    ],con[3][2],3000,2);
    addCraftShapelessRecipe([
        cha,cha,cha,cha
    ],<item:minecraft:coal>);
    addEMCStage(<item:emcworld:enriched_gobber>,4);
    setEMCStage(<item:gobber2:gobber2_globette>,2048,4);
    setEMCStage(<item:gobber2:gobber2_glob>,18432,4);
    setEMCStage(<item:gobber2:gobber2_ingot>,18514,4);
    setEMCStage(<item:gobber2:gobber2_block>,166626,4);
    setEMCStage(<item:gobber2:gobber2_globette_nether>,16384,6);
    setEMCStage(<item:gobber2:gobber2_glob_nether>,147456,6);
    setEMCStage(<item:gobber2:gobber2_ingot_nether>,148105,6);
    setEMCStage(<item:gobber2:gobber2_block_nether>,1332945,6);
    setEMCStage(<item:gobber2:gobber2_globette_end>,131072,8);
    setEMCStage(<item:gobber2:gobber2_glob_end>,1179648,8);
    setEMCStage(<item:gobber2:gobber2_ingot_end>,1327753,8);
    setEMCStage(<item:gobber2:gobber2_block_end>,11949777,8);
    infuserRecipe([
        <item:naturesaura:time_changer>,
        <item:stalwart_dungeons:awful_crystal>,
        <item:minecraft:netherite_ingot>,con[0][4],con[0][4]
    ],con[3][4]*2,500,300000,2);
    treeRitualRecipe([
        con[0][5],con[0][5],<item:mekanism:pellet_antimatter>,
        <item:twilightforest:time_sapling>,
        <item:cataclysm:ignitium_ingot>,
        <item:mythicbotany:vanaheim_rune>
    ],<item:byg:nightshade_sapling>,con[3][5]*2);
    infuserRecipe([
        <item:undergarden:forgotten_ingot>,
        <item:the_afterlight:spectral_glyph>,
        <item:astralsorcery:ritual_link>,
        <item:the_afterlight:lunariteingot>,con[0][6]
    ],con[3][6],4000,300000,3);
    addCraftShapedRecipeNoName([
        [a,con[0][0],a],
        [con[0][0],<item:thermal:machine_output_augment>,con[0][0]],
        [a,con[0][0],a]
    ],con[1][0]*4);
    addCraftShapedRecipeNoName([
        [alt,con[0][1],alt],
        [con[0][1],<item:aether:golden_amber>,con[0][1]],
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
    addCraftShapedRecipeNoName([
        [bge,ws,bge],
        [ws,<item:emcworld:hardcore_stone>,ws],
        [bge,ws,bge]
    ],<item:emcworld:weapon_upgrade_core>);
    removeCraftRecipe([
        <item:waystones:return_scroll>,
        <item:waystones:bound_scroll>,
        <item:waystones:warp_scroll>,
        <item:waystones:attuned_shard>,
        <item:waystones:warp_plate>,
        <item:waystones:warp_dust>
    ]);
    infuserRecipe([sa,sa,sa,sa,sa],<item:emcworld:raid_light>,15000,100000,1);
    infuserRecipe([ali,ali,ali,alv,alv],vaai*2,10000,500000,1);
    infuserRecipe([ali,ali,alv,alv,alv],vaai*3,10000,500000,1);
    infuserRecipe([ali,alv,a,a,a],vaai*32,10000,10000000,3);
    infuserRecipe([eoi,sn[5],ci,di,esi],rai,3000,800000,1);
    modifyShapedRecipe([
        [beg,beg,beg],
        [beg,<item:minecraft:ender_pearl>,beg],
        [beg,beg,beg]
    ],<item:waystones:warp_stone>);
    steelFurnaceRecipe([<item:gobber2:gobber2_ingot>,<item:mekanism:block_steel>],<item:mekanism:steel_casing>,300);
    steelFurnaceRecipe([<item:emcworld:enriched_gobber>,<item:mekanism:block_steel>],<item:mekanism:steel_casing>,600);
    infuserRecipe([ss,rai,ki,zi,asi],hs,5000,15000000,2);
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
    combiningRecipe(<item:mekanism:teleportation_core>,<item:astralsorcery:starmetal_ingot>,<item:emcworld:infuse_core>);
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
    //infuserRecipe([pa,pa,pa,pa,pa],sw,300,5000,1);
    removeCraftRecipe(ngb);
    removeCraftRecipe(ngb1);
    rotaryRecipe(<fluid:emcworld:sodium_cyanide>,<gas:emcworld:sodium_cyanide>);
    smithingRecipe(<item:gobber2:gobber2_bow>,gin*6,ngb1[0].asIItemStack());
    smithingRecipe(ngb[5].asIItemStack(),gin*12,ngb1[1].asIItemStack());
    smithingRecipe(ngb[7].asIItemStack(),gin*9,ngb1[2].asIItemStack());
    removeFurnaceRecipeByName(["stalwart_dungeons:tungsten_ingot_recipe"]);
    combiningRecipe(<item:stalwart_dungeons:tungsten_ingot>,<item:minecraft:netherite_scrap>*2,nei);
    metallurgicInfusingRecipe(<item:mekanism:ingot_steel>*2,<infuse_type:emcworld:ice>*1000,<item:emcworld:stainless_steel>);
    <recipetype:mekanism:painting>.addRecipe("bx",<item:emcworld:update_base_purple>*camt,<pigment:emcworld:bx>*128,<item:emcworld:update_base_bx_purple>*camt);
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
            <item:hem:blueleaf_grass>,
            <item:hem:blueleaf_flowering_lily>,
            <item:hem:lush_flower_1>,
            <item:naturesaura:aura_bloom>
        ],eni,4);
    }
}

public function add_emc_stage(item as IItemStack[],stage as int) as void{
    for i in item{
        addEMCStage(i,stage);
    }
}

public function pink(c as IItemStack,r as IItemStack) as void{
    infuserRecipe([
        <item:emcworld:pink_matter>,
        <item:rats:oratchalcum_block>,
        <item:rats:dutchrat_wheel>,
        c,
        <item:rats:arcane_technology>
    ],r,4000,5000000,2);
}

public function violet(c as IItemStack,r as IItemStack) as void{
    var evm = <item:emcworld:violet_matter>;
    treeRitualRecipe([
        <item:cataclysm:ignitium_block>,evm,evm,<item:emcworld:advanced_emc_gem>,<item:gobber2:gobber2_block_nether>,
        c,<item:byg:death_cap>,<item:naturesaura:token_euphoria>
    ],<item:byg:blue_enchanted_sapling>,r);
}

public function blue(c as IItemStack,r as IItemStack) as void{
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

public function cyan(c as IItemStack,r as IItemStack) as void{
    mythicInfuserRecipe([
        c,
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:sculk" as string}),
        <item:emcworld:cyan_matter>,
        <item:emcworld:super_emc_gem>,
        <item:dead_guys_untitled_deep_dark_:warden_antler>
    ],r,4000000,0xffffff,0xffffff);
}

public function green(c as IItemStack,r as IItemStack) as void{
    nucleosyRecipe(c,<gas:emcworld:stable_void>*1000,r,100);
}

public function lime(c as IItemStack,r as IItemStack) as void{
    alchemalTableRecipe([
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:alfsteel" as string}),
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:atm" as string}),
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:vibranium" as string}),
        c
    ],r,300000,4);
}

public function p(a as IItemStack,b as IItemStack,c as IItemStack,d as IItemStack) as void{
    addCraftShapedRecipeNoName([
        [a,b,a],
        [b,c,b],
        [a,b,a]
    ],d);
}

public function kt(a as IItemStack,b as IItemStack,c as IItemStack[][]) as void{
    for i in c{
        p(a,b,i[0],i[1]);
    }
}

public function yellow(c as IItemStack,r as IItemStack) as void{
    p(<item:emcworld:yellow_matter>,<item:emcworld:dragon_steel>,c,r);
}

public function orange() as void{
    kt(<item:emcworld:orange_matter>,<item:emcworld:emc_core>,[[<item:projectex:yellow_collector>,<item:projectex:orange_collector>],[<item:projectex:yellow_relay>,<item:projectex:orange_relay>]]);
}

public function white() as void{
    kt(<item:emcworld:clay_matter>,<item:extendedcrafting:the_ultimate_ingot>,[[<item:projectex:orange_relay>,<item:projectex:white_relay>],[<item:projectex:orange_collector>,<item:projectex:white_collector>]]);
}