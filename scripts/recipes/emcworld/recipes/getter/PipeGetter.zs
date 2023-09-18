#priority 80

import crafttweaker.api.item.IItemStack;

public function getPipezNormalItems() as IItemStack[]{
    return [
        <item:pipez:item_pipe>,
        <item:pipez:fluid_pipe>,
        <item:pipez:energy_pipe>,
        <item:pipez:gas_pipe>
    ];
}

public function getPipezUpgradeItems() as IItemStack[]{
    return [
        <item:pipez:basic_upgrade>,
        <item:pipez:improved_upgrade>,
        <item:pipez:advanced_upgrade>,
        <item:pipez:ultimate_upgrade>,
        <item:pipez:infinity_upgrade>
    ];
}

public function getPipezUtilsItems() as IItemStack[]{
    return [
        <item:mekanism:basic_control_circuit>,
        <item:minecraft:bucket>,
        <item:minecraft:redstone>,
        <item:minecraft:glass>
    ];
}