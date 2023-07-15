#priority 53
import crafttweaker.api.item.IItemStack;

public function pink(c as IItemStack,r as IItemStack) as void{
    infuserRecipe([
        <item:emcworld:pink_matter>,
        <item:rats:oratchalcum_block>,
        <item:rats:dutchrat_wheel>,
        c,
        <item:rats:arcane_technology>
    ],r,4000,5000000,2);
}

public function orange() as void{
    kt(<item:emcworld:orange_matter>,<item:emcworld:emc_core>,[[<item:projectex:yellow_collector>,<item:projectex:orange_collector>],[<item:projectex:yellow_relay>,<item:projectex:orange_relay>]]);
}

public function blue(c as IItemStack,r as IItemStack) as void{
    treeRitualRecipe([
        c,
        <item:undergarden:forgotten_block>,
        <item:undergarden:masticator_scales>,
        <item:aerialhell:obsidian_shard>,
        <item:extendedcrafting:elite_component>,
        <item:aerialhell:arsonist_ingot>,
        <item:undergarden:regalium_block>,
        <item:emcworld:blue_matter>
    ],<item:undergarden:smogstem_sapling>,r);
}

public function cyan(c as IItemStack,r as IItemStack) as void{
    mythicInfuserRecipe([
        c,
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:sculk" as string}),
        <item:emcworld:cyan_matter>,
        <item:emcworld:super_emc_gem>,
        <item:dead_guys_untitled_deep_dark_:warden_antler>
    ],r,4000000,0xffffff,0xffffff);
}

public function green(c as IItemStack,r as IItemStack) as void{
    nucleosyRecipe(c,<gas:emcworld:stable_void>*1000,r,100);
}

public function lime(c as IItemStack,r as IItemStack) as void{
    alchemalTableRecipe([
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:alfsteel" as string}),
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:atm" as string}),
        <item:extendedcrafting:singularity>.withTag({Id: "extendedcrafting:vibranium" as string}),
        c
    ],r,300000,4);
}

public function pul(a as IItemStack,b as IItemStack,c as IItemStack,d as IItemStack) as void{
    addCraftShapedRecipeNoName([
        [a,b,a],
        [b,c,b],
        [a,b,a]
    ],d);
}