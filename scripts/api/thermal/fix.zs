#priority 48
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.ItemStack;
import mods.jei.JEI;

private function removePress(item as IItemStack) as void{
    <recipetype:thermal:press>.removeRecipe(item);
}

private function hide(item as IItemStack) as void{
    JEI.hideItem(item);
}

private function removeIngot(item as IItemStack) as void{
    <tag:items:forge:ingots>.remove(item);
    hide(item);
}

private function removeBlock(item as IItemStack) as void{
    <tag:items:forge:storage_blocks>.remove(item);
    removePress(item);
    hide(item);
}

private function removeNugget(item as IItemStack) as void{
    <tag:items:forge:nuggets>.remove(item);
    hide(item);
}

private function removeCopperNugget(item as IItemStack[]) as void{
    for i in item{
        <tag:items:forge:nuggets/copper>.remove(i);
        removeNugget(i);  
    }
}

private function removeBronze(item as IItemStack) as void{
    <tag:items:forge:ingots/bronze>.remove(item);
    removeIngot(item);
}

private function removeCopper(i as ItemStack) as void{
    var item as IItemStack = i.asIItemStack();
    <tag:items:forge:ingots/copper>.remove(item);
    removeIngot(item);
    removeAllRecipe([i]);
}

private function removeTin(i as ItemStack) as void{
    var item as IItemStack = i.asIItemStack();
    <tag:items:forge:ingots/tin>.remove(item);
    removeIngot(item);
    removeAllRecipe([i]);
}

private function removeLead(i as ItemStack) as void{
    var item as IItemStack = i.asIItemStack();
    <tag:items:forge:ingots/lead>.remove(item);
    removeIngot(item);
    removeAllRecipe([i]);
}

private function removeSilver(i as ItemStack) as void{
    var item as IItemStack = i.asIItemStack();
    <tag:items:forge:ingots/silver>.remove(item);
    removeIngot(item);
    removeAllRecipe([i]);
}

private function removeNickel(i as ItemStack) as void{
    var item as IItemStack = i.asIItemStack();
    <tag:items:forge:ingots/nickel>.remove(item);
    removeIngot(item);
    removeAllRecipe([i]);
}

private function removeTinBlock(i as ItemStack) as void{
    var item as IItemStack = i.asIItemStack();
    <tag:items:forge:storage_blocks/tin>.remove(item);
    removeBlock(item);
    removeAllRecipe([i]);
}

private function removeLeadBlock(i as ItemStack) as void{
    var item as IItemStack = i.asIItemStack();
    <tag:items:forge:storage_blocks/lead>.remove(item);
    removeBlock(item);
    removeAllRecipe([i]);
}

private function removeSilverBlock(i as ItemStack) as void{
    var item as IItemStack = i.asIItemStack();
    <tag:items:forge:storage_blocks/silver>.remove(item);
    removeBlock(item);
    removeAllRecipe([i]);
}

private function removeCopperBlock(item as ItemStack[]) as void{
    for i in item{
        <tag:items:forge:storage_blocks/copper>.remove(i.asIItemStack());
        removeBlock(i.asIItemStack());
    }
    removeAllRecipe(item);
}

private function removeDust(item as IItemStack[]) as void{
    for i in item{
        removeCraftRecipe([i]);
        <tag:items:forge:dusts>.remove(i);
        <recipetype:thermal:pulverizer>.removeRecipe(i);
        hide(i);
    }
    <recipetype:thermal:centrifuge>.removeRecipe(item,[<fluid:minecraft:empty>]);
}

public function fixTE() as void{
    var ld = <item:thermal:lead_dust>;
    var cd = <item:thermal:copper_ingot>;
    var td = <item:thermal:tin_dust>;
    var sd = <item:thermal:silver_dust>;
    var gr = <item:minecraft:gravel>;
    removeIngot(<item:thermal:copper_ingot>);
    removeBronze(<item:thermal:bronze_ingot>);
    removeCopperBlock([<item:thermal:copper_block>]);
    removeCopperNugget([<item:thermal:copper_nugget>]);
    removeTin(<item:thermal:tin_ingot>);
    removeLead(<item:thermal:lead_ingot>);
    removeNickel(<item:thermal:nickel_ingot>);
    removeSilver(<item:thermal:silver_ingot>);
    removeTinBlock(<item:thermal:tin_block>);
    removeLeadBlock(<item:thermal:lead_block>);
    removeSilverBlock(<item:thermal:silver_block>);
    removeCraftRecipe([<item:thermal:bronze_dust>,<item:thermal:constantan_dust>]);
    removeDust([
        <item:thermal:copper_dust>,
        <item:thermal:bronze_dust>,
        ld,
        td,
        <item:thermal:iron_dust>,
        <item:thermal:gold_dust>,
        <item:thermal:diamond_dust>,
        <item:thermal:emerald_dust>,
        <item:thermal:lapis_dust>
    ]);
    <recipetype:thermal:pulverizer_catalyst>.removeRecipe(<item:minecraft:flint>);
    <recipetype:thermal:pulverizer_catalyst>.removeRecipe(<item:thermal:basalz_powder>);
    <recipetype:thermal:pulverizer>.removeRecipe(ld,sd,gr);
    <recipetype:thermal:pulverizer>.removeRecipe(sd,ld,gr);
    <recipetype:thermal:pulverizer>.removeRecipe(td,<item:thermal:apatite>,gr);
    <recipetype:thermal:centrifuge>.removeRecipe([cd*3,td],[<fluid:minecraft:empty>]);
}