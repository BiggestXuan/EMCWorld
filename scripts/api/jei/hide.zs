#priority 28

import mods.jei.JEI;
import mods.emcworld.DifficultyItem;

import crafttweaker.api.mods.Mods;

public function hideItemInJei() as void{
    for i in new Getter().getHide(){
        JEI.hideItem(i);
    }
    removeCraftRecipe(new Getter().getHide());
    var emcworld = loadedMods.getMod("emcworld");
    for i in emcworld.items{
        if(!(DifficultyItem.isReachDifficulty(i))){
            JEI.hideItem(i);
            removeCraftRecipe([i]);
        }
    }
}