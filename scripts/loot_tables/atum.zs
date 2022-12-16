#priority 30

public function tweakerAtumLootTable() as void{
    var name as string[]=[
        "atum:chests/crate",
        "atum:chests/crate_bonus",
        "atum:chests/girafi_tomb",
        "atum:chests/lighthouse",
        "atum:chests/pharaoh",
        "atum:chests/pyramid_chest",
        "atum:chests/subloot/diamond_armor_0",
        "atum:chests/subloot/diamond_armor_25",
        "atum:chests/subloot/diamond_armor_35",
        "atum:chests/subloot/diamond_weapons_0",
        "atum:chests/subloot/diamond_weapons_25",
        "atum:chests/subloot/diamond_weapons_35",
        "atum:chests/subloot/gold_armor_0",
        "atum:chests/subloot/gold_armor_25",
        "atum:chests/subloot/gold_armor_35",
        "atum:chests/subloot/gold_weapons_0",
        "atum:chests/subloot/gold_weapons_25",
        "atum:chests/subloot/gold_weapons_35",
        "atum:chests/subloot/iron_armor_0",
        "atum:chests/subloot/iron_armor_25",
        "atum:chests/subloot/iron_armor_35",
        "atum:chests/subloot/iron_weapons_0",
        "atum:chests/subloot/iron_weapons_25",
        "atum:chests/subloot/iron_weapons_35",
        "atum:chests/subloot/limestone_weapons_0",
        "atum:chests/tomb",
        "atum:chests/village_crate"
    ];
    var cm = <item:emcworld:copper_medal>;
    var nb = <item:atum:nebu_ingot>;
    for i in name{
        modifyLootTable(cm,0.07f,getRL(i));
        modifyLootTable(nb*1,0.1f,getRL(i));
        modifyLootTable(nb*2,0.08f,getRL(i));
        modifyLootTable(nb*3,0.04f,getRL(i));
        modifyLootTable(nb*4,0.02f,getRL(i));
        modifyLootTable(nb*5,0.01f,getRL(i));
    }
}
