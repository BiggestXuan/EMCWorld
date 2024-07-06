#priority 52
import crafttweaker.api.item.IIngredient;

import mods.emcworld.CrTSingularity;

public function crts(name as string) as IIngredient{
    return CrTSingularity.getCrTSingularity(name);
}