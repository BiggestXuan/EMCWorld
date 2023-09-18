#priority 59

import crafttweaker.api.item.IItemStack;

public function EMCWorldDifficultyRecipes() as void{
    var pdm as IItemStack[] = getProjecteBaseArmor();

    if(getGameDifficulty() == 3){
        D3Recipes();
    }
    if(getGameDifficulty() >= 2){
        D2Recipes();
    }else{
        removeCraftRecipeIItemStack(pdm);
    }
    if(getGameDifficulty() >= 1){
        D1Recipes();
    }
}