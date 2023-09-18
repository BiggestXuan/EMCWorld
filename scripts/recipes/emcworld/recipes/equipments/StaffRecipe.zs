#priority 62

public function addEMCWorldGodStaffRecipe() as void{
    infuserRecipe([
        <item:astralsorcery:starmetal_ingot>,
        <item:astralsorcery:infused_crystal_sword>,
        <item:mekanism:pellet_polonium>|<item:mekanism:pellet_plutonium>,
        <item:mekanism:ingot_refined_obsidian>,
        <item:quark:rainbow_rune>
    ],<item:emcworld:purple_staff>,30000,1000000,2);
    infuserRecipe([
        <item:astralsorcery:starmetal_ingot>,
        <item:astralsorcery:infused_crystal_sword>,
        <item:mythicbotany:vanaheim_rune>,
        <item:mythicbotany:yggdrasil_branch>,
        <item:quark:rainbow_rune>
    ],<item:emcworld:nature_staff>,30000,1000000,2);
}