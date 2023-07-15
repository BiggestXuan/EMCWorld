#priority 28
import crafttweaker.api.item.IItemStack;

public function addAlloyRecipe() as void{
    addInfusionConversionRecipe();
    metallurgicInfusingRecipe(<item:mekanism:ingot_copper>,<infuse_type:emcworld:nickel>*10,<item:thermal:constantan_ingot>*2);
    metallurgicInfusingRecipe(<item:emcworld:silver_ingot>,<infuse_type:mekanism:gold>*10,<item:thermal:electrum_ingot>*2);
    addEnrichedRecipe();
    metallurgicInfusingRecipe(<item:mekanism:ingot_steel>*2,<infuse_type:emcworld:nickel>*10,<item:thermal:invar_ingot>*3);
    metallurgicInfusingRecipe(<item:emcworld:aluminum_ingot>*3,<infuse_type:emcworld:silver>*20,<item:thermal:signalum_ingot>*6);
    metallurgicInfusingRecipe(<item:mekanism:ingot_refined_glowstone>*3,<infuse_type:emcworld:silver>*20,<item:thermal:lumium_ingot>*4);
    metallurgicInfusingRecipe(<item:mekanism:ingot_lead>*3,<infuse_type:emcworld:ender>*40,<item:thermal:enderium_ingot>*3);
    metallurgicInfusingRecipe(<item:minecraft:glass_bottle>,<infuse_type:emcworld:ender>*80,<item:botania:ender_air_bottle>);
}