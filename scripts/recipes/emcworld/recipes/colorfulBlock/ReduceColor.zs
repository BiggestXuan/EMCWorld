#priority 60

import crafttweaker.api.item.IItemStack;

public function addEMCWorldReduceConstBlockRecipes() as void{
    var con as IItemStack[][] = getConstBlocks();
    var a = <item:minecraft:air>;
    var pon = <item:minecraft:potion>.withTag({Potion: "minecraft:swiftness" as string});
    var cha = <item:minecraft:charcoal>;
    var mr = <item:astralsorcery:marble_runed>;
    var sing as IItemStack[][] = getSingularity();
    var anti = <item:mekanism:pellet_antimatter>;

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
    infuserRecipe([
        sing[0][20],sing[0][22],<item:botania:brew_flask>.withTag({brewKey: "botania:overload" as string}),con[0][7],con[0][7]
    ],con[3][7]*2,4000,1500000,3);
    infuserRecipe([
        <item:undergarden:forgotten_ingot>,
        <item:aerialhell:arsonist_ingot>,
        <item:astralsorcery:ritual_link>,
        <item:aerialhell:arsonist_ingot>,con[0][6]
    ],con[3][6],4000,300000,3);
}