#priority 48
import crafttweaker.api.recipe.Replacer;

public function modifyGobberRecipe() as void{
    Replacer.forTypes(<recipetype:minecraft:crafting>)
    .forMods("gobber2")
    .replace(<item:gobber2:gobber2_rod>,<item:emcworld:netherite_stick>)
    .execute();

    Replacer.forTypes(<recipetype:minecraft:crafting>)
    .forMods("gobber2")
    .replace(<item:minecraft:emerald>,<item:emcworld:niobium_ingot>)
    .execute();

    natureAltarRecipe(<item:gobber2:gobber2_globette_nether>,<item:gobber2:gobber2_ingot_nether>,2,200000);
}