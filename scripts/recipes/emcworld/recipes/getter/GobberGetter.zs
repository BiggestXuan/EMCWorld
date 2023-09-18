#priority 72

import crafttweaker.api.item.IItemStack;

public function getGobberTools() as IItemStack[]{
    return [
        <item:gobber2:gobber2_helmet>,
        <item:gobber2:gobber2_chestplate>,
        <item:gobber2:gobber2_leggings>,
        <item:gobber2:gobber2_boots>,
        <item:gobber2:gobber2_sword>,
        <item:gobber2:gobber2_axe>,
        <item:gobber2:gobber2_pickaxe>,
        <item:gobber2:gobber2_shovel>,
        <item:gobber2:gobber2_hoe>
    ];
}

public function getGobberNetherTools() as IItemStack[]{
    return [
        <item:gobber2:gobber2_bow_nether>,
        <item:gobber2:gobber2_tree_axe_nether>,
        <item:gobber2:gobber2_excavator_nether>
    ];
}

public function getGobberNetherItems() as IItemStack[]{
    return [
        <item:gobber2:gobber2_helmet_nether>,
        <item:gobber2:gobber2_chestplate_nether>,
        <item:gobber2:gobber2_leggings_nether>,
        <item:gobber2:gobber2_boots_nether>,
        <item:gobber2:gobber2_sword_nether>,
        <item:gobber2:gobber2_axe_nether>,
        <item:gobber2:gobber2_pickaxe_nether>,
        <item:gobber2:gobber2_shovel_nether>,
        <item:gobber2:gobber2_hoe_nether>,
        <item:gobber2:gobber2_hammer_nether>
    ];
}

public function getGobberEnderArmors() as IItemStack[]{
    return [
        <item:gobber2:gobber2_helmet_end>,
        <item:gobber2:gobber2_chestplate_end>,
        <item:gobber2:gobber2_leggings_end>,
        <item:gobber2:gobber2_boots_end>
    ];
}