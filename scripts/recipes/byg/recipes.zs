#priority 15
import crafttweaker.api.item.IIngredient;

public function addEmeraldShardRecipe() as void{
    var tt as IIngredient= <item:botania:mana_pearl>|<item:botania:mana_diamond>;
    var pb = <item:quark:permafrost_bricks>;
    crushingRecipe(<item:minecraft:emerald>,<item:byg:emeraldite_shards>,1);
    tartaricForgeRecipe([
        <item:minecraft:blue_ice>,
        <item:botania:rune_winter>
    ],<item:byg:subzero_crystal_shard>,65,10);
    modifyShapedRecipe([
        [tt,pb,tt],
        [pb,<item:mythicbotany:niflheim_rune>,pb],
        [tt,pb,tt]
    ],<item:byg:hypogeal_imperium>);
}