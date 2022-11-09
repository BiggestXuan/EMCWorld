#priority 47

public function modifyManaSteelRecipe() as void{
    removeManaInfusionRecipe([<item:botania:manasteel_ingot>]);
    manaInfusionRecipe(<item:mekanism:ingot_steel>,<item:botania:manasteel_ingot>,1500 * getAdditionCost());
    manaInfusionRecipe(<item:gobber2:gobber2_glob>,<item:gobber2:gobber2_ingot>,1000 * getAdditionCost());
}