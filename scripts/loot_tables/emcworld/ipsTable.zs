# priority 36
import mods.emcworld.math;
import mods.emcworld.configHelper;

public function ips() as void{
    var ri = <item:mekanism:ingot_refined_obsidian>;
    var ig = <item:emcworld:illager_gem>;
    var names as string[] = [
        "illagers_plus:structure/fort_common",
        "illagers_plus:structure/fort_rare",
        "illagers_plus:structure/ice_castle_common",
        "illagers_plus:structure/ice_castle_rare",
        "illagers_plus:structure/illager_archer_tower",
        "illagers_plus:structure/illager_centre",
        "illagers_plus:structure/illager_mine",
        "illagers_plus:structure/illager_tower_archery",
        "illagers_plus:structure/illager_tower_brewing",
        "illagers_plus:structure/illager_tower_common",
        "illagers_plus:structure/illager_tower_dendrology",
        "illagers_plus:structure/illager_tower_enchanting",
        "illagers_plus:structure/illager_tower_rare",
        "illagers_plus:structure/illager_tower_uncommon",
        "minecraft:chests/pillager_outpost"
    ];
    for i in [0,1]{
        var a as string = names[i];
        if(!configHelper.isFreeMode() && !configHelper.PillagerChestLimit()){
            modifyLootTable(ig*3,0.06f,getRL(a));
            modifyLootTable(ig*4,0.04f,getRL(a));
            modifyLootTable(ig*5,0.02f,getRL(a));
            modifyLootTable(<item:emcworld:biggest_emc_gem>*4,0.1f,getRL(a));
            modifyLootTable(<item:emcworld:biggest_emc_gem>*7,0.05f,getRL(a));
            modifyLootTable(<item:emcworld:advanced_emc_stored_totem>.withDamage(math.getRangeRandom(7000000,9500000)),0.01f,getRL(a));
            modifyLootTable(<item:emcworld:biggest_emc_gem>*10,0.02f,getRL(a));
        }else{
            modifyLootTable(ig,0.1f,getRL(a));
            modifyLootTable(ig*2,0.08f,getRL(a));
        }
        
    }
    modifyLootTable(ri*3,0.04f,getRL(names[1]));
    for i in names{
        modifyLootTable(<item:emcworld:big_emc_gem>*5,0.05f,getRL(i));
        modifyLootTable(<item:emcworld:biggest_emc_gem>*3,0.1f,getRL(i));
        modifyLootTable(<item:emcworld:biggest_emc_gem>*6,0.1f,getRL(i));
        modifyLootTable(ig*2,0.06f,getRL(i));
        modifyLootTable(ig*3,0.04f,getRL(i));
    }
}

