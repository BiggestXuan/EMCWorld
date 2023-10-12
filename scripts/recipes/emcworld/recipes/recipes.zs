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
    var beg = <item:emcworld:big_emc_gem>;
    var sg = <item:emcworld:scroll_green>;
    var em = <item:minecraft:emerald>;
    var test = <item:emcworld:test_block>;
    var sp = <item:emcworld:scroll_purple>;
    var sw = <item:emcworld:scroll_white>;
    var tun = <item:extendedcrafting:the_ultimate_nugget>;
    var ucc = <item:mekanism:ultimate_control_circuit>;
    var sr = <item:emcworld:scroll_red>;
    var ra = <item:good_nights_sleep:rainbow_ingot>;
    var gi = <item:minecraft:gold_ingot>;
    var bgi = <item:botania:gaia_ingot>;
    var ws = <item:emcworld:scroll_white>;
    var bge = <item:emcworld:biggest_emc_gem>;
    var um = <item:emcworld:unreal_metal>;
    var si = <item:thermal:signalum_ingot>;
    var ifc = <item:emcworld:infinity_catalyst>;
    var mmas = <item:mythicbotany:alfsteel_sword>;
    var aqd = CrTSingularity.getCrTSingularity("alfsteel");
    var gr = <item:gobber2:gobber2_rod>;
    var dm = <item:projecte:dark_matter>;
    var rm = <item:projecte:red_matter>;
    var gin = <item:gobber2:gobber2_ingot_nether>;
    var oi = <item:rats:oratchalcum_ingot>;
    var pp2 = <item:mekanism:pellet_plutonium>;
    var pp = <item:mekanism:pellet_polonium>;
    var oib = <item:rats:oratchalcum_block>;
    var eoi = <item:emcworld:orichalcos_ingot>;
    var ci = <item:emcworld:chlorophyte_ingot>;
    var di = <item:emcworld:drystone_ingot>;
    var eus = <item:extendedcrafting:ultimate_singularity>;
    var mt = <item:mekanism:meka_tool>;
    var anti = <item:mekanism:pellet_antimatter>;
    var mr = <item:astralsorcery:marble_runed>;
    var st = <item:astralsorcery:starmetal>;
    var bx = <item:emcworld:biggest_xuan_ingot>;
    var hs = <item:emcworld:hard_steel>;
    var esi = <item:emcworld:sunlit_ingot>;
    var emcc = <item:emcworld:emc_core>;
    var rai = <item:emcworld:rainbow_ingot>;
    var dee = <item:quark:deepslate>;
    var vaai = <item:allthemodium:vibranium_allthemodium_alloy_ingot>;
    var ali = <item:allthemodium:allthemodium_ingot>;
    var alv = <item:allthemodium:vibranium_ingot>;
    var sb = <item:emcworld:scroll_blue>;
    var aui = <item:allthemodium:unobtainium_ingot>;
    var tui = <item:extendedcrafting:the_ultimate_ingot>;
    var bp = <item:minecraft:blaze_powder>;
    var cho = <tag:items:candyworld:chocolate_bars>;
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
    var dsr = <item:dead_guys_untitled_deep_dark_:sculk_rambler_cut_root>;
    var aeg = <item:emcworld:advanced_emc_gem>;
    var seg = <item:emcworld:super_emc_gem>;
    var eeeg = <item:emcworld:epic_emc_gem>;
    var ki = <item:twilightforest:knightmetal_ingot>;
    var asi = <item:astralsorcery:starmetal_ingot>;
    var atmqd = new CrTSingularity("atm",3).asIIngredient();
    var csi = <item:emcworld:god_steel_ingot>;
    var ai = <item:mythicbotany:alfsteel_ingot>;
    var unqd = new CrTSingularity("unobtainium",3).asIIngredient();
    var zi = <item:good_nights_sleep:zitrite_ingot>;
    var sa = <item:minecraft:spectral_arrow>;
    var iss = <item:mekanism:ingot_steel>;
    var ub = <item:emcworld:universal_ball>;
    var frc = <item:mekanismgenerators:fission_reactor_casing>;
    var aq = <item:astralsorcery:aquamarine>;
    var pa = <item:minecraft:paper>;
    var camt = getColorBlockCount();
    var baseqd as IIngredient = new CrTSingularity("iron",2).addSingularity("steel","nickel","copper","lead","aluminum","silver").asIIngredient(); 
    var amo as int[]=[
        1500,2000,10000,15000,5000,100,10000,6000,10000,6500,5000,5000,10000,10000,10000,8000,8000,8000,15000,6500,7000,800,400,10000,3000,1500
    ];
    var red_armor as IItemStack[]= getRedArmor();
    var ender as IItemStack[] = getGobberEnderArmors();
    var ngb as IItemStack[] = getGobberNetherItems();
    var tor as IItemStack[]=[
        <item:torcherino:torcherino>,
        <item:torcherino:compressed_torcherino>,
        <item:torcherino:double_compressed_torcherino>
    ];
    var tung as IItemStack[] = getTungItems();
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
    var lg as IItemStack[] = getLuckGems();
    var gobber as IItemStack[] = getGobberTools();
    var ne_armor as IItemStack[] = getNetherArmors();
    var armor as IItemStack[] = getModsArmor();
    var pipez as IItemStack[] = getPipezNormalItems();
    var pipez_u as IItemStack[] = getPipezUpgradeItems();
    var pipez_m as IItemStack[] = getPipezUtilsItems();
    var ngb1 as IItemStack[] = getGobberNetherTools();
    var nether as IItemStack[]= getNetherItems();
    var pdm as IItemStack[] = getProjecteBaseArmor();
    var sword as IItemStack[]=[
        <item:mythicbotany:alfsteel_sword>,
        <item:emcworld:atm_sword>,
        <item:emcworld:vibranium_sword>,
        <item:emcworld:unobtainium_sword>
    ];
    var abag as IItemStack[] = getProjecteAlchBags();
    var sn as IItemStack[] = Ice.INSTANCE.getIce();
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
    var sing as IItemStack[][] = getSingularity();
    var gaia_item as IItemStack[]=[
        <item:emcworld:gaia_sword>,
        <item:emcworld:gaia_staff>,
        <item:emcworld:gaia_warhammer>,
        <item:emcworld:gaia_dagger>,
        <item:emcworld:gaia_gun>
    ];
    var staff_item as IItemStack[][] = getBaseTierItems();
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
    smithingRecipe(gaia_item[1],<item:emcworld:rainbow_ingot>,<item:emcworld:rainbow_staff>);
    staffRecipe(<item:emcworld:rainbow_ingot>,<item:emcworld:rainbow_staff>);
    craftingTable.removeByModid("projectex");
    //craftingTable.removeByModid("projecte");
    removeCraftRecipeIItemStack(abag);
    smithingRecipe(staff_item[1][4],<item:cataclysm:ignitium_ingot>,<item:emcworld:netherite_staff>);
    smithingRecipe(staff_item[2][4],<item:cataclysm:ignitium_ingot>,<item:emcworld:netherite_warhammer>);
    blue(<item:projectex:violet_relay>,<item:projectex:blue_relay>);
    addEMCWorldColorfulBlockRecipes();
    removeCraftRecipeIItemStack([mt,<item:quark:pickarang>,<item:gobber2:block_looter>,<item:gobber2:block_protector>,<item:gobber2:block_defender>]);
    crockPotRecipe(new CrTRequirement().addCategoryMin(new CrTFoodValue().put("meat",3.5)).addMustItem(<item:farmersdelight:ham>,1),<item:emcworld:ham_bat>,100);
    violet(<item:projectex:purple_collector>,<item:projectex:violet_collector>);
    blue(<item:projectex:violet_collector>,<item:projectex:blue_collector>);
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
    removeFurnaceRecipe([<item:twilightforest:knightmetal_ingot>]);
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
    EMCWorldDifficultyRecipes();
    /*addCraftShapedRecipeNoName([
        [dee,dee,dee],
        [dee,<item:minecraft:iron_ingot>,dee],
        [dee,dee,dee]
    ],<item:emcworld:steel_furnace_core>);*/
    tartaricForgeRecipe([<item:emcworld:base_key>,<item:twilightforest:lamp_of_cinders>,<item:astralsorcery:rock_collector_crystal>.withTag({astralsorcery: {constellation: "astralsorcery:vicio" as string}}),<item:rats:dutchrat_wheel>],<item:emcworld:nether_key>,4096,1000);
    smithingRecipe(sword[0],atmqd,sword[1]);
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
    addEMCWorldGodStaffRecipe();
    smithingRecipe(sword[2],unqd,sword[3]);
    crockPotRecipe(new CrTRequirement().addCategoryMin(new CrTFoodValue().put("meat",1.5).put("sweetener",1.5)).addMustItem(<item:farmersdelight:smoked_ham>,1),<item:farmersdelight:honey_glazed_ham_block>,30);
    addCraftShapedRecipeNoName([
        [sp,sp,sp],
        [sp,<item:emcworld:weapon_upgrade_core>,sp],
        [sp,sp,sp]
    ],<item:emcworld:top_core>);
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
    if(getGameDifficulty() >= 1){
        removeCraftRecipeIItemStack(tor);
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
    combiningRecipe(<item:scalinghealth:medkit>,<item:emcworld:biggest_emc_gem>,<item:emcworld:emc_healing_bag>);
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
    removeCraftRecipeIItemStack(tung);
    furnaceRecipeNoName(aq*3,<item:emcworld:aquamarine_ore>,1);
    infuserRecipe([ub,aui,aui,ali,ali],<item:allthemodium:unobtainium_allthemodium_alloy_ingot>*6,30000,9000000,3);
    bloodAltarRecipe(<item:mekanism:steel_casing>,<item:emcworld:control_update_core>,15000,3);
    addNuggetAndBlockRecipe(
        <item:emcworld:copper_medal>,
        <item:emcworld:silver_medal>,
        <item:emcworld:gold_medal>
    );
    for i in 0 .. 15{
        var c as IItemStack = new Getter().getCollector()[i];
        var b as IItemStack = abag[i];
        addCraftShapelessRecipe([
            abag[11],c
        ],b);
    }
    removeCraftRecipeIItemStack([abag[13]]);
    addCraftShapelessRecipe([
        <tag:items:forge:dyes/white>,abag[11]
    ],abag[13]);
    removeCraftRecipeIItemStack([abag[11]]);
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
    GodShardRecipe();
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
    extraOresRecipe(<item:allthemodium:allthemodium_ore>,<slurry:allthemodium:dirty_allthemodium>,"atm_extra");
    extraOresRecipe(<item:allthemodium:vibranium_ore>,<slurry:allthemodium:dirty_vibranium>,"vib_extra");
    extraOresRecipe(<item:allthemodium:unobtainium_ore>,<slurry:allthemodium:dirty_unobtainium>,"uno_extra");
    alchemalArrayRecipe(<item:emcworld:base_key>,<item:bloodmagic:life_essence_bucket>,<item:emcworld:twilight_key>);
    addNuggetAndIngotRecipe(<item:emcworld:niobium_nugget>,<item:emcworld:niobium_ingot>);
    addCraftShapedRecipeNoName([
        [sg,sg,sg],
        [sg,<item:emcworld:hardcore_stone>,sg],
        [sg,sg,sg]
    ],<item:emcworld:prefix_core>);
    treeRitualRecipe([
        <item:emcworld:chlorophyte_ingot>,<item:emcworld:hardcore_stone>
    ],a,<item:emcworld:vis_conversion_core>);
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
    addCraftShapedRecipeNoName([
        [oib,oi,oib],
        [oi,ucc,oi],
        [oib,oi,oib]
    ],<item:emcworld:nuclear_ball>.withDamage(10000));
    for i in 0 .. lg.length -1{
        EMCWorldRecipe(lg[i],lg[i+1]);
    }
    for i in 4 .. ngb.length{
        smithingRecipe(tung[i],gin*4,ngb[i]);
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
    combiningRecipe(<item:minecraft:torch>,<item:mekanism:pellet_plutonium> | <item:mekanism:pellet_polonium>,<item:projecte:interdiction_torch>);
    setEMCStage(<item:hem:bronze_ingot>,2048,3);
    addCraftShapedRecipeNoName([
        [bge,ws,bge],
        [ws,<item:emcworld:hardcore_stone>,ws],
        [bge,ws,bge]
    ],<item:emcworld:weapon_upgrade_core>);
    removeEMCWorldModpackSomeRecipes();
    infuserRecipe([sa,sa,sa,sa,sa],<item:emcworld:raid_light>,15000,100000,1);
    infuserRecipe([ali,ali,ali,alv,alv],vaai*2,10000,500000,1);
    infuserRecipe([ali,ali,alv,alv,alv],vaai*3,10000,500000,1);
    setEMCStage(<item:hem:copparite>,3170,5);
    combiningRecipe(<tag:items:quark:candles>,<item:projecte:red_matter>,<item:emcworld:exorcism_candle>);
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
    addEMCWorldIceInfuser();
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
        smithingRecipe(ignis[i],gin,ngb[i]);
    }
    addCraftShapelessRecipe([sg,sg,sg,sg],sb);
    addCraftShapelessRecipe([sw,sw,sw,sw],sg);
    //infuserRecipe([pa,pa,pa,pa,pa],sw,300,5000,1);
    removeCraftRecipeIItemStack(ngb);
    removeCraftRecipeIItemStack(ngb1);
    rotaryRecipe(<fluid:emcworld:sodium_cyanide>,<gas:emcworld:sodium_cyanide>);
    smithingRecipe(<item:gobber2:gobber2_bow>,gin*6,ngb1[0]);
    smithingRecipe(ngb[5],gin*12,ngb1[1]);
    smithingRecipe(ngb[7],gin*9,ngb1[2]);
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