
#priority 52
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;

import mods.emcworld.CrTSingularity;
import mods.emcworld.CrTWeightItem;
import mods.emcworld.ItemUtils;

public function D3Recipes() as void{
    var cmi = <item:emcworld:crystal_matrix_ingot>;
    var ifc = <item:emcworld:infinity_catalyst>;
    var epss as IIngredient = ItemUtils.getEMCGodItemWithMaxLevel();
    var eus = <item:extendedcrafting:ultimate_singularity>;
    var ewi = <item:emcworld:wooden_ingot>;
    var essi = <item:emcworld:stone_ingot>;
    var evm = <item:emcworld:violet_matter>;
    var eri = <item:emcworld:rune_ingot>;
    var eni = <item:emcworld:nature_ingot>;
    var dim = <item:minecraft:diamond>;
    var eis = <item:emcworld:illager_shard>;
    var red_armor as IItemStack[]= getRedArmor();
    var anti = <item:mekanism:pellet_antimatter>;
    var lg as IItemStack[] = getLuckGems();
    var a = <item:minecraft:air>;
    var tui = <item:extendedcrafting:the_ultimate_ingot>;
    removeExtendedCraftRecipe(eus);
    extendedCraftingShapelessRecipe(CrTSingularity.getAllSingularityAsIIngredientArray(),eus,4);
    for i in 0 .. 4{
        combiningRecipe(red_armor[i+4].withArmorLevelMax(),<item:emcworld:dragon_steel>,red_armor[i]);
    }
    tartaricForgeRecipe([
        <item:bloodmagic:dungeon_metal>,
        <item:minecraft:enchanted_golden_apple>,
        <item:bloodmagic:weakbloodshard>,
        <item:bloodmagic:reagentbinding>
    ],<item:emcworld:exception_apple>,1024,512);
    addCraftShapedRecipeNoName([
        [eis,eis,eis],
        [eis,<tag:items:forge:feathers>,eis],
        [eis,eis,eis]
    ],<item:emcworld:flying_gem>);
    craftTogether([<item:emcworld:infinity_sword>,<item:emcworld:infinity_module>]);
    addCraftShapedRecipeNoName([
        [tui,anti,tui],
        [anti,eis,anti],
        [tui,anti,tui]
    ],<item:emcworld:energy_protect_module>);
    addCraftShapedRecipeNoName([
        [lg[3],lg[3],lg[3]],
        [lg[3],<item:emcworld:illager_shard>,lg[3]],
        [lg[3],lg[3],lg[3]]
    ],<item:emcworld:lucky_gem_emerald>*8);
    combiningRecipe(<item:projecte:transmutation_table>,<item:emcworld:magenta_matter>,<item:projecte:transmutation_tablet>);
    piglinTrade(<item:emcworld:advanced_emc_gem>,[
        new CrTWeightItem(red_armor[4],1,1,3),
        new CrTWeightItem(red_armor[5],1,1,3),
        new CrTWeightItem(red_armor[6],1,1,3),
        new CrTWeightItem(red_armor[7],1,1,3),
        new CrTWeightItem(<item:mekanism:pellet_polonium>,1,3,1),
        new CrTWeightItem(<item:mekanism:pellet_plutonium>,1,3,1),
        new CrTWeightItem(<item:emcworld:biggest_emc_gem>,1,16,18)
    ] as CrTWeightItem[]);
    addCraftShapelessRecipe([<item:emcworld:biggest_emc_gem>],<item:emcworld:big_emc_gem>*32);
    addCraftShapedRecipeNoName([
        [tui,tui,tui],
        [tui,eus,tui],
        [tui,tui,tui]
    ],ifc);
    addCraftShapedRecipeNoName([
        [eis,eis,eis],
        [eis,eis,eis],
        [eis,eis,eis]
    ],tui);
    for i in [tui,anti]{
        addCraftShapelessRecipe([
            eis,i
        ],i*8);
    }
    var kl as IItemStack[] = [ewi,essi,eri,eni];
    for i in kl{
        addCraftShapelessRecipe([
            eis,i
        ],i*64);
    }
    addCraftShapedRecipeNoName([
        [dim,dim,dim],
        [dim,<item:emcworld:infinity_emc_gem>,dim],
        [dim,dim,dim]
    ],<item:emcworld:crystal_matrix_ingot>);
    extendedCraftingShapedRecipe([
        [a,a,a,a,a,a,cmi,cmi,cmi],
        [a,a,a,a,a,cmi,ifc,ifc,cmi],
        [cmi,a,a,a,cmi,ifc,ifc,ifc,cmi],
        [a,cmi,a,cmi,ifc,ifc,ifc,cmi,a],
        [a,a,cmi,ifc,ifc,ifc,cmi,a,a],
        [a,a,cmi,epss,cmi,cmi,a,a,a],
        [a,cmi,ifc,cmi,cmi,a,a,a,a],
        [cmi,ifc,cmi,a,a,cmi,a,a,a],
        [cmi,cmi,a,a,a,a,cmi,a,a]
    ],<item:emcworld:infinity_sword>,4);
    addCraftShapelessRecipe([<item:extendedcrafting:ultimate_singularity>,<item:emcworld:illager_shard>],<item:extendedcrafting:ultimate_singularity>*2);

}