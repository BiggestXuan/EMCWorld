#priority 60

import crafttweaker.api.item.IItemStack;

public function addEMCWorldBaseConstBlockRecipes() as void{
    var con as IItemStack[][] = getConstBlocks();
    var a = <item:minecraft:air>;
    var camt = getColorBlockCount();
    var ip = <item:astralsorcery:illumination_powder>;
    var dm = <item:projecte:dark_matter>;
    var sc = <item:mekanism:steel_casing>;

    addCraftShapedRecipeNoName([
        [dm,sc,dm],
        [sc,dm,sc],
        [dm,sc,dm]
    ],con[0][0]*camt);
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
}