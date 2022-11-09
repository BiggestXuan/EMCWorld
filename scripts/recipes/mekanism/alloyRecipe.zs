#priority 28
import crafttweaker.api.item.IItemStack;

public function addInfusionConversionRecipe() as void{
    iron(<item:minecraft:iron_nugget>,1);
    iron(<item:minecraft:iron_ingot>,10);
    iron(<item:emcworld:enriched_iron>,80);
    iron(<item:minecraft:iron_block>,90);

    nickel(<item:emcworld:nickel_ingot>,10);
    nickel(<item:emcworld:enriched_nickel>,80);
    nickel(<item:thermal:nickel_block>,90);

    silver(<item:emcworld:silver_ingot>,10);
    silver(<item:emcworld:enriched_silver>,80);
    silver(<item:thermal:silver_block>,90);

    ender(<item:minecraft:ender_pearl>,10);
    ender(<item:emcworld:enriched_ender>,80);

    gobber(<item:gobber2:gobber2_globette>,10);
    gobber(<item:gobber2:gobber2_glob>,90);
    gobber(<item:gobber2:gobber2_ingot>,90);
    gobber(<item:gobber2:gobber2_block>,810);
    gobber(<item:emcworld:enriched_gobber>,80);

    hellforged(<item:bloodmagic:ingot_hellforged>,10);
    hellforged(<item:bloodmagic:dungeon_metal>,90);
}

public function addEnrichedRecipe() as void{
    enrichingRecipe(<item:minecraft:iron_ingot>,<item:emcworld:enriched_iron>,1);
    enrichingRecipe(<item:emcworld:nickel_ingot>,<item:emcworld:enriched_nickel>,1);
    enrichingRecipe(<item:emcworld:silver_ingot>,<item:emcworld:enriched_silver>,1);
    enrichingRecipe(<item:minecraft:ender_pearl>,<item:emcworld:enriched_ender>,1);
    enrichingRecipe(<item:gobber2:gobber2_globette>,<item:emcworld:enriched_gobber>,1);
    enrichingRecipe(<item:gobber2:gobber2_ingot>,<item:emcworld:enriched_gobber>,9);
}

public function addAlloyRecipe() as void{
    addInfusionConversionRecipe();
    addEnrichedRecipe();
    metallurgicInfusingRecipe(<item:mekanism:ingot_copper>,<infuse_type:emcworld:nickel>*10,<item:thermal:constantan_ingot>*2);
    metallurgicInfusingRecipe(<item:emcworld:silver_ingot>,<infuse_type:mekanism:gold>*10,<item:thermal:electrum_ingot>*2);
    metallurgicInfusingRecipe(<item:mekanism:ingot_steel>*2,<infuse_type:emcworld:nickel>*10,<item:thermal:invar_ingot>*3);
    metallurgicInfusingRecipe(<item:emcworld:aluminum_ingot>*3,<infuse_type:emcworld:silver>*20,<item:thermal:signalum_ingot>*6);
    metallurgicInfusingRecipe(<item:mekanism:ingot_refined_glowstone>*3,<infuse_type:emcworld:silver>*20,<item:thermal:lumium_ingot>*4);
    metallurgicInfusingRecipe(<item:mekanism:ingot_lead>*3,<infuse_type:emcworld:ender>*40,<item:thermal:enderium_ingot>*3);
    metallurgicInfusingRecipe(<item:minecraft:glass_bottle>,<infuse_type:emcworld:ender>*80,<item:botania:ender_air_bottle>);
}