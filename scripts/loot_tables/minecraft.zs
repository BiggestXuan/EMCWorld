 #priority 52
import crafttweaker.api.util.MCResourceLocation;

public function getRL(input as string) as MCResourceLocation{
    var part as string[] = input.split(":");
    return new MCResourceLocation(part[0],part[1]);
}

public function tweakerMinecraftLootTable() as void{
    var tableList as string[]=[
        "minecraft:chests/abandoned_mineshaft",
        "minecraft:chests/bastion_bridge",
        "minecraft:chests/bastion_hoglin_stable",
        "minecraft:chests/bastion_other",
        "minecraft:chests/bastion_treasure",
        "minecraft:chests/buried_treasure",
        "minecraft:chests/desert_pyramid",
        "minecraft:chests/end_city_treasure",
        "minecraft:chests/igloo_chest",
        "minecraft:chests/jungle_temple",
        "minecraft:chests/jungle_temple_dispenser",
        "minecraft:chests/nether_bridge",
        "minecraft:chests/pillager_outpost",
        "minecraft:chests/ruined_portal",
        "minecraft:chests/shipwreck_map",
        "minecraft:chests/shipwreck_supply",
        "minecraft:chests/shipwreck_treasure",
        "minecraft:chests/simple_dungeon",
        "minecraft:chests/spawn_bonus_chest",
        "minecraft:chests/stronghold_corridor",
        "minecraft:chests/stronghold_crossing",
        "minecraft:chests/stronghold_library",
        "minecraft:chests/underwater_ruin_big",
        "minecraft:chests/underwater_ruin_small",
        "minecraft:chests/village/village_armorer",
        "minecraft:chests/village/village_butcher",
        "minecraft:chests/village/village_cartographer",
        "minecraft:chests/village/village_desert_house",
        "minecraft:chests/village/village_fisher",
        "minecraft:chests/village/village_fletcher",
        "minecraft:chests/village/village_mason",
        "minecraft:chests/village/village_plains_house",
        "minecraft:chests/village/village_savanna_house",
        "minecraft:chests/village/village_shepherd",
        "minecraft:chests/village/village_snowy_house",
        "minecraft:chests/village/village_taiga_house",
        "minecraft:chests/village/village_tannery",
        "minecraft:chests/village/village_temple",
        "minecraft:chests/village/village_toolsmith",
        "minecraft:chests/village/village_weaponsmith",
        "minecraft:chests/void_treasure",
        "minecraft:chests/woodland_mansion"
    ];
    var gamePlay as string[]=[
        "minecraft:gameplay/piglin_bartering"
    ];
    for table in tableList{
        var rl as MCResourceLocation = getRL(table);
        modifyLootTable(<item:emcworld:small_emc_gem> * 6,0.12f,rl);
        modifyLootTable(<item:emcworld:big_emc_gem> * 4,0.05f,rl);
        modifyLootTable(<item:emcworld:big_emc_gem> * 3,0.1f,rl);
        modifyLootTable(<item:emcworld:big_emc_gem> * 2,0.15f,rl);
    }
    for i in 0 .. 7{
        var rl1 = getRL(tableList[i]);
        modifyLootTable(<item:gobber2:gobber2_globette> * 3,0.25f,rl1);
        modifyLootTable(<item:mekanism:ingot_steel> * 4,0.05f,rl1);
        modifyLootTable(<item:mekanism:ingot_steel> * 1,0.22f,rl1);
    }
    for i in 0 .. 23{
        var rl2 = getRL(tableList[i]);
        modifyLootTable(<item:emcworld:orichalcos_ingot> * 2,0.03f,rl2);
        modifyLootTable(<item:emcworld:orichalcos_ingot>* 1,0.08f,rl2);
    }
    for i in gamePlay{
        var rl3 as MCResourceLocation = getRL(i);
        modifyLootTable(<item:emcworld:niobium_nugget>,0.15f,rl3);
        modifyLootTable(<item:emcworld:niobium_nugget>*2,0.07f,rl3);
        modifyLootTable(<item:emcworld:niobium_nugget>*3,0.03f,rl3);
        modifyLootTable(<item:emcworld:big_emc_gem>*2,0.08f,rl3);
        modifyLootTable(<item:emcworld:biggest_emc_gem>,0.02f,rl3);
        modifyLootTable(<item:emcworld:niobium_nugget>*5,0.03f,rl3);
    }
    var atmn = <item:allthemodium:allthemodium_nugget>;
    var rl4 as MCResourceLocation = getRL(tableList[5]);
    modifyLootTable(atmn*6,0.05f,rl4);
    modifyLootTable(atmn*5,0.1f,rl4);
    modifyLootTable(atmn*4,0.15f,rl4);
    modifyLootTable(atmn*3,0.2f,rl4);
    modifyLootTable(atmn*2,0.26f,rl4);
    modifyLootTable(atmn*1,0.33f,rl4);
    var vn = <item:allthemodium:vibranium_nugget>;
    for i in 1 .. 5{
        var rl5 as MCResourceLocation = getRL(tableList[i]);
        modifyLootTable(vn*4,0.08f,rl5);
        modifyLootTable(vn*3,0.1f,rl5);
        modifyLootTable(vn*2,0.15f,rl5);
        modifyLootTable(vn*1,0.2f,rl5);
    } 
    var rl6 as MCResourceLocation = getRL(tableList[7]);
    var un = <item:allthemodium:unobtainium_nugget>;
    modifyLootTable(un*3,0.02f,rl6);
    modifyLootTable(un*2,0.05f,rl6);
    modifyLootTable(un*1,0.08f,rl6);
}