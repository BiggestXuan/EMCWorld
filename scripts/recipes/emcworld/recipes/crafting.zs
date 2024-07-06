#priority 52
import crafttweaker.api.item.IItemStack;

public function pro(s as IItemStack,s1 as IItemStack) as void{
    var dm = <item:projecte:dark_matter>;
    addCraftShapelessRecipe([s,dm,dm],s1);
}

public function pipe(input as IItemStack,output as IItemStack) as void{
    addCraftShapedRecipeNoName([
        [<tag:items:forge:ingots/steel>],
        [input],
        [<tag:items:forge:ingots/steel>]
    ],output);
}