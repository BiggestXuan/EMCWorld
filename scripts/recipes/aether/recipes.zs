#priority 20

public function modifyAetherRecipe() as void{
    addCraftShapelessRecipe([
        <item:aether:ambrosium_shard>,
        <item:aether:zanite_gemstone>,
        <item:aether:cold_parachute>,
        <item:aether:enchanted_gravitite>,
        <item:emcworld:stone_shard>,
        <item:emcworld:sunlit_ingot>,
        <item:minecraft:glowstone>
    ],<item:aether:victory_medal>);
    terraPlateRecipe([
        <item:aether:golden_amber>,
        <item:aether:hellfire_stone>,
        <item:aether:holystone>
    ],<item:emcworld:sunlit_ore>,50000);
}