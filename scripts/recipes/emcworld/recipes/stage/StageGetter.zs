#priority 122

import crafttweaker.api.item.IItemStack;

public function getEMCWorldEMCStageItem() as IItemStack[][]{
    return [
        [
            <item:mekanism:ingot_osmium>,
            <item:minecraft:iron_ingot>
        ],
        [
            <item:mekanism:ingot_tin>,
            <item:mekanism:ingot_bronze>,
            <item:mekanism:ingot_copper>,
            <item:mekanism:ingot_lead>,
            <item:emcworld:silver_ingot>,
            <item:emcworld:magnesium_ingot>,
            <item:minecraft:lapis_lazuli>
        ],
        [
            <item:mekanism:ingot_uranium>,
            <item:mekanism:ingot_refined_glowstone>,
            <item:minecraft:gold_ingot>,
            <item:minecraft:diamond>,
            <item:emcworld:aluminum_ingot>,
            <item:emcworld:nickel_ingot>,
            <item:emcworld:drystone_ingot>,
            <item:mekanism:enriched_iron>,
            <item:mekanism:ingot_steel>
        ],
        [
            <item:allthemodium:allthemodium_ingot>,
            <item:byg:pendorite_ingot>,
            <item:emcworld:cold_ingot>,
            <item:emcworld:chlorophyte_ingot>,
            <item:emcworld:orichalcos_ingot>
        ],
        [
            <item:allthemodium:vibranium_ingot>,
            <item:emcworld:niobium_ingot>,
            <item:emcworld:sunlit_ingot>,
            <item:minecraft:netherite_scrap>
        ],
        [
            <item:allthemodium:unobtainium_ingot>
        ],
        [
            <item:emcworld:titanium_ingot>
        ]
    ];
}