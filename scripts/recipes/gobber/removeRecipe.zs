#priority 50

public function removeGobberRecipe() as void{
    removeCraftRecipe([
        <item:gobber2:gobber2_seed>,
        <item:gobber2:gobber2_seed_nether>,
        <item:gobber2:gobber2_seed_end>,
        <item:gobber2:gobber2_globette>,
        <item:gobber2:gobber2_globette_nether>,
        <item:gobber2:gobber2_globette_end>
    ]);
    removeFurnaceRecipe([
        <item:gobber2:gobber2_glob>,
        <item:gobber2:gobber2_glob_nether>,
        <item:gobber2:gobber2_glob_end>
    ]);
    removeCraftRecipeByName([
        "gobber2:gobber2_ingot",
        "gobber2:gobber2_ingot_nether",
        "gobber2:gobber2_ingot_end"
    ]);
}