#priority 72

import crafttweaker.api.item.IItemStack;

public function getNetherItems() as IItemStack[]{
    return [
        <item:minecraft:netherite_helmet>,
        <item:minecraft:netherite_chestplate>,
        <item:minecraft:netherite_leggings>,
        <item:minecraft:netherite_boots>,
        <item:minecraft:netherite_sword>,
        <item:minecraft:netherite_axe>,
        <item:minecraft:netherite_pickaxe>,
        <item:minecraft:netherite_shovel>,
        <item:minecraft:netherite_hoe>,
        <item:stalwart_dungeons:netherite_hammer>
    ];
}

public function getNetherArmors() as IItemStack[]{
    return [
        <item:emcworld:fire_red_helmet>,
        <item:emcworld:fire_red_chestplate>,
        <item:emcworld:fire_red_leggings>,
        <item:emcworld:fire_red_boots>,
        <item:minecraft:netherite_helmet>,
        <item:minecraft:netherite_chestplate>,
        <item:minecraft:netherite_leggings>,
        <item:minecraft:netherite_boots>
    ];
}