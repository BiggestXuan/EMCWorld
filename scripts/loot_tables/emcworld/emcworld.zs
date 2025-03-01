# priority 27
import crafttweaker.api.item.IItemStack;
import mods.emcworld.math;
import mods.emcworld.configHelper;

public function tweakerBotania() as void{
    var ei = <item:extendedcrafting:ender_ingot>;
    var eei = <item:botania:elementium_ingot>;
    var iss = <item:mekanism:ingot_steel>;
    var name as string = "botania:gaia_guardian_2";
    var bn as string = "botania:gaia_guardian";
    var ii as IItemStack[] = [
        <item:mythicbotany:asgard_rune>,
        <item:mythicbotany:vanaheim_rune>,
        <item:mythicbotany:alfheim_rune>,
        <item:mythicbotany:midgard_rune>,
        <item:mythicbotany:joetunheim_rune>,
        <item:mythicbotany:muspelheim_rune>,
        <item:mythicbotany:niflheim_rune>,
        <item:mythicbotany:nidavellir_rune>,
        <item:mythicbotany:helheim_rune>,
        <item:emcworld:abunana_loot>,
        <item:emcworld:scroll_green>,
        <item:emcworld:scroll_blue>,
        <item:emcworld:scroll_gaia>
    ];
    for i in [ei,eei]{
        modifyLootTable(i*8,0.3f,getRL(name));
        modifyLootTable(i*6,0.18f,getRL(name));
        modifyLootTable(i*5,0.13f,getRL(name));
        modifyLootTable(i*4,0.11f,getRL(name));
        modifyLootTable(i*3,0.08f,getRL(name));
        modifyLootTable(i*2,0.04f,getRL(name));
        modifyLootTable(i,0.02f,getRL(name));
    }
    for i in ii{
        modifyLootTable(i*3,0.1f,getRL(name));
        modifyLootTable(i*2,0.15f,getRL(name));
        modifyLootTable(i*1,0.2f,getRL(name));
    }
    for n in [name,bn] as string[]{
        for a in 1 .. 7{
            modifyLootTable(<item:emcworld:scroll_white>*a,0.15f-0.02f*a,getRL(n));
        }
    }
    modifyLootTable(iss,0.3f,getRL(bn));
    modifyLootTable(iss*2,0.3f,getRL(bn));
    modifyLootTable(iss*3,0.25f,getRL(bn));
    modifyLootTable(iss*4,0.2f,getRL(bn));
    modifyLootTable(iss*5,0.15f,getRL(bn));
    modifyLootTable(iss*6,0.1f,getRL(bn));
    modifyLootTable(<item:emcworld:base_emc_stored_totem>.withDamage(math.getRangeRandom(0,50000)),0.35f,getRL(name));
}



