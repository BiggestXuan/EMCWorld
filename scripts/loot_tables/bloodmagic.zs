#priority 30

public function tweakerBloodMagicLootTable() as void{
    var name as string[]=[
        "bloodmagic:chests/simple_dungeon/bastion",
        "bloodmagic:chests/simple_dungeon/crypt",
        "bloodmagic:chests/simple_dungeon/entrance_chest",
        "bloodmagic:chests/simple_dungeon/farm_parts",
        "bloodmagic:chests/simple_dungeon/farm_tools",
        "bloodmagic:chests/simple_dungeon/food",
        "bloodmagic:chests/simple_dungeon/library",
        "bloodmagic:chests/simple_dungeon/nether",
        "bloodmagic:chests/simple_dungeon/potion_ingredients",
        "bloodmagic:chests/simple_dungeon/simple_armoury",
        "bloodmagic:chests/simple_dungeon/simple_blacksmith"
    ];
    var be = <item:emcworld:blood_eye>;
    for i in name{
        modifyLootTable(be,0.12f,getRL(i));
    }
}