#priority 133

import crafttweaker.api.item.IItemStack;


public function getSingularity() as IItemStack[][]{
    var dm = <item:projecte:dark_matter>;

    return [
        [
            crts("atm"),
            crts("vibranium"),
            crts("invar"),
            crts("coal"),
            crts("electrum"),
            crts("alfsteel"),
            crts("redstone"),
            crts("emerald"),
            crts("glowstone"),
            crts("steel"),
            crts("diamond"),
            crts("uranium"),
            crts("iron"),
            crts("lead"),
            crts("copper"),
            crts("silver"),
            crts("tin"),
            crts("nickel"),
            crts("lapis_lazuli"),
            crts("bronze"),
            crts("clock"),
            crts("dark_matter"),
            crts("red_matter"),
            crts("sculk"),
            crts("aluminum"),
            crts("unobtainium")
        ],
        [
            <item:allthemodium:allthemodium_ingot>,
            <item:allthemodium:vibranium_ingot>,
            <item:thermal:invar_ingot>,
            <item:minecraft:coal>,
            <item:thermal:electrum_ingot>,
            <item:mythicbotany:alfsteel_ingot>,
            <item:minecraft:redstone>,
            <item:minecraft:emerald>,
            <item:minecraft:glowstone_dust>,
            <item:mekanism:ingot_steel>,
            <item:minecraft:diamond>,
            <item:mekanism:ingot_uranium>,
            <item:minecraft:iron_ingot>,
            <item:mekanism:ingot_lead>,
            <item:mekanism:ingot_copper>,
            <item:emcworld:silver_ingot>,
            <item:mekanism:ingot_tin>,
            <item:emcworld:nickel_ingot>,
            <item:minecraft:lapis_lazuli>,
            <item:mekanism:ingot_bronze>,
            <item:minecraft:clock>,
            dm,
            <item:projecte:red_matter>,
            <item:dead_guys_untitled_deep_dark_:sculk>,
            <item:emcworld:aluminum_ingot>,
            <item:allthemodium:unobtainium_ingot>
        ]
    ];
}