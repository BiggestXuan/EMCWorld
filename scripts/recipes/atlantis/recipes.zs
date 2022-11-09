#priority 30

public function modifyAtlantisRecipe() as void{
    removeCraftRecipe([
        <item:atlantis:ocean_stone>,
        <item:atlantis:atlantean_crystal>,
        <item:atlantis:drop_of_atlantis>,
        <item:atlantis:ocean_lantern>,
        <item:atlantis:atlantean_core>,
        <item:atlantis:orb_of_atlantis>,
        <item:atlantis:chiseled_golden_block>
    ]);

    manaInfusionRecipe(<item:minecraft:prismarine_shard>,<item:atlantis:ocean_stone>,15000);

    runeAltarRecipe([
        <item:atlantis:ocean_stone>,
        <item:botania:rune_water>,
        <item:botania:life_essence>,
        <item:minecraft:prismarine_crystals>
        ],<item:atlantis:atlantean_crystal>,20000
    );
    combiningRecipe(<item:atlantis:atlantean_crystal>*3,<item:atlantis:atlantean_core>,<item:atlantis:drop_of_atlantis>);
    compressingRecipe(<item:minecraft:gold_block>,<item:atlantis:chiseled_golden_block>);
    terraPlateRecipe([
        <item:atlantis:drop_of_atlantis>,
        <item:atlantis:aquamarine_gem>,
        <item:mekanism:teleportation_core>,
        <item:emcworld:cold_ingot>
    ],<item:atlantis:orb_of_atlantis>,200000);
    runeAltarRecipe([
        <item:atlantis:orb_of_atlantis>,
        <item:botania:rune_winter>,
        <item:atlantis:atlantean_crystal>,
        <item:botania:life_essence>
    ],<item:atlantis:orb_of_atlantis>*2,100000);
    var list as string[]=[];
    <recipetype:botania:pure_daisy>.addRecipe("sea_lantern",<blockstate:atlantis:atlantean_core>,<block:atlantis:ocean_lantern>,70 * getAdditionCost());
}