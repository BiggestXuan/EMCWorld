#priority 61

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.recipe.Replacer;

import mods.emcworld.ItemUtils;

public function D2Recipes() as void{
    var pdm as IItemStack[] = getProjecteBaseArmor();
    var eus = <item:extendedcrafting:ultimate_singularity>;
    var tui = <item:extendedcrafting:the_ultimate_ingot>;
    var eeg = <item:emcworld:epic_emc_gem>;
    var ieg = <item:emcworld:infinity_emc_gem>;
    var pcm = <item:emcworld:clay_matter>;
    var ub = <item:emcworld:universal_ball>;
    var eri = <item:emcworld:rune_ingot>;
    var adss as IIngredient = ItemUtils.getEMCGodItemWithLevel(20);
    var emcc = <item:emcworld:emc_core>;
    var dm = <item:projecte:dark_matter>;
    var essi = <item:emcworld:stone_ingot>;
    var eni = <item:emcworld:nature_ingot>;
    var pfm = <item:emcworld:fading_matter>;
    var ewi = <item:emcworld:wooden_ingot>;

    orange();
    copyFinalIngot(eni);
    for i in 24 .. 28{
        extendedCraftingShapedRecipe([
            [eus,tui,tui,eeg,eeg,eeg,tui,tui,eus],
            [tui,pcm,eeg,eeg,eeg,eeg,eeg,pcm,tui],
            [tui,eeg,pcm,eeg,eeg,eeg,pcm,eeg,tui],
            [eeg,eeg,eeg,pcm,ieg,pcm,eeg,eeg,eeg],
            [eeg,eeg,eeg,ieg,pdm[i-13],ieg,eeg,eeg,eeg],
            [eeg,eeg,eeg,pcm,ieg,pcm,eeg,eeg,eeg],
            [tui,eeg,pcm,eeg,eeg,eeg,pcm,eeg,tui],
            [tui,pcm,eeg,eeg,eeg,eeg,eeg,pcm,tui],
            [eus,tui,tui,eeg,eeg,eeg,tui,tui,eus]
        ],pdm[i],4);
    }
    white();
    copyFinalIngot(ewi);
    extendedCompressionRecipe(ub,emcc,350,1);
    for i in pdm{
        Replacer.forTypes(craftingTable)
        .forMods("projecte")
        .forOutput(i,craftingTable)
        .replace(dm,pfm)
        .replace(<item:projecte:red_matter>,pcm)
        .execute();
    }
    FinalExtendedRecipes();
    copyFinalIngot(eri);
    removeCraftRecipeIItemStack([pdm[22]]);
    extendedCraftingShapedRecipe([
        [tui,tui,tui,eeg,eeg,eeg,tui,tui,tui],
        [tui,tui,eeg,eeg,eus,eeg,eeg,tui,tui],
        [tui,eeg,ieg,pcm,pcm,pcm,ieg,eeg,tui],
        [eeg,eeg,pcm,pcm,pdm[18],pcm,pcm,eeg,eeg],
        [eeg,eus,pcm,pdm[15],adss,pdm[16],pcm,eus,eeg],
        [eeg,eeg,pcm,pcm,pdm[23],pcm,pcm,eeg,eeg],
        [tui,eeg,ieg,pcm,pcm,pcm,ieg,eeg,tui],
        [tui,tui,eeg,eeg,eus,eeg,eeg,tui,tui],
        [tui,tui,tui,eeg,eeg,eeg,tui,tui,tui]
    ],pdm[22],4);
    copyFinalIngot(essi);
}