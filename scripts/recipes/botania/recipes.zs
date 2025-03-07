#priority 51
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.IItemStack;

public function modifyBotaniaRecipe() as void{
    var l = <item:botania:livingwood>;
    var a = <item:minecraft:air>;
    var dai = <item:mythicbotany:alfsteel_armor_upgrade>;
    var ms = <item:botania:manasteel_ingot>;
    var mp = <item:botania:mana_powder>;
    var t = <item:botania:terra_plate>;
    var gb = <item:atlantis:chiseled_golden_block>;
    var eb = <item:botania:elementium_block>;
    var mg = <item:botania:mana_glass>;
    var ss = <item:bloodmagic:soulsword>;
    var gll = <item:botania:glimmering_livingwood>;
    var mi = <item:botania:manasteel_ingot>;
    var lt = <item:botania:livingwood_twig>;
    var ai = <item:mythicbotany:alfsteel_ingot>;
    var mh = <item:mythicbotany:master_rune_holder>;
    var ts = <item:botania:terra_sword>;
    var ti = <item:botania:terrasteel_ingot>;
    var km = <item:mythicbotany:kvasir_mead>;
    var ar = <item:mythicbotany:asgard_rune>;
    var r = new Getter().getRunes();
    var quark_runes as IItemStack[]=[
        <item:quark:cyan_rune>,
        <item:quark:red_rune>,
        <item:quark:green_rune>,
        <item:quark:yellow_rune>
    ];
    var teb as ItemStack[]=[
        <item:botania:terra_pick>,
        <item:botania:terra_axe>,
        <item:botania:mana_ring_greater>,
        <item:botania:aura_ring_greater>
    ];
    var mab as IItemStack[]=[
        <item:botania:manasteel_pick>,
        <item:botania:manasteel_axe>,
        <item:botania:mana_ring>,
        <item:botania:aura_ring>
    ];
    var tes as IItemStack[]=[
        <item:botania:terrasteel_helmet>,
        <item:botania:terrasteel_chestplate>,
        <item:botania:terrasteel_leggings>,
        <item:botania:terrasteel_boots>
    ];
    var mas as IItemStack[]=[
        <item:botania:manasteel_helmet>,
        <item:botania:manasteel_chestplate>,
        <item:botania:manasteel_leggings>,
        <item:botania:manasteel_boots>
    ];
    modifyShapedRecipe([
        [l,l,l],
        [l,ti,l],
        [l,l,l]
    ],<item:botania:alfheim_portal>);
    <recipetype:botania:pure_daisy>.removeByName("botania:pure_daisy/livingrock");
    removeCraftRecipe([<item:botania:lens_weight>,<item:botania:black_hole_talisman>,<item:botania:gaia_pylon>,<item:mythicbotany:yggdrasil_branch>,<item:minecraft:sea_lantern>,<item:mythicbotany:mana_infuser>,mh,ts,dai,km]);
    removeCraftRecipe(teb);
    modifyShapelessRecipe([
        l,<item:emcworld:small_emc_gem>
    ],gll);
    modifyRuneAltarRecipe([ms,mp,<tag:items:forge:stone>,<item:minecraft:gunpowder>,<tag:items:forge:sand>],<item:botania:rune_fire>*2,3000);
    for i in 0 .. quark_runes.length{
        runeAltarRecipe([quark_runes[i]],new Getter().getRune()[i].asIItemStack()*6,15000);
    }
    addNuggetAndIngotRecipe(<item:emcworld:gaia_nugget>,<item:botania:gaia_ingot>);
    modifyApothecaryRecipe([
        <tag:items:botania:petals/blue>,<tag:items:botania:petals/blue>,<tag:items:botania:petals/cyan>,<tag:items:botania:petals/cyan>,
        <item:botania:mana_powder>
    ],<item:botania:hydroangeas>);
    removeApothecaryRecipe(<item:botania:entropinnyum>);
    enderCraftingRecipe([
        [eb,t,eb],
        [eb,<item:botania:terrasteel_block>,eb],
        [eb,<item:botania:dragonstone_block>,eb]
    ],<item:mythicbotany:mana_infuser>);
    modifyShapelessRecipe([
        <item:botania:manasteel_ingot>,
        <item:botania:mana_pearl>,
        <item:botania:rune_water>
    ],<item:botania:spark_upgrade_dispersive>);
    removeManaInfusionRecipe([<item:mythicbotany:gjallar_horn_empty>,<item:botania:manasteel_block>]);
    manaInfusionRecipe(<item:mekanism:block_steel>,<item:botania:manasteel_block>,5000);
    terraPlateRecipe([r[2],r[3],r[4],r[5],r[6],r[7],r[8],<item:botania:horn_grass>],<item:mythicbotany:gjallar_horn_empty>,1000000);
    modifyShapedRecipe([
        [a,mg,a],
        [a,mi,a],
        [lt,<item:botania:livingwood_slab>,lt]
    ],<item:mythicbotany:rune_holder>);
    modifyApothecaryRecipe([<tag:items:botania:petals/brown>, <tag:items:botania:petals/brown>, <tag:items:botania:petals/red>, <tag:items:botania:petals/light_gray>,<item:botania:mana_powder>],<item:botania:endoflame>);
    reactionChamberRecipe(
        ss,
        ts,
        <fluid:bloodmagic:life_essence_fluid> * 20000,
        <fluid:minecraft:water> * 10000,
        <item:mythicbotany:nidavellir_rune>,
        [ti%50]
    );
    for i in 0 .. tes.length{
        modifyShapedRecipe([
            [lt,new Getter().getRune()[i+4].asIItemStack(),lt],
            [ti,mas[i],ti],
            [a,ai,a]
        ],tes[i]);
    }
    for i in 0 .. teb.length{
        runeAltarRecipe([
            mab[i],
            ai,
            ti,
            <item:good_nights_sleep:rainbow_ingot>,
            <item:good_nights_sleep:zitrite_ingot>
        ],teb[i].asIItemStack(),30000);
    }
    
    combiningRecipe(ai*2,new Getter().getMatter()[3],dai);
    removeRuneAltarRecipe(ar);
    runeAltarRecipe([
        <item:botania:dandelifeon>,
        <item:botania:alfheim_portal>,<item:emcworld:god_steel_ingot>
    ],<item:mythicbotany:yggdrasil_branch>,10000);
    reactionChamberRecipe(
        <item:mythicbotany:kvasir_blood>,
        km,
        <fluid:cofh_core:honey>*1000,
        <fluid:minecraft:empty>,
        <item:bloodmagic:reinforcedteleposerfocus>,
        [<item:minecraft:honeycomb>%50]
    );
    runeAltarRecipe([
        <item:minecraft:netherite_ingot>,
        <tag:items:botania:runes/air>,
        <tag:items:botania:runes/autumn>, 
        <tag:items:botania:runes/pride>,
        <item:botania:rainbow_rod>,
        <item:gobber2:gobber2_ingot_nether>
    ],ar,16000);
    setEMCStage(<item:botania:mana_powder>,66,2);
    for i in [
        <item:mythicbotany:alfsteel_armor_upgrade>,
    ] as IItemStack[]{
        addEMCStage(i,10);
    }
}