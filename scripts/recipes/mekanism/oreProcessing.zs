#priority 42
import mods.mekanism.api.ingredient.ItemStackIngredient;

public function extraOresProcessing() as void{
    extraOresRecipe(ItemStackIngredient.from(<tag:items:forge:ores/iron>),<slurry:mekanism:dirty_iron>,"iron_ore_processing");
    extraOresRecipe(ItemStackIngredient.from(<tag:items:forge:ores/gold>),<slurry:mekanism:dirty_gold>,"gold_ore_processing");
    extraOresRecipe(ItemStackIngredient.from(<tag:items:forge:ores/copper>),<slurry:mekanism:dirty_copper>,"copper_ore_processing");
    extraOresRecipe(ItemStackIngredient.from(<tag:items:forge:ores/tin>),<slurry:mekanism:dirty_tin>,"tin_ore_processing");
    extraOresRecipe(ItemStackIngredient.from(<tag:items:forge:ores/osmium>),<slurry:mekanism:dirty_osmium>,"osmium_ore_processing");
    extraOresRecipe(ItemStackIngredient.from(<tag:items:forge:ores/uranium>),<slurry:mekanism:dirty_uranium>,"uranium_ore_processing");
    extraOresRecipe(ItemStackIngredient.from(<tag:items:forge:ores/lead>),<slurry:mekanism:dirty_lead>,"lead_ore_processing");
    addIron([
        <item:dead_guys_untitled_deep_dark_:deep_stone_iron>,
        <item:theabyss:iron_variant>
    ]);
    addGold([
        <item:byg:blue_nether_gold_ore>,
        <item:byg:brimstone_nether_gold_ore>,
        <item:dead_guys_untitled_deep_dark_:deep_stone_gold>,
        <item:theabyss:gold_variant>
    ]);
}