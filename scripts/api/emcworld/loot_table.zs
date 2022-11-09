#priority 54

import crafttweaker.api.loot.conditions.LootConditionBuilder;
import crafttweaker.api.loot.conditions.vanilla.LootTableId;
import crafttweaker.api.loot.conditions.vanilla.RandomChance;
import crafttweaker.api.loot.modifiers.CommonLootModifiers;

import crafttweaker.api.util.MCResourceLocation;
import crafttweaker.api.item.IItemStack;

private function getLootTableName(item as IItemStack,namepath as MCResourceLocation) as string{
    return "emcworld_loot_tables_"+item.registryName.getPath()+namepath.getPath()+item.amount;
}

public function modifyLootTable(input as IItemStack,base as float,rl as MCResourceLocation) as void{
    var name as string = getLootTableName(input,rl);
    var chance as float = getLootTableAddition(base);
    loot.modifiers.register(
        name,
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