#priority 72
import crafttweaker.api.item.ItemStack;

public function modifyProjecteRecipe() as void{
    var table = <item:projecte:transmutation_table>;
    var condenser_mk1 = <item:projecte:condenser_mk1>;
    var red = <item:projecte:red_matter>;
    var collector = new Getter().getCollector();
    var relay = new Getter().getRelay();
    var b = <item:emcworld:big_emc_gem>;
    var d = <item:projecte:dark_matter>;
    var coals = new Getter().getMatter();
    var c = <item:minecraft:coal>;
    var ab = coals[2];
    var ecc = <item:mekanism:elite_control_circuit>;
    var ucc = <item:mekanism:ultimate_control_circuit>;
    var s = new Getter().getSlate();
    var si = <item:emcworld:sunlit_ingot>;
    var g = new Getter().getGem();
    var zi = <item:good_nights_sleep:zitrite_ingot>;
    var ra = <item:good_nights_sleep:rainbow_ingot>;
    var ru = <tag:items:emcworld:advanced_runes>;
    var ei = <item:extendedcrafting:ender_ingot>;
    var a = <item:minecraft:air>;
    var ro = <item:mekanism:ingot_refined_obsidian>;
    var flowers as ItemStack[] = new Getter().getFlowers();

    removeAllRecipe(collector);
    removeAllRecipe(relay);
    removeAllRecipe(flowers);
    removeAllRecipe([table,condenser_mk1,red,<item:projectex:personal_link>]);
    addNuggetAndIngotRecipe(g[0],g[1]);

    addCraftShapedRecipeNoName([
        [a,b,a],
        [b,d,b],
        [a,b,a]
    ],table);

    addCraftShapedRecipeNoName([
        [b,d,b],
        [d,<item:botania:elementium_ingot>,d],
        [b,d,b]
    ],collector[0]);

    addCraftShapedRecipeNoName([
        [b,d,b],
        [d,<item:minecraft:furnace>,d],
        [b,d,b]
    ],relay[0]);

    addCraftShapedRecipeNoName([
        [d,b,d],
        [b,<tag:items:forge:chests>,b],
        [d,b,d]
    ],condenser_mk1);
    modifyShapelessRecipe([
        <item:projecte:red_matter_block>
    ],red*4);
    removeCraftRecipe(coals);
    for i in 0 .. 2{
        addCraftShapelessRecipe([coals[i],coals[i],coals[i],coals[i]],coals[i+1]);
    }
    addCraftShapelessRecipe([c,c,c,c],coals[0]);
    addCraftShapedRecipeNoName([
        [ab,ab,ab],
        [ab,<item:minecraft:diamond>,ab],
        [ab,ab,ab]
    ],d);
    addCraftShapedRecipeNoName([
        [ecc,s[2],ecc],
        [si,collector[0],si],
        [g[1],<item:botania:gaia_ingot>,g[1]]
    ],collector[1]);

    addCraftShapedRecipeNoName([
        [ecc,s[2],ecc],
        [si,relay[0],si],
        [g[1],<item:botania:gaia_ingot>,g[1]]
    ],relay[1]);

    extendedCraftingShapedRecipe([
        [ru,ucc,coals[4],ucc,ru],
        [ro,ra,g[1],ra,ro],
        [ei,g[1],collector[1],g[1],ei],
        [ro,ra,g[1],ra,ro],
        [ru,ucc,coals[4],ucc,ru],
    ],collector[2],2);

    extendedCraftingShapedRecipe([
        [ru,ucc,coals[4],ucc,ru],
        [ro,ra,g[1],ra,ro],
        [ei,g[1],relay[1],g[1],ei],
        [ro,ra,g[1],ra,ro],
        [ru,ucc,coals[4],ucc,ru],
    ],relay[2],2);

    craftingTable.removeByName("projecte:conversions/emerald_to_diamond");

    removeCraftRecipe(new Getter().getPEItem());
}