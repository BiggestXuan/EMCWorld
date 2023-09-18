#priority 52

import crafttweaker.api.item.IItemStack;

public function addEMCWorldEMCStage() as void{
    var emc_stage as IItemStack[][] = getEMCWorldEMCStageItem();
    var anti = <item:mekanism:pellet_antimatter>;

    setEMCStage(<item:botania:manasteel_ingot>,864,3);
    setEMCStage(<item:botania:mana_pearl>,1146,3);
    setEMCStage(<item:botania:mana_diamond>,8246,3);
    setEMCStage(<item:botania:elementium_ingot>,1768,3);
    setEMCStage(<item:botania:dragonstone>,8262,3);
    setEMCStage(<item:botania:terrasteel_ingot>,32132,4);
    setEMCStage(<item:mythicbotany:alfsteel_ingot>,143420,6);
    setEMCStage(<item:twilightforest:fiery_blood>,32768*2,6);
    setEMCStage(<item:twilightforest:fiery_tears>,32768*2,6);
    setEMCStage(<item:twilightforest:knightmetal_ingot>,16384,5);
    setEMCStage(<item:twilightforest:carminite>,8192,6);
    setEMCStage(<item:emcworld:unreal_metal>,(8192*2.5) as int,6);
    setEMCStage(<item:mekanism:fluorite_gem>,1024,5);
    addEMCStage(<item:emcworld:enriched_gobber>,4);
    setEMCStage(<item:gobber2:gobber2_globette>,2048,4);
    setEMCStage(<item:gobber2:gobber2_glob>,18432,4);
    setEMCStage(<item:gobber2:gobber2_ingot>,18514,4);
    setEMCStage(<item:gobber2:gobber2_globette_nether>,16384,6);
    setEMCStage(<item:gobber2:gobber2_glob_nether>,147456,6);
    setEMCStage(<item:gobber2:gobber2_ingot_nether>,148105,6);
    setEMCStage(<item:gobber2:gobber2_globette_end>,131072,8);
    setEMCStage(<item:gobber2:gobber2_glob_end>,1179648,8);
    setEMCStage(<item:gobber2:gobber2_ingot_end>,1327753,8);
    addEMCStage(<item:bloodmagic:dungeon_metal>,4);
    addEMCStage(<item:rats:assorted_vegetables>,10);
    add_emc_stage(emc_stage[0],2);
    add_emc_stage(emc_stage[1],3);
    add_emc_stage(emc_stage[2],4);
    add_emc_stage(emc_stage[3],5);
    add_emc_stage(emc_stage[4],6);
    add_emc_stage(emc_stage[5],7);
    add_emc_stage(emc_stage[6],8);
    setEMCStage(anti,46080000,114514);
    setEMCStage(<item:emcworld:universal_ball>,320000,8);
}