#priority 52

public function modifyAstralRecipe() as void{
    var well = <item:astralsorcery:well>;
    var a = <item:minecraft:air>;
    var test = <item:emcworld:test_block>;
    var wand = <item:astralsorcery:wand>;
    var aq = <item:astralsorcery:aquamarine>;
    var bmr = <item:astralsorcery:black_marble_raw>;
    var iw = <item:astralsorcery:infused_wood>;
    var rc = <item:astralsorcery:rock_crystal>;
    var lt = <item:astralsorcery:linking_tool>;
    var rp = <item:astralsorcery:ritual_pedestal>;
    var mr = <item:astralsorcery:marble_runed>;
    var lm = <item:extendedcrafting:luminessence>;
    var gi = <item:minecraft:gold_ingot>;
    var rs = <item:bloodmagic:ritualstone>;
    var rm = <item:botania:rune_mana>;
    var gsi = <item:emcworld:god_steel_ingot>;
    var lw = <item:botania:livingwood>;
    var tl = <item:astralsorcery:telescope>;
    var gl = <item:astralsorcery:glass_lens>;
    var iwc = <item:astralsorcery:infused_wood_column>;
    var aa = <item:astralsorcery:attunement_altar>;
    var si = <item:astralsorcery:starmetal_ingot>;
    var sd = <item:astralsorcery:stardust>;
    var mb = <tag:items:forge:marble>;
    var ts = <item:astralsorcery:telescope>;
    var lr = <item:botania:livingrock>;

    removeCraftRecipe([well,wand,bmr]);
    //astralTransmutationRecipe(<blockstate:minecraft:diamond_block>,<block:astralsorcery:well>);
    removeAstralAltarRecipe([well,wand,lt,bmr,<item:astralsorcery:infuser>,aa,ts,rp,<item:astralsorcery:infused_wood_column>]);
    enderCraftingRecipe([
        [a,<item:twilightforest:steeleaf_ingot>,],
        [aq,<item:botania:livingwood_twig>,rc],
        [<item:rats:gem_of_ratlantis>,aq,a]
    ],lt);
    extendedCraftingShapedRecipe([
	    [a, a, a, a, a, a, a], 
	    [a, test, <item:astralsorcery:marble_runed>, <item:mekanismgenerators:laser_focus_matrix>, <item:astralsorcery:marble_runed>, test, a], 
	    [a, <item:astralsorcery:marble_pillar>, <item:undergarden:cloggrum_block>, <item:naturesaura:field_creator>, <item:undergarden:cloggrum_block>, <item:astralsorcery:marble_pillar>, a], 
	    [a, test, test, <item:botania:lens_flare>,test, test, a], 
	    [a, <item:bloodmagic:ritualstone>, <item:astralsorcery:ritual_link>, <item:astralsorcery:perk_gem_night>, <item:astralsorcery:ritual_link>, <item:bloodmagic:ritualstone>, a], 
	    [a, <item:bloodmagic:ritualstone>, <item:mythicbotany:vanaheim_rune>, <item:bloodmagic:masterritualstone>, <item:mythicbotany:asgard_rune>, <item:bloodmagic:ritualstone>, a], 
	    [a, a, a, a, a, a, a]
    ],rp,3);
    runeAltarRecipe([
        <item:botania:dreamwood_twig>,aq,<item:gobber2:gobber2_ingot>,<item:rats:marbled_cheese>
    ],wand,10000);
    removeAstralTransmutationRecipe(<blockstate:astralsorcery:starmetal_ore>);
    removeFurnaceRecipe([si]);
    metallurgicInfusingRecipe(sd,<infuse_type:emcworld:gobber>*20,si);
    astralAltarRecipe([
        [a,a,a,a,a],
        [a,iw,sd,iw,a],
        [a,iw,sd,iw,a],
        [a,iw,sd,iw,a],
        [a,a,a,a,a]
    ],<item:astralsorcery:infused_wood_column>,1);
    addCraftShapedRecipeNoName([
        [mb,lr,mb],
        [lr,<item:astralsorcery:nocturnal_powder>,lr],
        [mb,lr,mb]
    ],bmr*8);
    astralAltarRecipe([
        [mr,lm,a,lm,mr],
        [gsi,<item:astralsorcery:colored_lens_fire>,<item:astralsorcery:rock_crystal>|<item:astralsorcery:attuned_rock_crystal>,<item:astralsorcery:colored_lens_break>,gsi],
        [a,<item:astralsorcery:colored_lens_growth>,<item:astralsorcery:infused_glass>,<item:astralsorcery:colored_lens_damage>,a],
        [gi,<item:astralsorcery:colored_lens_regeneration>,<item:astralsorcery:colored_lens_push>,<item:astralsorcery:colored_lens_spectral>,gi],
        [rs,rm,a,rm,rs]
    ],aa,3);
    extendedCraftingShapedRecipe([
        [<item:botania:lens_warp>,lw,a,a,a],
        [lw,gl,iw,a,a],
        [a,iw,<item:astralsorcery:hand_telescope>,gi,a],
        [a,<item:botania:livingwood_twig>,si,gl,a],
        [a,iwc,a,a,a]
    ],ts,2);
}