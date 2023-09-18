#priority 62

public function removeEMCWorldModpackSomeRecipes() as void{
    removeCraftRecipeIItemStack([
        <item:bountifulbaubles:spectral_silt>,
        <item:allthemodium:alloy_sword>,
        <item:allthemodium:alloy_pick>,
        <item:allthemodium:alloy_axe>,
        <item:allthemodium:alloy_shovel>,
        <item:allthemodium:alloy_paxel>
    ]);
    removeCraftRecipe([
        <item:waystones:return_scroll>,
        <item:waystones:bound_scroll>,
        <item:waystones:warp_scroll>,
        <item:waystones:attuned_shard>,
        <item:waystones:warp_plate>,
        <item:waystones:warp_dust>
    ]);
}