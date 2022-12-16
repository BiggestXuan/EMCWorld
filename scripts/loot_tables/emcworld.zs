# priority 27
import mods.emcworld.math;

private function tweakerBotania() as void{
    var ei = <item:extendedcrafting:ender_ingot>;
    var eei = <item:botania:elementium_ingot>;
    var name as string = "botania:gaia_guardian_2";
    for i in [ei,eei]{
        modifyLootTable(i*8,1f,getRL(name));
        modifyLootTable(i*6,0.33f,getRL(name));
        modifyLootTable(i*5,0.22f,getRL(name));
        modifyLootTable(i*4,0.15f,getRL(name));
        modifyLootTable(i*3,0.1f,getRL(name));
        modifyLootTable(i*2,0.06f,getRL(name));
        modifyLootTable(i,0.04f,getRL(name));
    }
    modifyLootTable(<item:emcworld:base_emc_stored_totem>.withDamage(math.getRangeRandom(0,50000)),1f,getRL(name));
}

public function tweakerEMCWorld() as void{
    tweakerBotania();
}