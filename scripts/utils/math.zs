#priority 86
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.ItemStack;
import math.Functions;

import mods.itemstages.ItemStages;
import mods.emcworld.math;
import mods.emcworld.configHelper;

public function getGameDifficulty() as double{
    return configHelper.getWorldDifficulty();
}

public function getDifficultyLoss() as double{
    return math.difficultyLoss();
}

public function getAdditionCost() as int{
    return Functions.round(getDifficultyLoss()) as int;
}

public function getLootTableAddition(base as float) as float{
    return Functions.min(0.99f,base * getDifficultyLoss() as float);
}

public function addStage(items as ItemStack[],stage as string) as void{
    for i in items{
        ItemStages.restrict(i.asIItemStack(),stage);
    }
}

public function addModStage(id as string[],stage as string) as void{
    ItemStages.createModRestriction(id,stage);
}
