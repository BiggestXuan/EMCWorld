#priority 45

import crafttweaker.api.item.ItemStack;

public function removeSmelterRecipe(input as ItemStack) as void{
    <recipetype:thermal:smelter>.removeRecipe(input.asIItemStack());
}

public function removeSmelterRecipe3(input1 as ItemStack,input2 as ItemStack,input3 as ItemStack)as void{
    <recipetype:thermal:smelter>.removeRecipe(input1.asIItemStack(),input2.asIItemStack(),input3.asIItemStack());
}

public function modifyTERecipe() as void{
    var bi = <item:thermal:bronze_ingot>;
    var item as ItemStack[]=[
        <item:thermal:copper_ingot>,
        <item:thermal:lead_ingot>,<item:thermal:silver_ingot>,<item:thermal:nickel_ingot>,<item:thermal:tin_ingot>];
    removeFurnaceRecipe(item);
    //removeSmelterCatalystRecipe();
    for i in item{
        removeSmelterRecipe(i);
    }
    var s = <item:thermal:rich_slag>;var a = <item:thermal:apatite>;removeSmelterRecipe3(item[1],item[2],s);removeSmelterRecipe3(item[2],item[1],s);removeSmelterRecipe3(item[0],<item:minecraft:gold_ingot>,s);removeSmelterRecipe3(item[3],item[0],s);removeSmelterRecipe3(item[4],a,s); removeSmelterRecipe3(<item:minecraft:iron_ingot>,item[3],s);
    var TEHigherAlloy as ItemStack[]=[bi,<item:thermal:signalum_ingot>,<item:thermal:lumium_ingot>,<item:thermal:enderium_ingot>];removeAllRecipe(TEHigherAlloy);removeSmelterRecipe(bi);removeAllRecipe(item);removeAllRecipe([bi]);var alloy as ItemStack[]=[
        <item:thermal:electrum_ingot>,
        <item:thermal:constantan_ingot>,
        <item:thermal:invar_ingot>
    ];var dust as ItemStack[]=[<item:thermal:signalum_dust>,<item:thermal:lumium_dust>,<item:thermal:enderium_dust>];removeCraftRecipe(dust);removeAllRecipe(alloy);
}
