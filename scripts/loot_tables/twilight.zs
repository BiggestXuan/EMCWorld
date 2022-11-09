#priority 30

public function tweakerTwilightLootTable() as void{
    var name as string[]=[
        "twilightforest:entities/hydra",
        "twilightforest:entities/lich",
        "twilightforest:entities/minoshroom",
        "twilightforest:entities/naga",
        "twilightforest:entities/snow_queen",
        "twilightforest:entities/yeti_alpha"
    ];
    var beg = <item:emcworld:biggest_emc_gem>;
    for i in name{
        modifyLootTable(beg*3,1f,getRL(i));
        modifyLootTable(beg*2,0.6f,getRL(i));
        modifyLootTable(beg*1,0.4f,getRL(i));
    }
}