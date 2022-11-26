#priority 12
import crafttweaker.api.item.IItemStack;

public class emcItem{
    public static var INSTANCE as emcItem = new emcItem();

    public this() {

    }

    var stack as IItemStack[]=[
        <item:minecraft:powered_rail>,
        <item:minecraft:detector_rail>,
        <item:minecraft:rail>,
        <item:minecraft:activator_rail>,
        <item:minecraft:tnt>,
        <item:minecraft:nether_star>,
        <item:mekanism:alloy_infused>,
        <item:mekanism:alloy_reinforced>,
        <item:mekanism:module_base>,
        <item:minecraft:trident>,
        <item:projecte:interdiction_torch>,
        <item:thermal:signalum_ingot>,
        <item:thermal:constantan_ingot>,
        <item:thermal:enderium_ingot>,
        <item:thermal:lumium_ingot>,
        <item:undergarden:catalyst>,
        <item:mekanism:hdpe_pellet>,
        <item:minecraft:sand>,
        <item:minecraft:gravel>,
        <item:byg:emeraldite_shards>,
        <item:minecraft:water_bucket>,
        <item:minecraft:lava_bucket>,
        <item:minecraft:milk_bucket>
    ];

    public getStack() as IItemStack[]{
        return this.stack;
    }
}