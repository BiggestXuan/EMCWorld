#priority 62
import mods.mekanism.api.chemical.SlurryStack;
import mods.mekanism.api.ingredient.ItemStackIngredient;
import mods.mekanism.api.ingredient.ChemicalStackIngredient.GasStackIngredient;
import crafttweaker.api.item.ItemStack;

public function extraOresRecipe(ore as ItemStackIngredient,slurry as SlurryStack,name as string) as void{
    <recipetype:mekanism:dissolution>.addRecipe(name+"_cyanide",ore,GasStackIngredient.from(<gas:emcworld:hydrogen_cyanide>),slurry*1600);
    <recipetype:mekanism:dissolution>.addRecipe(name+"_cosmic",ore,GasStackIngredient.from(<gas:emcworld:cosmic_flow>),slurry*3000);
}

public function addIron(ore as ItemStack[]) as void{
    for i in ore{
        <tag:items:forge:ores/iron>.add(i.asIItemStack());
    }
}

public function addGold(ore as ItemStack[]) as void{
    for i in ore{
        <tag:items:forge:ores/gold>.add(i.asIItemStack());
    }
}