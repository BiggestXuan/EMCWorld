#priority 20

public function modifyAetherRecipe() as void{
    var test = <item:emcworld:test_block>;
    /*addCraftShapelessRecipe([
        <item:aether:ambrosium_shard>,
        <item:aether:zanite_gemstone>,
        <item:aether:cold_parachute>,
        <item:aether:enchanted_gravitite>,
        <item:emcworld:stone_shard>,
        <item:emcworld:sunlit_ingot>,
        <item:minecraft:glowstone>
    ],<item:aether:victory_medal>);*/
    terraPlateRecipe([
        test,test,test
    ],<item:emcworld:sunlit_ore>,50000);
}