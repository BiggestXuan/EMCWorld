#priority 52
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;

public function modifyBloodMagicRecipe() as void{
    var s = <item:emcworld:hardcore_stone>;
    var ei = <item:botania:elementium_ingot>;
    var a = <item:minecraft:air>;
    var ob = <item:minecraft:obsidian>;
    var g = <tag:items:forge:glass>;
    var rb = <item:bloodmagic:reagentbinding>;
    var he = <item:bloodmagic:ingot_hellforged>;
    var ag = <item:atlantis:aquamarine_gem>;
    var mms = <item:bloodmagic:masterritualstone>;
    var ms = <item:bloodmagic:ritualstone>;
    var qr = <tag:items:quark:runes>;
    var ss = <item:bloodmagic:soulsword>;
    var br = <item:bloodmagic:blankrune>;
    var iss = <item:quark:iron_plate_slab>;
    var tt as IIngredient = <item:botania:mana_pearl>|<item:botania:mana_diamond>;
    var ds = <item:bloodmagic:demoncrystallizer>;
    var ac = <item:bloodmagic:alchemicalreactionchamber>;
    var bs = <item:atum:porphyry_brick_smooth>;
    var orb as IIngredient = <item:bloodmagic:weakbloodorb>|<item:bloodmagic:apprenticebloodorb>|<item:bloodmagic:magicianbloodorb>|<item:bloodmagic:masterbloodorb>;
        var lis as IItemStack[]=[
        <item:bloodmagic:livinghelmet>,
        <item:bloodmagic:livingplate>,
        <item:bloodmagic:livingleggings>,
        <item:bloodmagic:livingboots>
    ];
    var ats as IItemStack[]=[
        <item:atum:eyes_of_atem>,
        <item:atum:body_of_atem>,
        <item:atum:legs_of_atem>,
        <item:atum:feet_of_atem>
    ];
    modifyStageRecipe([
        [s,<item:minecraft:air>,s],
        [s,<item:minecraft:furnace>,s],
        [ei,ei,ei]
    ],<item:bloodmagic:altar>,"one");
    modifyShapedRecipe([
        [a,a,ei],
        [a,ei,a],
        [<tag:items:forge:rods>,a,a]
    ],<item:bloodmagic:sacrificialdagger>);
    modifyShapedRecipe([
        [g,g,g],
        [g,<item:emcworld:blood_eye>,g],
        [ob,ob,ob]
    ],<item:minecraft:beacon>);
    bloodAltarRecipe(<item:bloodmagic:demonslate>,<item:bloodmagic:etherealslate>,50000,4);
    bloodAltarRecipe(<item:bloodmagic:weak_tau>,<item:bloodmagic:strong_tau>,100000,4);
    modifyAltarRecipe(<item:minecraft:feather>,<item:bloodmagic:airscribetool>,1500,2);
    modifyAltarRecipe(<item:minecraft:tnt>,<item:bloodmagic:firescribetool>,1500,2);
    modifyAltarRecipe(<item:minecraft:sugar_cane>,<item:bloodmagic:waterscribetool>,1500,2);
    modifyAltarRecipe(<item:minecraft:obsidian>,<item:bloodmagic:earthscribetool>,1500,2);
    bloodAltarRecipe(<item:emcworld:dust_aquamarine>,<item:bloodmagic:sand_hellforged>,5000,3);
    removeAlchemalTableRecipe([rb]);
    alchemalTableRecipe([
        <item:extendedcrafting:luminessence>,
        <item:bloodmagic:lavacrystal>,
        <item:bloodmagic:weakbloodshard>
    ],rb,10000,4);
    removeFurnaceRecipe([he]);
    tartaricForgeRecipe([
        <item:bloodmagic:sand_hellforged>,
        <item:minecraft:iron_ingot>,
        <item:botania:mana_powder>
    ],he,100,1);
    modifyShapedRecipe([
        [ag,qr,ag],
        [qr,<item:atlantis:orb_of_atlantis>,qr],
        [ag,qr,ag]
    ],<item:bloodmagic:soulforge>);
    modifyShapedRecipe([
        [he,a,he],
        [a,<item:aether:altar>,a],
        [br,<item:mythicbotany:helheim_rune>,br]
    ],<item:bloodmagic:demoncrucible>);
    removeTartaricForgeRecipe([ds]);
    modifyShapedRecipe([
        [tt,he,tt],
        [br,<item:byg:hypogeal_imperium>,br],
        [iss,<item:mythicbotany:nidavellir_rune>,iss]
    ],ds);
    modifyShapedRecipe([
        [<item:botania:flask>,orb.reuse(),<item:atum:porphyry_smooth_slab>],
        [<item:atlantis:aquamarine_gem>,<item:mythicbotany:alfheim_rune>,<item:minecraft:book>],
        [bs,bs,bs]
    ],<item:bloodmagic:alchemytable>);
    removeCraftRecipe([ac]);
    alchemalArrayRecipe(rb,<item:atum:godforge>,ac);
    removeTartaricForgeRecipe([ss]);
    tartaricForgeRecipe([
        <item:bloodmagic:rawdemoncrystal>,
        <item:bloodmagic:daggerofsacrifice>,
        <item:mythicbotany:alfsteel_ingot>,
        <item:bloodmagic:mundanepowercatalyst>
    ],ss,1025,100);
    removeAlchemalArrayRecipe(lis);
    for i in 0 .. lis.length{
        alchemalArrayRecipe(rb,ats[i],lis[i]);
    }
}