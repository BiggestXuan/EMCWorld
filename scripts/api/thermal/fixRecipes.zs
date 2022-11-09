#priority 28
import crafttweaker.api.item.IItemStack;

private function onetonine(input as IItemStack,output as IItemStack) as void{
    addCraftShapelessRecipe([input],output*9);
}

private function fix(input as IItemStack[]) as void{
    var ingot = input[0];
    var dust = input[1];
    var block = input[2];
    onetonine(block,ingot);
    furnaceRecipeNoName(ingot,dust,1);
    crushingRecipe(ingot,dust,1);
}

public function fixTEAlloyRecipe() as void{
    fix([<item:thermal:electrum_ingot>,<item:thermal:electrum_dust>,<item:thermal:electrum_block>]);
    fix([<item:thermal:invar_ingot>,<item:thermal:invar_dust>,<item:thermal:invar_block>]);
    fix([<item:thermal:constantan_ingot>,<item:thermal:constantan_dust>,<item:thermal:constantan_block>]);
    fix([<item:thermal:enderium_ingot>,<item:thermal:enderium_dust>,<item:thermal:enderium_block>]);
    fix([<item:thermal:lumium_ingot>,<item:thermal:lumium_dust>,<item:thermal:lumium_block>]);
    fix([<item:thermal:signalum_ingot>,<item:thermal:signalum_dust>,<item:thermal:signalum_block>]);
}