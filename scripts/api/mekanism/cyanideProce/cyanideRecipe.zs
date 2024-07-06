#priority 50
import crafttweaker.api.item.ItemStack;

import mods.emcworld.math;

public function addAmmoniaRecipe() as void{
    chemicalInfusingRecipe(<gas:emcworld:nitrogen>,<gas:mekanism:hydrogen>*3,<gas:emcworld:ammonia>*2);
}

public function specialCoalReactionRecipe(coal as ItemStack,rate as int) as void{
    var amt as int= math.getDifficultyMulti(50)*rate;
    reactionRecipe(coal,<fluid:minecraft:water>*1000,<gas:emcworld:ammonia>*amt,<item:mekanism:substrate>*rate,<gas:emcworld:hydrogen_cyanide>*amt);  
}

public function addHydrogenCyanideRecipe() as void{
    specialCoalReactionRecipe(<item:minecraft:coal>,2);
    specialCoalReactionRecipe(<item:minecraft:charcoal>,4);
    specialCoalReactionRecipe(<item:mekanism:enriched_carbon>,16);
}

public function addSodiumCyanideRecipe() as void{
    chemicalInfusingRecipe(<gas:mekanism:sodium>,<gas:emcworld:hydrogen_cyanide>,<gas:emcworld:sodium_cyanide>);
}

public function addCosmicFlowRecipe() as void{
    var amt as int = math.getDifficultyMulti(5);
    chemicalInfusingRecipe(<gas:emcworld:sodium_cyanide>*amt,<gas:mekanism:antimatter>,<gas:emcworld:cosmic_flow>*amt);
}