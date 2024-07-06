#priority 28
import crafttweaker.api.item.IIngredient;

public function modifyAtumRecipe() as void{
    var a = <item:minecraft:air>;
    var g = <item:emcworld:gaia_nugget>;
    var n = <item:atum:nebu_ore>;
    var gb = <item:atum:godforged_block>;
    var d = <item:atum:nebu_drop>;
    var test = <item:emcworld:test_block>;
    var godBlock as IIngredient[]=[
        <item:atum:anput_godforged_block>,
        <item:atum:anubis_godforged_block>,
        <item:atum:atem_godforged_block>,
        <item:atum:geb_godforged_block>,
        <item:atum:horus_godforged_block>,
        <item:atum:isis_godforged_block>,
        <item:atum:montu_godforged_block>,
        <item:atum:nepthys_godforged_block>,
        <item:atum:nuit_godforged_block>,
        <item:atum:osiris_godforged_block>,
        <item:atum:ptah_godforged_block>,
        <item:atum:ra_godforged_block>,
        <item:atum:seth_godforged_block>,
        <item:atum:shu_godforged_block>,
        <item:atum:tefnut_godforged_block>
    ];
    removeCraftRecipe([gb]);
    modifyShapedRecipe([
        [a,g,a],
        [g,<item:minecraft:diamond>,g],
        [a,g,a]
    ],<item:atum:scarab>);
    enrichingRecipe(n,d,5);
    purifyingRecipe(n,d,7);
    injectingRecipe(n,d,10);
    furnaceRecipeNoName(<item:atum:crunchy_golden_scarab>,<item:atum:scarab>,0);
    craftTogether(new Getter().getShard());
    for item in new Getter().getShard(){
        addCraftShapelessRecipe([<item:emcworld:copper_medal>,item],item.asIItemStack()*2);
        addCraftShapelessRecipe([<item:emcworld:silver_medal>,item],item.asIItemStack()*12);
    }
    for i in godBlock{
        for j in i.items{
            <tag:items:emcworld:godblock>.add(j);
        }
    }
    addCraftShapelessRecipe([
        <item:atum:godforged_block>
    ],<item:atum:anput_godshard>*32);
    extendedCraftingShapelessRecipe(godBlock,gb,2);
}