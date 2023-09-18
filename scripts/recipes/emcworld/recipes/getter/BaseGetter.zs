#priority 107
import crafttweaker.api.item.IItemStack;

public function getRedArmor() as IItemStack[]{
    return [
        <item:emcworld:guardian_helmet>,
        <item:emcworld:guardian_chestplate>,
        <item:emcworld:guardian_leggings>,
        <item:emcworld:guardian_boots>,
        <item:emcworld:fire_red_helmet>,
        <item:emcworld:fire_red_chestplate>,
        <item:emcworld:fire_red_leggings>,
        <item:emcworld:fire_red_boots>
    ];
}

public function getProjecteAlchBags() as IItemStack[]{
    return [
        <item:projecte:light_gray_alchemical_bag>,
        <item:projecte:gray_alchemical_bag>,
        <item:projecte:red_alchemical_bag>,
        <item:projecte:magenta_alchemical_bag>,
        <item:projecte:pink_alchemical_bag>,
        <item:projecte:purple_alchemical_bag>,
        <item:projecte:blue_alchemical_bag>,
        <item:projecte:cyan_alchemical_bag>,
        <item:projecte:light_blue_alchemical_bag>,
        <item:projecte:green_alchemical_bag>,
        <item:projecte:lime_alchemical_bag>,
        <item:projecte:yellow_alchemical_bag>,
        <item:projecte:orange_alchemical_bag>,
        <item:projecte:white_alchemical_bag>,
        <item:projecte:brown_alchemical_bag>,
        <item:projecte:black_alchemical_bag>
    ];
}

public function getLuckGems() as IItemStack[]{
    return [
        <item:emcworld:lucky_gem_blue>,
        <item:emcworld:lucky_gem_red>,
        <item:emcworld:lucky_gem_purple>,
        <item:emcworld:lucky_gem_gold>
    ];
}

public function getBaseTierItems() as IItemStack[][]{
    return [
        [
            <item:minecraft:oak_planks>,
            <item:minecraft:cobblestone>,
            <item:minecraft:iron_ingot>,
            <item:minecraft:gold_ingot>,
            <item:minecraft:diamond>
        ],
        [
            <item:emcworld:wooden_staff>,
            <item:emcworld:stone_staff>,
            <item:emcworld:iron_staff>,
            <item:emcworld:golden_staff>,
            <item:emcworld:diamond_staff>
        ],
        [
            <item:emcworld:wooden_warhammer>,
            <item:emcworld:stone_warhammer>,
            <item:emcworld:iron_warhammer>,
            <item:emcworld:golden_warhammer>,
            <item:emcworld:diamond_warhammer>
        ],
        [
            <item:emcworld:wooden_dagger>,
            <item:emcworld:stone_dagger>,
            <item:emcworld:iron_dagger>,
            <item:emcworld:golden_dagger>,
            <item:emcworld:diamond_dagger>
        ],
        [
            <item:emcworld:wooden_gun>,
            <item:emcworld:stone_gun>,
            <item:emcworld:iron_gun>,
            <item:emcworld:golden_gun>,
            <item:emcworld:diamond_gun>
        ]
    ];
}

public function getTungItems() as IItemStack[]{
    return [
        <item:stalwart_dungeons:tungsten_helmet>,
        <item:stalwart_dungeons:tungsten_chestplate>,
        <item:stalwart_dungeons:tungsten_leggings>,
        <item:stalwart_dungeons:tungsten_boots>,
        <item:stalwart_dungeons:tungsten_sword>,
        <item:stalwart_dungeons:tungsten_axe>,
        <item:stalwart_dungeons:tungsten_pickaxe>,
        <item:stalwart_dungeons:tungsten_shovel>,
        <item:stalwart_dungeons:tungsten_hoe>,
        <item:stalwart_dungeons:tungsten_hammer>
    ];
}