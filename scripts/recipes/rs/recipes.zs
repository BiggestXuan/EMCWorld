#priority 56
import crafttweaker.api.item.IItemStack;

import mods.emcworld.configHelper;

public function modifyRSRecipe() as void{
    var ir = <item:minecraft:iron_ingot>;
    var qe = <item:refinedstorage:quartz_enriched_iron>;
    var pd = <item:refinedstorage:processor_binding>;
    var si = <item:refinedstorage:silicon>;
    var cc = <item:refinedstorage:construction_core>;
    var dc = <item:refinedstorage:destruction_core>;
    var ap = <item:refinedstorage:advanced_processor>;
    var ip = <item:refinedstorage:improved_processor>;
    var ec = <item:mekanism:elite_control_circuit>;
    var glass = <tag:items:forge:glass>;

    if(!configHelper.enableRS()){
        removeCraftRecipeIItemStack([
            <item:refinedstorage:pattern_grid>,
            <item:refinedstorage:network_receiver>,
            <item:refinedstorage:network_transmitter>,
            <item:refinedstorage:disk_manipulator>,
            <item:refinedstorage:crafter>,
            <item:refinedstorage:crafter_manager>,
            <item:refinedstorage:crafting_monitor>
        ]);
    }
    modifyShapelessRecipe([
        <item:refinedstorage:basic_processor>,<item:minecraft:lapis_lazuli>
    ],<item:refinedstorage:destruction_core>);
    modifyShapelessRecipe([
        ec,dc,ip
    ],<item:refinedstorage:importer>);
    modifyShapelessRecipe([
        ec,cc,ip
    ],<item:refinedstorage:exporter>);
    modifyShapedRecipe([
        [qe,ap,qe],
        [dc,ec,dc],
        [qe,dc,qe]
    ],<item:refinedstorage:controller>);
    modifyShapedRecipe([
        [ip,cc,glass],
        [qe,ec,glass],
        [ip,dc,glass]
    ],<item:refinedstorage:grid>);
    modifyShapelessRecipe([
        ir,ir,ir,<item:emcworld:big_emc_gem>
    ],qe*4);
    modifyShapelessRecipe([
        <item:minecraft:string>,<item:emcworld:small_emc_gem>
    ],pd);
    removeFurnaceRecipe([si]);
    furnaceRecipeNoName(si,<item:emcworld:small_emc_gem>,0);
    if(configHelper.getWorldDifficulty() == 3){
        setEMCStage(qe,256,2);
        setEMCStage(pd,48,2);
        setEMCStage(si,32,2);
    }
    for i in [
        <item:refinedstorage:1k_storage_part>,
        <item:refinedstorage:4k_storage_part>,
        <item:refinedstorage:16k_storage_part>,
        <item:refinedstorage:64k_storage_part>,
        <item:refinedstorage:64k_fluid_storage_part>,
        <item:refinedstorage:256k_fluid_storage_part>,
        <item:refinedstorage:1024k_fluid_storage_part>,
        <item:refinedstorage:4096k_fluid_storage_part>,
        <item:extradisks:256k_storage_part>,
        <item:extradisks:1024k_storage_part>,
        <item:extradisks:4096k_storage_part>,
        <item:extradisks:16384k_storage_part>,
        <item:extradisks:65536k_storage_part>,
        <item:extradisks:262144k_fluid_storage_part>
    ] as IItemStack[]{
        addEMCStage(i,114514);
    }
    for i in [
        cc,
        dc,
        <item:refinedstorage:raw_basic_processor>,
        <item:refinedstorage:raw_improved_processor>,
        <item:refinedstorage:raw_advanced_processor>,
        <item:refinedstorage:basic_processor>,
        ip,
        ap,
        <item:refinedstorage:external_storage>,
        <item:refinedstorage:importer>,
        <item:refinedstorage:exporter>,
        <item:refinedstorage:interface>,
        <item:refinedstorage:fluid_interface>,
        <item:refinedstorage:constructor>,
        <item:refinedstorage:destructor>
    ] as IItemStack[]{
        addEMCStage(i,2);
    }
}