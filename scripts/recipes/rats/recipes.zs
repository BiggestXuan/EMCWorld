#priority 53

public function modifyRatsRecipe() as void{
    var cct = <item:rats:chunky_cheese_token>;
    var tp = <item:rats:token_piece>;
    var ft = <item:rats:filth>;
    var fc = <item:rats:filth_corruption>;
    var ch = <item:rats:cheese>;
    var a = <item:minecraft:air>;
    var gr = <item:rats:gem_of_ratlantis>;
    var mcb = <item:rats:marbled_cheese_brick>;
    var mcp = <item:rats:marbled_cheese_pillar>;
    var vs = <item:rats:vial_of_sentience>;
    var db = <item:rats:dutchrat_bell>;
    var gpe = <item:rats:ghost_pirat_ectoplasm>;
    var mcbc = <item:rats:marbled_cheese_brick_chiseled>;
    var mct = <item:rats:marbled_cheese_tile>;
    var ah = <item:rats:archeologist_hat>;
    var ip = <item:astralsorcery:illumination_powder>;
    var pl = <item:rats:pirat_log>;
    var base = <item:rats:rat_upgrade_basic>;
    var at = <item:rats:arcane_technology>;
    var rf = <item:rats:ratlantean_flame>;
    var prb = <item:rats:psionic_rat_brain>;
    var ucc = <item:mekanism:ultimate_control_circuit>;
    var mcgc = <item:rats:marbled_cheese_golem_core>;
    var rua = <item:rats:rat_upgrade_archeologist>;
    var b = <item:minecraft:bell>;
    removeCraftRecipe([cct,ft,fc,mcgc,db,vs,ah,rua]);
    removeAllRecipe([gr]);
    enderCraftingRecipe([
        [tp,<item:emcworld:base_key>,tp],
        [tp,<item:rats:cheese>,tp],
        [tp,<item:rats:tangled_rat_tails>,tp],
    ],cct);
    alchemalTableRecipe([
        <item:rats:compressed_garbage>,<item:rats:garbage_pile>,ch
    ],ft,1000,1);
    terraPlateRecipe([
        <item:atum:ectoplasm>,<item:rats:ratlantean_flame>
    ],<item:rats:ghost_pirat_ectoplasm>*3,500000);
    astralAltarRecipe([
        [mcb,mcp,a,mcp,mcb],
        [mcp,mcbc,mct,mcbc,mcp],
        [a,mcp,mct,mcp,a],
        [a,a,mcp,a,a],
        [a,ip,<item:rats:ratlantean_flame>,ip,a]
    ],<item:rats:marbled_cheese_rat_head>,114514);
    combiningRecipe(gr,<item:mekanism:ultimate_control_circuit>,mcgc);
    tartaricForgeRecipe([
        rf,gpe,<item:botania:terrasteel_nugget>,<item:atlantis:aquamarine_gem>
    ],gr,1025,1);
    terraPlateRecipe([
        b,gpe,pl,gr,rf
    ],db,500000);
    alchemalTableRecipe([
        <item:bloodmagic:slate_vial>,
        <item:bloodmagic:fortune_anointment>,
        <item:bloodmagic:looting_anointment>,
        <item:bloodmagic:life_essence_bucket>,
        rf,gr
    ],vs,5000,2);
    alchemalArrayRecipe(<item:minecraft:leather_helmet>,<item:bloodmagic:reagentsight>,ah);
    enderCraftingRecipe([
        [rf,ah,<item:botania:monocle>],
        [at,base,<item:rats:dutchrat_wheel>],
        [cct,prb,ucc]
    ],rua);
}