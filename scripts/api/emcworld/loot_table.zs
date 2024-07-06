#priority 54

import crafttweaker.api.loot.conditions.LootConditionBuilder;
import crafttweaker.api.loot.conditions.vanilla.LootTableId;
import crafttweaker.api.loot.conditions.vanilla.RandomChance;
import crafttweaker.api.loot.modifiers.CommonLootModifiers;

import crafttweaker.api.util.MCResourceLocation;
import crafttweaker.api.item.IItemStack;

public function getLootTableName(item as IItemStack,namepath as MCResourceLocation) as string{
    return "emcworld_loot_tables_"+item.registryName.getPath()+"_"+namepath.getPath()+"_"+item.amount;
}

public function modifyLootTable(input as IItemStack,base as float,rl as MCResourceLocation) as void{
    var chance as float = getLootTableAddition(base);
    var name as string = getLootTableName(input,rl);
    loot.modifiers.register(
        name+"_"+chance,
        LootConditionBuilder.create()
            .add<LootTableId>(condition => {
                condition.withTableId(rl);
            })
            .add<RandomChance>(condition => {
                condition.withChance(chance);
            }),
        CommonLootModifiers.add(input)
    );
}