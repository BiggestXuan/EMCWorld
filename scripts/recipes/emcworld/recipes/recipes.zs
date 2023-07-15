#priority 51
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IIngredient;

import crafttweaker.api.recipe.Replacer;

import mods.emcworld.CrTFoodValue;
import mods.emcworld.CrTRequirement;
import mods.emcworld.CrTWeightItem;
import mods.emcworld.CrTSingularity;
import mods.emcworld.EWItem;
import mods.emcworld.EMCHelper;
import mods.emcworld.ItemUtils;

public function emcworldRecipe() as void{
    var al = <item:mythicbotany:alfheim_rune>;
    var a = <item:minecraft:air>;
    var dim = <item:minecraft:diamond>;
    var beg = <item:emcworld:big_emc_gem>;
    var sg = <item:emcworld:scroll_green>;
    var em = <item:minecraft:emerald>;
    var test = <item:emcworld:test_block>;
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
    var aqd = CrTSingularity.getCrTSingularity("alfsteel");
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
    var anti = <item:mekanism:pellet_antimatter>;
    var mr = <item:astralsorcery:marble_runed>;
    var st = <item:astralsorcery:starmetal>;
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
    var bp = <item:minecraft:blaze_powder>;
    var cho = <tag:items:candyworld:chocolate_bars>;
    var ip = <item:astralsorcery:illumination_powder>;
    var sar = <item:bloodmagic:sacrificerune>;
    var sel = <item:bloodmagic:selfsacrificerune>;
    var nei = <item:minecraft:netherite_ingot>;
    var viqd = CrTSingularity.getCrTSingularity("vibranium");
    var ss = <item:emcworld:stainless_steel>;
    var wp = <item:endrem:witch_pupil>;
    var we = <item:endrem:witch_eye>;
    var wss = <item:minecraft:wither_skeleton_skull>;
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
    var baseqd as IIngredient = new CrTSingularity("iron",2).addSingularity("steel","nickel","copper","lead","aluminum","silver").asIIngredient(); 
    var bases as IIngredient = ItemUtils.getEMCGodItemWithLevel(14);
    var adss as IIngredient = ItemUtils.getEMCGodItemWithLevel(20);
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
    var epss as IIngredient = ItemUtils.getEMCGodItemWithMaxLevel();
    var amo as int[]=[
        1500,2000,10000,15000,5000,100,10000,6000,10000,6500,5000,5000,10000,10000,10000,8000,8000,8000,15000,6500,7000,800,400,10000,3000,1500
    ];
    var red_armor as IItemStack[]=[
        <item:emcworld:guardian_helmet>,
        <item:emcworld:guardian_chestplate>,
        <item:emcworld:guardian_leggings>,
        <item:emcworld:guardian_boots>,
        <item:emcworld:fire_red_helmet>,
        <item:emcworld:fire_red_chestplate>,
        <item:emcworld:fire_red_leggings>,
        <item:emcworld:fire_red_boots>
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
        <item:gobber2:gobber2_boots>,
        <item:gobber2:gobber2_sword>,
        <item:gobber2:gobber2_axe>,
        <item:gobber2:gobber2_pickaxe>,
        <item:gobber2:gobber2_shovel>,
        <item:gobber2:gobber2_hoe>
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
    var pipez as IItemStack[] = [
        <item:pipez:item_pipe>,
        <item:pipez:fluid_pipe>,
        <item:pipez:energy_pipe>,
        <item:pipez:gas_pipe>
    ];
    var pipez_u as ItemStack[] = [
        <item:pipez:basic_upgrade>,
        <item:pipez:improved_upgrade>,
        <item:pipez:advanced_upgrade>,
        <item:pipez:ultimate_upgrade>,
        <item:pipez:infinity_upgrade>
    ];
    var pipez_m as IItemStack[] = [
        <item:mekanism:basic_control_circuit>,
        <item:minecraft:bucket>,
        <item:minecraft:redstone>,
        <item:minecraft:glass>
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
            <item:mekanism:ingot_osmium>,
            <item:minecraft:iron_ingot>
        ],
        [
            <item:mekanism:ingot_tin>,
            <item:mekanism:ingot_bronze>,
            <item:mekanism:ingot_copper>,
            <item:mekanism:ingot_lead>,
            <item:emcworld:silver_ingot>,
            <item:emcworld:magnesium_ingot>,
            <item:minecraft:lapis_lazuli>
        ],
        [
            <item:mekanism:ingot_uranium>,
            <item:mekanism:ingot_refined_glowstone>,
            <item:minecraft:gold_ingot>,
            <item:minecraft:diamond>,
            <item:emcworld:aluminum_ingot>,
            <item:emcworld:nickel_ingot>,
            <item:emcworld:drystone_ingot>,
            <item:mekanism:enriched_iron>,
            <item:mekanism:ingot_steel>
        ],
        [
            <item:allthemodium:allthemodium_ingot>,
            <item:byg:pendorite_ingot>,
            <item:emcworld:cold_ingot>,
            <item:emcworld:chlorophyte_ingot>,
            <item:emcworld:orichalcos_ingot>
        ],
        [
            <item:allthemodium:vibranium_ingot>,
            <item:emcworld:niobium_ingot>,
            <item:emcworld:sunlit_ingot>,
            <item:minecraft:netherite_scrap>
        ],
        [
            <item:allthemodium:unobtainium_ingot>
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
            <item:aerialhell:ruby>,
            <item:aerialhell:azurite_crystal>,
            <item:aerialhell:fluorite>,
            <item:aerialhell:volucite_vibrant>
        ]
    ];
    var sing as IItemStack[][] = [
        [
            crts("atm"),
            crts("vibranium"),
            crts("invar"),
            crts("coal"),
            crts("electrum"),
            crts("alfsteel"),
            crts("redstone"),
            crts("emerald"),
            crts("glowstone"),
            crts("steel"),
            crts("diamond"),
            crts("uranium"),
            crts("iron"),
            crts("lead"),
            crts("copper"),
            crts("silver"),
            crts("tin"),
            crts("nickel"),
            crts("lapis_lazuli"),
            crts("bronze"),
            crts("clock"),
            crts("dark_matter"),
            crts("red_matter"),
            crts("sculk"),
            crts("aluminum"),
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
            dm,
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
        <item:emcworld:gaia_dagger>,
        <item:emcworld:gaia_gun>
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
        ],
        [
            <item:emcworld:wooden_gun>,
            <item:emcworld:stone_gun>,
            <item:emcworld:iron_gun>,
            <item:emcworld:golden_gun>,
            <item:emcworld:diamond_gun>
        ]
    ];
    for i in 1 .. 5{
        smithingRecipe(staff_item[i][4],bgi,gaia_item[i]);
    }
    smithingRecipe(<item:minecraft:diamond_sword>,bgi,gaia_item[0]);
    for i in 0 .. 5{
        staffRecipe(staff_item[0][i],staff_item[1][i]);
        warHammer(staff_item[0][i],staff_item[2][i]);
        dagger(staff_item[0][i],staff_item[3][i]);
        gun(staff_item[0][i],staff_item[4][i]);
    }
    smithingRecipe(gaia_item[4],<item:emcworld:rainbow_ingot>,<item:emcworld:rainbow_gun>);
    staffRecipe(<item:emcworld:rainbow_ingot>,<item:emcworld:rainbow_staff>);
    craftingTable.removeByModid("projectex");
    //craftingTable.removeByModid("projecte");
    removeCraftRecipe(abag);
    smithingRecipe(staff_item[1][4],<item:cataclysm:ignitium_ingot>,<item:emcworld:netherite_staff>);
    smithingRecipe(staff_item[2][4],<item:cataclysm:ignitium_ingot>,<item:emcworld:netherite_warhammer>);
    add_emc_stage(emc_stage[0],2);
    add_emc_stage(emc_stage[1],3);
    add_emc_stage(emc_stage[2],4);
    add_emc_stage(emc_stage[3],5);
    add_emc_stage(emc_stage[4],6);
    add_emc_stage(emc_stage[5],7);
    add_emc_stage(emc_stage[6],8);
    blue(<item:projectex:violet_relay>,<item:projectex:blue_relay>);
    setEMCStage(anti,46080000,114514);
    setEMCStage(<item:emcworld:universal_ball>,320000,8);
    removeCraftRecipeIItemStack([mt,<item:quark:pickarang>,<item:gobber2:block_looter>,<item:gobber2:block_protector>,<item:gobber2:block_defender>]);
    crockPotRecipe(new CrTRequirement().addCategoryMin(new CrTFoodValue().put("meat",3.5)).addMustItem(<item:farmersdelight:ham>,1),<item:emcworld:ham_bat>,100);
    violet(<item:projectex:purple_collector>,<item:projectex:violet_collector>);
    blue(<item:projectex:violet_collector>,<item:projectex:blue_collector>);
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
    violet(<item:projectex:purple_relay>,<item:projectex:violet_relay>);
    explosionRecipe(<item:candyworld:crystallized_sugar>,<item:emcworld:stone_shard>,0.97,true);
    natureSpawnerRecipe([
        <item:aerialhell:arsonist_ingot>,<item:aerialhell:overheated_ruby>
    ],20000,<entitytype:aerialhell:chained_god>,"emcworld");
    natureSpawnerRecipe([
        <item:aerialhell:lunatic_crystal>,<item:aerialhell:magmatic_gel>
    ],20000,<entitytype:aerialhell:lunatic_priest>,"emcworld");
    natureSpawnerRecipe([
        <item:aerialhell:obsidian_shard>,<item:aerialhell:mud_bone_block>
    ],20000,<entitytype:aerialhell:mud_cycle_mage>,"emcworld");
    setEMCStage(<item:dead_guys_untitled_deep_dark_:sculk>,1024,8);
    addCraftShapelessRecipe([<item:minecraft:wooden_sword>,dm,dm],<item:emcworld:profession_sword>);
    addCraftShapelessRecipe([<item:minecraft:leather_chestplate>,dm,dm],<item:emcworld:profession_tank>);
    addCraftShapedRecipeNoName([
        [a,dm,a],
        [dm,rm,dm],
        [a,dm,a]
    ],<item:emcworld:red_matter_crystal>);
    for i in 0 .. 4{
        nucleosyRecipe(ne_armor[i+4],<gas:mekanism:antimatter>*(10 * getDifficultyLoss()) as int,ne_armor[i],2000);
    }
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
    smithingRecipe(sword[1],new CrTSingularity("vibranium",3).asIIngredient(),sword[2]);
    modifyShapedRecipe([
        [bp,cho,bp],
        [cho,<item:minecraft:glowstone_dust>,cho],
        [bp,cho,bp]
    ],<item:candyworld:teleporter>);
    infuserRecipe([
        <item:minecraft:nether_star>,
        wss,wss,wss,wss
    ],<item:cataclysm:witherite_ingot>,600,1000000,2);
    terraPlateRecipe([<item:atum:ptah_godshard>],<item:good_nights_sleep:hope_mushroom>,125000);
    terraPlateRecipe([<item:atum:anput_godshard>],<item:good_nights_sleep:despair_mushroom>,125000);
    addNuggetAndIngotRecipe(<item:cataclysm:ignitium_ingot>,<item:cataclysm:ignitium_block>);
    gemTrade();
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
    crockPotRecipe(new CrTRequirement().addCategoryMin(new CrTFoodValue().put("meat",1.5).put("sweetener",1.5)).addMustItem(<item:farmersdelight:smoked_ham>,1),<item:farmersdelight:honey_glazed_ham_block>,30);
    for i in 20 .. 24{
        removeSmithingRecipe(armor[i]);
    }
    addEMCWorldSmithingRecipe();
    for i in 24 .. 28{
        removeCraftRecipeIItemStack([armor[i],pdm[i]]);
    }
    addCraftShapedRecipeNoName([
        [a,em,a],
        [em,<item:emcworld:hardcore_stone>,em],
        [a,em,a]
    ],<item:emcworld:profession_core>);
    setEMCStage(emcc,112000000,114514);
    removeCompressionRecipe(unqd);
    for i in 0 .. amo.length{
        extendedCompressionRecipe(sing[1][i],sing[0][i],amo[i],i as int);
        if(EWItem.getItemEMC(sing[1][i]) != 0){
            var value as long = (EWItem.getItemEMC(sing[1][i]) * amo[i] as long) as long;
            setEMCStageName(sing[0][i],value,114514);
        }
    }
    infuserRecipe([
        <item:minecraft:diamond_helmet>,
        <item:minecraft:diamond_chestplate>,
        <item:minecraft:diamond_leggings>,
        <item:minecraft:diamond_boots>,
        <item:minecraft:diamond_block>
    ],<item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:platinum" as string}),600,100000,1);
    if(getGameDifficulty() >= 1){
        removeCraftRecipeIItemStack(tor);
        yellow(<item:projectex:lime_collector>,<item:projectex:yellow_collector>);
        yellow(<item:projectex:lime_relay>,<item:projectex:yellow_relay>);
    }
    for i in 0 .. pipez.length{
        removeCraftRecipe([pipez[i]]);
        removeCraftRecipe([pipez_u[i]]);
        pipe(pipez_m[i],pipez[i]*8); 
    }
    metallurgicInfusingRecipe(<item:mekanism:ingot_tin>,<infuse_type:mekanism:redstone>*20,pipez_u[0]);
    metallurgicInfusingRecipe(pipez_u[0],<infuse_type:mekanism:diamond>*40,pipez_u[1]);
    metallurgicInfusingRecipe(pipez_u[1],<infuse_type:mekanism:refined_obsidian>*80,pipez_u[2]);
    combiningRecipe(pipez_u[2],<item:minecraft:netherite_ingot>,pipez_u[3]);
    combiningRecipe(pipez_u[3],<item:mekanism:pellet_antimatter>,pipez_u[4]);
    addEMCWorldEMCStage();
    for i in red_armor{
        <tag:items:emcworld:god_weapon>.add(i);
        <tag:items:emcworld:god_weapon_armor>.add(i);
    }
    removeExtendedCraftRecipe(eus);
    combiningRecipe(<item:scalinghealth:medkit>,<item:emcworld:biggest_emc_gem>,<item:emcworld:emc_healing_bag>);
    if(getGameDifficulty() == 3){
        extendedCraftingShapelessRecipe(CrTSingularity.getAllSingularityAsIIngredientArray(),eus,4);
        for i in 0 .. 4{
            combiningRecipe(red_armor[i+4].withArmorLevelMax(),<item:emcworld:dragon_steel>,red_armor[i]);
        }
        tartaricForgeRecipe([
            <item:bloodmagic:dungeon_metal>,
            <item:minecraft:enchanted_golden_apple>,
            <item:bloodmagic:weakbloodshard>,
            <item:bloodmagic:reagentbinding>
        ],<item:emcworld:exception_apple>,1024,512);
        addCraftShapedRecipeNoName([
            [eis,eis,eis],
            [eis,<tag:items:forge:feathers>,eis],
            [eis,eis,eis]
        ],<item:emcworld:flying_gem>);
        craftTogether([<item:emcworld:infinity_sword>,<item:emcworld:infinity_module>]);
        addCraftShapedRecipeNoName([
            [tui,anti,tui],
            [anti,eis,anti],
            [tui,anti,tui]
        ],<item:emcworld:energy_protect_module>);
        addCraftShapedRecipeNoName([
            [lg[3],lg[3],lg[3]],
            [lg[3],<item:emcworld:illager_shard>,lg[3]],
            [lg[3],lg[3],lg[3]]
        ],<item:emcworld:lucky_gem_emerald>*8);
        combiningRecipe(<item:projecte:transmutation_table>,<item:emcworld:magenta_matter>,<item:projecte:transmutation_tablet>);
        piglinTrade(<item:emcworld:advanced_emc_gem>,[
            new CrTWeightItem(red_armor[4],1,1,3),
            new CrTWeightItem(red_armor[5],1,1,3),
            new CrTWeightItem(red_armor[6],1,1,3),
            new CrTWeightItem(red_armor[7],1,1,3),
            new CrTWeightItem(<item:mekanism:pellet_polonium>,1,3,1),
            new CrTWeightItem(<item:mekanism:pellet_plutonium>,1,3,1),
            new CrTWeightItem(<item:emcworld:biggest_emc_gem>,1,16,18)
        ] as CrTWeightItem[]);
        addCraftShapelessRecipe([<item:emcworld:biggest_emc_gem>],<item:emcworld:big_emc_gem>*32);
        addCraftShapedRecipeNoName([
            [tui,tui,tui],
            [tui,eus,tui],
            [tui,tui,tui]
        ],ifc);
        addCraftShapedRecipeNoName([
            [eis,eis,eis],
            [eis,eis,eis],
            [eis,eis,eis]
        ],tui);
        for i in [tui,anti]{
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
        addCraftShapedRecipeNoName([
            [dim,dim,dim],
            [dim,<item:emcworld:infinity_emc_gem>,dim],
            [dim,dim,dim]
        ],<item:emcworld:crystal_matrix_ingot>);
        extendedCraftingShapedRecipe([
            [a,a,a,a,a,a,cmi,cmi,cmi],
            [a,a,a,a,a,cmi,ifc,ifc,cmi],
            [cmi,a,a,a,cmi,ifc,ifc,ifc,cmi],
            [a,cmi,a,cmi,ifc,ifc,ifc,cmi,a],
            [a,a,cmi,ifc,ifc,ifc,cmi,a,a],
            [a,a,cmi,epss,cmi,cmi,a,a,a],
            [a,cmi,ifc,cmi,cmi,a,a,a,a],
            [cmi,ifc,cmi,a,a,cmi,a,a,a],
            [cmi,cmi,a,a,a,a,cmi,a,a]
        ],<item:emcworld:infinity_sword>,4);
        addCraftShapelessRecipe([<item:extendedcrafting:ultimate_singularity>,<item:emcworld:illager_shard>],<item:extendedcrafting:ultimate_singularity>*2);
    }
    if(getGameDifficulty() >= 2){
        orange();
        copyFinalIngot(eni);
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
        white();
        copyFinalIngot(ewi);
        extendedCompressionRecipe(ub,emcc,350,1);
        for i in pdm{
            Replacer.forTypes(craftingTable)
            .forMods("projecte")
            .forOutput(i,craftingTable)
            .replace(dm,pfm)
            .replace(<item:projecte:red_matter>,pcm)
            .execute();
        }
        FinalExtendedRecipes();
        copyFinalIngot(eri);
        removeCraftRecipeIItemStack([pdm[22]]);
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
        copyFinalIngot(essi);
    }else{
        removeCraftRecipeIItemStack(pdm);
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
    modifyShapelessRecipe([
        <item:divinerpg:purple_blaze>,<item:divinerpg:purple_blaze>,<item:minecraft:netherite_ingot>,<item:emcworld:advanced_emc_gem>
    ],<item:divinerpg:infernal_flame>);
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
    removeCraftRecipeIItemStack(armor);
    //multiFurnaceRecipe(<tag:items:mekanism:colorable/concrete>,<item:emcworld:steel_furnace_brick>);
    removeSmithingRecipe(mmas);
    smithingRecipe(<item:mekanism:atomic_disassembler>,new CrTSingularity("alfsteel",2).asIIngredient(),mmas);
    removeFurnaceRecipe([aq]);
    infuserRecipe([ub,aui,aui,alv,alv],<item:allthemodium:unobtainium_vibranium_alloy_ingot>*7,25000,6000000,3);
    removeCraftRecipe([nei]);
    infuserRecipe([ub,aui,alv,alv,alv],<item:allthemodium:unobtainium_vibranium_alloy_ingot>*5,25000,7000000,3);
    removeCraftRecipe(tung);
    furnaceRecipeNoName(aq*3,<item:emcworld:aquamarine_ore>,1);
    infuserRecipe([ub,aui,aui,ali,ali],<item:allthemodium:unobtainium_allthemodium_alloy_ingot>*6,30000,9000000,3);
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
            abag[11],c
        ],b.asIItemStack());
    }
    removeCraftRecipe([abag[13]]);
    addCraftShapelessRecipe([
        <tag:items:forge:dyes/white>,abag[11]
    ],abag[13]);
    removeCraftRecipe([abag[11]]);
    addCraftShapelessRecipe([
        <tag:items:forge:dyes/black>,abag[11]
    ],<item:projecte:black_alchemical_bag>);
    combiningRecipe(<item:minecraft:stone>,<item:minecraft:obsidian>,<item:minecraft:blackstone>);
    removeFurnaceRecipe([<item:minecraft:nether_brick>]);
    runeAltarRecipe([<item:emcworld:drystone_ingot>,<item:botania:rune_fire>],<item:minecraft:nether_brick>*2,5000);
    ProRecipe();
    natureSpawnerRecipe([
        <item:naturesaura:birth_spirit>,bx,bx,bx
    ],500000,<entitytype:emcworld:biggest_xuan>,"bx");
    addCraftShapelessRecipe([
        <tag:items:forge:nuggets/gold>
    ],abag[11]);
    alchemalTableRecipe([
        <item:botania:gaia_ingot>,
        <item:good_nights_sleep:zitrite_ingot>,
        <item:good_nights_sleep:rainbow_ingot>
    ],<item:emcworld:unreal_metal>*getModifyRecipeAmount(),10000,4);
    addCraftShapedRecipeNoName([
        [al,csi,al],
        [ra,<item:emcworld:control_update_core>,zi],
        [frc,<item:emcworld:biggest_emc_gem>,frc]
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
    ],getBook());
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
        aeg,aeg,aeg,aeg,aeg,<item:cataclysm:ignitium_ingot>,anti,<item:cataclysm:ignitium_ingot>
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
        <item:aerialhell:lunatic_crystal>,
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
    alchemalArrayRecipe(con[0][3],<item:rats:ghost_pirat_ectoplasm>,con[0][4]*camt);
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
    natureAltarRecipe(con[0][5],con[0][6]*camt,2,100000);
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
    addCraftShapedRecipeNoName([
        [sg,sg,sg],
        [sg,<item:emcworld:hardcore_stone>,sg],
        [sg,sg,sg]
    ],<item:emcworld:prefix_core>);
    treeRitualRecipe([
        <item:emcworld:chlorophyte_ingot>,<item:emcworld:hardcore_stone>
    ],a,<item:emcworld:vis_conversion_core>);
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
    setEMCStage(<item:gobber2:gobber2_globette_nether>,16384,6);
    setEMCStage(<item:gobber2:gobber2_glob_nether>,147456,6);
    setEMCStage(<item:gobber2:gobber2_ingot_nether>,148105,6);
    setEMCStage(<item:gobber2:gobber2_globette_end>,131072,8);
    setEMCStage(<item:gobber2:gobber2_glob_end>,1179648,8);
    setEMCStage(<item:gobber2:gobber2_ingot_end>,1327753,8);
    infuserRecipe([
        <item:naturesaura:time_changer>,
        <item:stalwart_dungeons:awful_crystal>,
        <item:minecraft:netherite_ingot>,con[0][4],con[0][4]
    ],con[3][4]*2,500,300000,2);
    treeRitualRecipe([
        con[0][5],con[0][5],anti,
        <item:twilightforest:time_sapling>,
        <item:cataclysm:ignitium_ingot>,
        <item:mythicbotany:vanaheim_rune>
    ],<item:byg:nightshade_sapling>,con[3][5]*2);
    addCraftShapedRecipeNoName([
        [rm,rm,rm],
        [rm,<tag:items:emcworld:godblock>,rm],
        [rm,rm,rm]
    ],<item:projectex:energy_link>);
    addCraftShapedRecipeNoName([
        [a,bge,a],
        [bge,<item:astralsorcery:parchment>,bge],
        [a,bge,a]
    ],<item:astralsorcery:constellation_paper>.withTag({astralsorcery: {constellationName: "emcworld:emc" as string}}));
    infuserRecipe([
        <item:undergarden:forgotten_ingot>,
        <item:aerialhell:arsonist_ingot>,
        <item:astralsorcery:ritual_link>,
        <item:aerialhell:arsonist_ingot>,con[0][6]
    ],con[3][6],4000,300000,3);
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
    EMCHelper.setItemEMC(<item:emcworld:dust_aquamarine>,256);
    setEMCStage(<item:bloodmagic:ingot_hellforged>,1024,4);
    for i in [pp,pp2]{
        setEMCStage(i,(134560*getDifficultyLoss()) as int,6);
    }
    setEMCStage(<item:mekanism:reprocessed_fissile_fragment>,92160,5);
    EMCHelper.setItemEMC(<item:mekanism:dust_refined_obsidian>,4112);
    setEMCStage(<item:mekanism:ingot_refined_obsidian>,4112+756,5);
    crushingRecipe(<item:atlantis:aquamarine_gem>,<item:emcworld:dust_aquamarine>,1);
    addEMCStage(<item:minecraft:emerald>,9);
    setEMCStage(<item:emcworld:illager_gem>,16384,9);
    addCraftShapelessRecipe([
        <item:emcworld:illager_gem>
    ],<item:minecraft:emerald>*8);
    removeCraftRecipeIItemStack([
        <item:bountifulbaubles:spectral_silt>,
        <item:allthemodium:alloy_sword>,
        <item:allthemodium:alloy_pick>,
        <item:allthemodium:alloy_axe>,
        <item:allthemodium:alloy_shovel>,
        <item:allthemodium:alloy_paxel>
    ]);
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
    crushingRecipe(<item:mekanism:enriched_diamond>,<item:minecraft:coal>,64);
    infuserRecipe([eoi,sn[5],ci,di,esi],rai,3000,800000,1);
    astralAltarRecipe([
        [mr,a,a,a,mr],
        [mr,st,st,st,mr],
        [mr,st,st,st,mr],
        [mr,st,st,st,mr],
        [mr,mr,mr,mr,mr]
    ],<item:emcworld:star_pedestal>,4);
    modifyShapedRecipe([
        [beg,beg,beg],
        [beg,<item:minecraft:ender_pearl>,beg],
        [beg,beg,beg]
    ],<item:waystones:warp_stone>);
    steelFurnaceRecipe([<item:gobber2:gobber2_ingot>,<item:mekanism:block_steel>],<item:mekanism:steel_casing>,300);
    steelFurnaceRecipe([<item:emcworld:enriched_gobber>,<item:mekanism:block_steel>],<item:mekanism:steel_casing>,600);
    infuserRecipe([ss,rai,ki,zi,asi],hs,5000,15000000,2);
    addNuggetAndIngotRecipe(dm,<item:projecte:dark_matter_block>);
    addNuggetAndIngotRecipe(<item:projecte:red_matter>,<item:projecte:red_matter_block>);
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
        removeSmithingRecipe(ignis[i]);
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
    for i in game.items{
        if(EWItem.cantTrans(i)){
            addEMCStage(i,10);
        }
    }
}