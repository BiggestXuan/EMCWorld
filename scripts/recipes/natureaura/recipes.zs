#priority 33
import crafttweaker.api.recipe.Replacer;

public function modifyNatureAuraRecipe() as void{
    var ii = <item:naturesaura:infused_iron>;
    var ti = <item:botania:terrasteel_ingot>;
    var tg = <item:naturesaura:tainted_gold>;
    var fi = <item:twilightforest:fiery_ingot>;
    var si = <item:naturesaura:sky_ingot>;
    var gnb = <item:naturesaura:gold_nether_brick>;
    var gp = <item:naturesaura:gold_powder>;
    var ni = <item:emcworld:niobium_ingot>;
    var na = <item:naturesaura:nature_altar>;
    var cs = <item:naturesaura:calling_spirit>;
    var eye = <item:naturesaura:eye>;
    var gb = <item:naturesaura:gold_brick>;
    var gf = <item:naturesaura:gold_fiber>;
    var nas = <item:naturesaura:ancient_sapling>;
    var ei = <item:naturesaura:eye_improved>;
    var gl = <item:naturesaura:gold_leaf>;
    removeNatureAltarRecipe([ii,tg]);
    removeCraftRecipe([
        gf,gp,<item:naturesaura:wood_stand>,gb,gnb,<item:naturesaura:offering_table>,cs
    ]);
    removeOfferingRecipe([si]);
    natureAltarRecipe(ti,ii,1,100000);
    natureAltarRecipe(fi,tg,1,100000);
    offeringRecipe(<item:astralsorcery:starmetal_ingot>,cs,si*2);
    astralInfusionRecipe(<item:hem:blueleaf_leaves>,gf*4);
    crushingRecipe(gl*2,gp,1);
    natureSpawnerRecipe([
        <item:stalwart_dungeons:ancient_fire>,
        <item:stalwart_dungeons:awful_crystal>,
        <item:cataclysm:monstrous_horn>,
        <item:minecraft:nether_star>
    ],750000,<entitytype:cataclysm:ignis>,"first");
    natureSpawnerRecipe([
        <item:cataclysm:ignitium_ingot>,
        <item:botania:rune_wrath>,
        <item:naturesaura:token_rage>,
        <item:naturesaura:birth_spirit>
    ],750000,<entitytype:cataclysm:ignis>,"twice");
    removeTreeRitualRecipe([na,nas,eye,ei]);
    treeRitualRecipe([
        gb,gb,
        <item:mythicbotany:alfheim_rune>,
        <item:astralsorcery:attuned_rock_crystal>.withTag({astralsorcery: {constellationName: "astralsorcery:aevitas" as string}}),
        <item:naturesaura:token_joy>,
        <item:naturesaura:token_fear>,
        <item:naturesaura:token_anger>,
        <item:naturesaura:token_sorrow>
    ],nas,na);
    terraPlateRecipe([
        <item:botania:livingrock_bricks>,gf
    ],gb,50000);
    terraPlateRecipe([
        <item:quark:blackstone_bricks>,gf
    ],gnb,75000);
    treeRitualRecipe([
        <item:botania:monocle>,
        <item:bloodmagic:divinationsigil>,
        <item:botania:third_eye>
    ],<item:minecraft:oak_sapling>,eye);
    treeRitualRecipe([
        eye,<item:bloodmagic:seersigil>,<item:astralsorcery:glass_lens>
    ],<item:good_nights_sleep:dream_sapling>,ei);
    Replacer.forTypes(craftingTable)
    .forMods("naturesaura")
    .suppressWarnings()
    .replace(<item:minecraft:hay_block>,ni)
    .execute();
    alchemalTableRecipe([
        <item:botania:life_essence>*2,
        <item:naturesaura:infused_iron>,
        <item:naturesaura:tainted_gold>,
        <item:bloodmagic:defaultcrystal>
    ],cs,20000,4);
}