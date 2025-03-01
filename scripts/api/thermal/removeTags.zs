#priority 44
import crafttweaker.api.item.ItemStack;

public function removeTETags() as void{
    var bi = <item:thermal:bronze_ingot>;
    <tag:items:forge:ores/copper>.remove(<item:thermal:copper_ore>);
    <tag:items:forge:ores/lead>.remove(<item:thermal:lead_ore>);
    <tag:items:forge:ores/nickel>.remove(<item:thermal:nickel_ore>);
    <tag:items:forge:ores/silver>.remove(<item:thermal:silver_ore>);
    <tag:items:forge:ores/tin>.remove(<item:thermal:tin_ore>);
    <tag:items:forge:ingots/copper>.remove(<item:thermal:copper_ingot>);
    <tag:items:forge:nuggets/copper>.remove(<item:thermal:copper_nugget>);
    <tag:items:forge:nuggets/bronze>.remove(<item:thermal:bronze_nugget>);
    <tag:items:forge:nuggets/lead>.remove(<item:thermal:lead_nugget>);
    <tag:items:forge:nuggets/tin>.remove(<item:thermal:tin_nugget>);
    <tag:items:forge:dusts/copper>.remove(<item:thermal:copper_dust>);
    <tag:items:forge:dusts/bronze>.remove(<item:thermal:bronze_dust>);
    <tag:items:forge:dusts/silver>.add(<item:emcworld:dust_silver>);
    <tag:items:forge:dusts/lead>.remove(<item:thermal:lead_dust>);
    <tag:items:forge:dusts/tin>.remove(<item:thermal:tin_dust>);
    
}