#priority 44
import crafttweaker.api.item.ItemStack;
import crafttweaker.api.item.MCItemDefinition;
import crafttweaker.api.blocks.MCBlock;

public function addOreTag(ores as MCBlock[]) as void{
    for i in ores{
        <tag:blocks:forge:ores>.add(i);
        <tag:items:forge:ores>.add(i.asItem());
    }
}

public function addEMCWorldOresTag() as void{
    var ores as MCBlock[]=[
        <block:emcworld:emc_ore>,
        <block:emcworld:rich_emc_ore>,
        <block:emcworld:nether_emc_ore>,
        <block:emcworld:end_emc_ore>,
        <block:emcworld:end_rich_emc_ore>,
        <block:emcworld:cold_ore>,
        <block:emcworld:chlorophyte_ore>,
        <block:emcworld:orichalcos_ore>,
        <block:emcworld:nickel_ore>,
        <block:emcworld:aluminum_ore>,
        <block:emcworld:silver_ore>
    ];
    for i in 0 .. ores.length{
        if(i<5){
            <tag:blocks:forge:ores/emc>.add(ores[i]);
            <tag:items:forge:ores/emc>.add(ores[i].asItem());
        }
        if(i==2){
            <tag:blocks:forge:ores/nether_emc>.add(ores[i]);
            <tag:items:forge:ores/nether_emc>.add(ores[i].asItem());
        }
        if(i>2 && i<5){
            <tag:blocks:forge:ores/end_emc>.add(ores[i]);
            <tag:items:forge:ores/end_emc>.add(ores[i].asItem());
        }
        if(i==1||i==4){
            <tag:blocks:forge:ores/rich_emc>.add(ores[i]);
            <tag:items:forge:ores/rich_emc>.add(ores[i].asItem());
        }
        if(i==4){
            <tag:blocks:forge:ores/end_rich_emc>.add(ores[i]);
            <tag:items:forge:ores/end_rich_emc>.add(ores[i].asItem());
        }
    }
    <tag:blocks:forge:ores/cold>.add(ores[5]);
    <tag:items:forge:ores/cold>.add(ores[5].asItem());
    <tag:blocks:forge:ores/chlorophyte>.add(ores[6]);
    <tag:items:forge:ores/chlorophyte>.add(ores[6].asItem());
    <tag:blocks:forge:ores/orichalcos>.add(ores[7]);
    <tag:items:forge:ores/orichalcos>.add(ores[7].asItem());
    <tag:blocks:forge:ores/nickel>.add(ores[8]);
    <tag:items:forge:ores/nickel>.add(ores[8].asItem());
    <tag:blocks:forge:ores/aluminum>.add(ores[9]);
    <tag:items:forge:ores/aluminum>.add(ores[9].asItem());
    <tag:blocks:forge:ores/silver>.add(ores[10]);
    <tag:items:forge:ores/silver>.add(ores[10].asItem());
    <tag:items:forge:ingots/silver>.add(<item:emcworld:silver_ingot>);
    <tag:items:forge:ingots/aluminum>.add(<item:emcworld:aluminum_ingot>);
    <tag:items:forge:ingots/nickel>.add(<item:emcworld:nickel_ingot>);
    addOreTag(ores);
}