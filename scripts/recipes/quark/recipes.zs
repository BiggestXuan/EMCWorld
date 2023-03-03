#priority 52
import crafttweaker.api.item.ItemStack;

public function modifyQuarkRecipe() as void{
    var rune as ItemStack[]=[
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
        <item:quark:black_rune>
    ];
    var blank = <item:quark:blank_rune>;
    var s = <tag:items:forge:stone>;
    var hs = <item:emcworld:hardcore_stone>;
    var dyes = new Getter().getDye();
    removeCraftRecipe(rune);
    addCraftShapedRecipeNoName([
        [s,s,s],
        [s,hs,s],
        [s,s,s]
    ],blank);
    addCraftShapedRecipeNoName([
        [hs,hs,hs],
        [hs,s,hs],
        [hs,hs,hs]
    ],blank*12);
    for i in 0 .. rune.length{
        var c = dyes[i];
        addCraftShapedRecipeNoName([
            [c,c,c],
            [c,blank,c],
            [c,c,c]
        ],rune[i]);
    }
    runeAltarRecipe([
        <tag:items:forge:stone>,<item:emcworld:cold_ingot>
    ],<item:quark:permafrost>,5000);
}

public function fixQuarkRuneRecipe() as void{
    var cluster as ItemStack[]=[
        <item:quark:red_crystal_cluster>,
        <item:quark:orange_crystal_cluster>,
        <item:quark:yellow_crystal_cluster>,
        <item:quark:green_crystal_cluster>,
        <item:quark:blue_crystal_cluster>,
        <item:quark:indigo_crystal_cluster>,
        <item:quark:violet_crystal_cluster>,
        <item:quark:white_crystal_cluster>,
        <item:quark:black_crystal_cluster>
    ];
    var rune as ItemStack[]=[
        <item:quark:red_rune>,
        <item:quark:orange_rune>,
        <item:quark:yellow_rune>,
        <item:quark:green_rune>,
        <item:quark:blue_rune>,
        <item:quark:cyan_rune>,
        <item:quark:pink_rune>,
        <item:quark:white_rune>,
        <item:quark:black_rune>
    ];
    removeCraftRecipe(rune);
    var blank = <item:quark:blank_rune>;
    for i in 0 .. cluster.length{
        var r = cluster[i];
        addCraftShapedRecipeNoName([
            [r,r,r],
            [r,blank,r],
            [r,r,r]
        ],rune[i]);
    }
}