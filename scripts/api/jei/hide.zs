#priority 28

import mods.jei.JEI;
import mods.emcworld.DifficultyItem;
import mods.emcworld.ItemUtils;

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
    for i in loadedMods.getMod("divinerpg").items{
        if(ItemUtils.isTier(i) || ItemUtils.isArmor(i)){
            JEI.hideItem(i);
            removeCraftRecipe([i]);
        }
    }
}